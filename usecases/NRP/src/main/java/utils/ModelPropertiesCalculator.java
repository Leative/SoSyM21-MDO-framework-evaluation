package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.eclipse.emf.ecore.EObject;

import com.sun.source.tree.RequiresTree;

import nextReleaseProblem.Cost;
import nextReleaseProblem.Customer;
import nextReleaseProblem.NRP;
import nextReleaseProblem.Requirement;
import nextReleaseProblem.RequirementRealisation;
import nextReleaseProblem.SoftwareArtifact;

public class ModelPropertiesCalculator {
	
	static int maxArtifactDepCount = 0;
	static int minArtifactDepCount = Integer.MAX_VALUE;
	static int maxRealDepCount = 0;
	static int minRealDepCount = Integer.MAX_VALUE;
	static DescriptiveStatistics artifactDepStatistics;
	static DescriptiveStatistics realisationDepStatistics;	
		
	public static void main(String[] args) throws IOException {
		
		List<String> modelFileNames = new LinkedList<>();
		//modelFileNames.add("nrp-model-2-cus-10-imp-2-req-5-sa-3-dep.xmi");
		modelFileNames.add("nrp-model-5-cus-25-req-63-sa.xmi");
		modelFileNames.add("nrp-model-25-cus-50-req-203-sa.xmi");
		modelFileNames.add("nrp-model-5-cus-25-req-60-sa-3-maxoutdep.xmi");
		modelFileNames.add("nrp-model-25-cus-50-req-200-sa-3-maxoutdep.xmi");
		modelFileNames.add("nrp-model-25-cus-10-imp-25-req-300-sa-10-dep.xmi");
		modelFileNames.add("nrp-model-25-cus-10-imp-25-req-300-sa-20-dep.xmi");
		modelFileNames.add("nrp-model-25-cus-10-imp-25-req-600-sa-20-dep.xmi");
		modelFileNames.add("nrp-model-25-cus-10-imp-25-req-200-sa-4-dep.xmi");
		modelFileNames.add("nrp-model-25-cus-10-imp-50-req-200-sa-4-dep.xmi");
		modelFileNames.add("nrp-model-25-cus-10-imp-50-req-400-sa-4-dep.xmi");
		
		File f = new File("models/model-properties.txt");
		FileWriter fw = new FileWriter(f);
		
		for (String name : modelFileNames) {	
			EObject modelRoot = LoadModel.loadModel(name);
			NRP nrpModel = (NRP)modelRoot;
			fw.append("File: " + name + "\n");
			fw.append("Customers: " + nrpModel.getCustomers().size() + "\n");
			fw.append("Requirements: " + nrpModel.getRequirements().size() + "\n");
			int artifactNumber = nrpModel.getAvailableArtifacts().size();
			fw.append("Artifacts: " + artifactNumber + "\n");
			fw.append("Budget: " + nrpModel.getBudget() + "\n");
			fw.append("Total costs of all artifacts: " + getTotalCosts(nrpModel) + "\n");
			fw.append("Costs of most expensive artifact: " + getMaxCosts(nrpModel) + "\n");		
			fw.append("Number of realisations: " + nrpModel.getRequirements().stream().map(req -> req.getRealisations().size()).reduce(0, Integer::sum) + "\n");
			fw.append("Max. valuations per requirement: " + getMaxValuations(nrpModel) + "\n");
			fw.append("Max. realisations per requirement: " + getMaxRealisations(nrpModel) + "\n");
			fw.append("Distribution of number of artifacts a realisation depends on: " +  getDependsOnCountDistribution(nrpModel) + "\n");
			fw.append("Distribution of number of realisations an artifact contributes to: " +  getContributionCountDistribution(nrpModel) + "\n");
			fw.append("Distribution of number of artifact an artifact is dependent on: " +  getDependencyCountDistribution(nrpModel) + "\n");
			fw.append("Distribution of number of artifacts an artifact is a dependency for: " +  getIsDependencyCountDistribution(nrpModel) + "\n");
			artifactDepStatistics = new DescriptiveStatistics();
			Map<SoftwareArtifact, Set<SoftwareArtifact>> transitiveArtifactDepenencies = getTransitiveArtifactDependencies(nrpModel);
			fw.append("Min. number of transitive dependencies of an artifact: " + (int)artifactDepStatistics.getMin() + "\n");
			fw.append("Max. number of transitive dependencies of an artifact:: " + (int)artifactDepStatistics.getMax() + "\n");
			fw.append("Median number of transitive dependencies of an artifact:: " + (int)artifactDepStatistics.getPercentile(50) + "\n");			
			fw.append(transitiveArtifactDepenencies.toString() + "\n");
			realisationDepStatistics = new DescriptiveStatistics();
			Map<RequirementRealisation, Integer> transRealReq = getTransitiveRealisationDependencies(nrpModel, transitiveArtifactDepenencies);
			fw.append("Min. number of transitive dependencies of a realisation: " + (int)realisationDepStatistics.getMin() + "\n");
			fw.append("Max. number of transitive dependencies of a realisation: " + (int)realisationDepStatistics.getMax() + "\n");
			fw.append("Median number of transitive dependencies of a realisation: " + (int)realisationDepStatistics.getPercentile(50) + "\n");
			fw.append(transRealReq.toString() + "\n");
			fw.append("\n");
			checkMinimalCostForEachArtifact(nrpModel, 1.0d);
			checkCustomerAssignsValuationMultipleTimes(nrpModel);
			checkMinimumNumberOfRequirementsPerCustomer(nrpModel);
			fw.flush();
		}
		fw.close();		
	}
	
	/**
	 * Calculate the total costs of all artifacts specified in the given problem.
	 * @param nrpModel the problem root
	 * @return total costs
	 */
	public static double getTotalCosts(NRP nrpModel) {
		return nrpModel.getAvailableArtifacts().stream().map(artifact -> artifact.getCosts().get(0)).mapToDouble(Cost::getAmount).sum();
	}
	
	/**
	 * Calculate the maximum costs of any of the artifacts specified in the given problem.
	 * @param nrpModel the problem root
	 * @return costs of most expensive artifact
	 */
	public static double getMaxCosts(NRP nrpModel) {
		return nrpModel.getAvailableArtifacts().stream().map(artifact -> artifact.getCosts().get(0)).mapToDouble(Cost::getAmount).max().getAsDouble();
	}
	
	/**
	 * Calculate the maximum number of valuations occurring for a requirement in the given model.
	 * @param nrpModel the problem root
	 * @return costs of most expensive artifact
	 */
	public static int getMaxValuations(NRP nrpModel) {
		return nrpModel.getRequirements().stream().map(requirement -> requirement.getValuations().size()).mapToInt(i -> i).max().getAsInt();
	}
	
	/**
	 * Calculate the maximum number of realizations occurring for a requirement in the given model.
	 * @param nrpModel the problem root
	 * @return costs of most expensive artifact
	 */
	public static int getMaxRealisations(NRP nrpModel) {
		return nrpModel.getRequirements().stream().map(requirement -> requirement.getRealisations().size()).mapToInt(i -> i).max().getAsInt();
	}
	
	
	public static Map<SoftwareArtifact, Set<SoftwareArtifact>> getTransitiveArtifactDependencies(NRP nrpModel) {
		Map<SoftwareArtifact,  Set<SoftwareArtifact>> transitiveArtifactDependencies = new HashMap<>(nrpModel.getAvailableArtifacts().size());
		for (SoftwareArtifact artifact : nrpModel.getAvailableArtifacts()) {
			Set<SoftwareArtifact> artifactDeps = transitiveArtifactDependencies.get(artifact);
			if (artifactDeps == null) {
				artifactDeps = getTransitiveDependencies(artifact, transitiveArtifactDependencies);
				transitiveArtifactDependencies.put(artifact, artifactDeps);
			}
			int transitiveDeps = artifactDeps.size();
			artifactDepStatistics.addValue(transitiveDeps);
		}
		return transitiveArtifactDependencies;		
	}
	
	private static Set<SoftwareArtifact> getTransitiveDependencies(SoftwareArtifact artifact, Map<SoftwareArtifact, Set<SoftwareArtifact>> transitiveArtifactDependencies) {
		Set<SoftwareArtifact> transitiveDependencies = new HashSet<>();
		for (SoftwareArtifact dep : artifact.getRequires() ) {
			transitiveDependencies.add(dep);
			Set<SoftwareArtifact> depDeps = transitiveArtifactDependencies.get(dep);
			if (depDeps == null) {
				depDeps = getTransitiveDependencies(dep, transitiveArtifactDependencies);
				transitiveArtifactDependencies.put(dep, depDeps);
			}
			transitiveDependencies.addAll(depDeps);
		}
		return transitiveDependencies;
	}
	
	/**
	 * Calculate the maximum number of transitive dependencies needed to fulfill any of the 
	 * realisations of the given problem.
	 * @param nrpModel the problem root
	 * @return maximum number of transitive dependencies
	 */
	public static Map<RequirementRealisation, Integer> getTransitiveRealisationDependencies(NRP nrpModel, Map<SoftwareArtifact, Set<SoftwareArtifact>> transitiveArtifactDependencies) {
		List<RequirementRealisation> allRealisations = new ArrayList<RequirementRealisation>();
		for (Requirement req : nrpModel.getRequirements()) {
			allRealisations.addAll(req.getRealisations());
		}
		
		HashMap<RequirementRealisation, Integer> transitiveRealisationDepMap = new HashMap<>(allRealisations.size());
		for (RequirementRealisation real : allRealisations) {
			Set<SoftwareArtifact> transitiveRealisationDependencies = new HashSet<>();
			for (SoftwareArtifact sa : real.getDependsOn()) {
				transitiveRealisationDependencies.add(sa);
				transitiveRealisationDependencies.addAll(transitiveArtifactDependencies.get(sa));
			}
			int transitiveRealDepCount = transitiveRealisationDependencies.size();
			realisationDepStatistics.addValue(transitiveRealDepCount);
			transitiveRealisationDepMap.put(real, transitiveRealDepCount);
			
		}		
		return transitiveRealisationDepMap;
	}
		
	private static Map<Integer, Integer> getContributionCountDistribution(NRP nrpModel) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (SoftwareArtifact a : nrpModel.getAvailableArtifacts()) {
			Integer numberOfContributions = a.getContributesTo().size();
			if (map.containsKey(numberOfContributions)) {
				Integer current = map.get(numberOfContributions);
				map.put(numberOfContributions, current + 1);
			} else {
				map.put(numberOfContributions, 1);
			}
		}
		return map;
	}
	
	private static Map<Integer, Integer> getDependsOnCountDistribution(NRP nrpModel) {
		HashMap<Integer, Integer> map = new HashMap<>();
		List<RequirementRealisation> realisations = nrpModel.getRequirements().stream()
				.flatMap(req -> req.getRealisations().stream()).collect(Collectors.toList());
		for (RequirementRealisation r : realisations) {
			Integer numberOfContributions = r.getDependsOn().size();
			if (map.containsKey(numberOfContributions)) {
				Integer current = map.get(numberOfContributions);
				map.put(numberOfContributions, current + 1);
			} else {
				map.put(numberOfContributions, 1);
			}
		}
		return map;
	}
	
	public static Map<Integer, Integer> getDependencyCountDistribution(NRP nrpModel) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (SoftwareArtifact a : nrpModel.getAvailableArtifacts()) {
			int numberOfDependencies = a.getRequires().size();
			if (map.containsKey(numberOfDependencies)) {
				Integer current = map.get(numberOfDependencies);
				map.put(numberOfDependencies, current + 1);
			} else {
				map.put(numberOfDependencies, 1);
			}
		}
		return map;
	}
	
	public static Map<Integer, Integer> getIsDependencyCountDistribution(NRP nrpModel) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (SoftwareArtifact a : nrpModel.getAvailableArtifacts()) {
			Integer isDependencyCount = 0;
			for (SoftwareArtifact b : nrpModel.getAvailableArtifacts()) {
				if (b.getRequires().contains(a)) {
					isDependencyCount++;
				}
			}
			if (map.containsKey(isDependencyCount)) {
				Integer current = map.get(isDependencyCount);
				map.put(isDependencyCount, current + 1);
			} else {
				map.put(isDependencyCount, 1);
			}
		}
		return map;
	}
	
	private static void checkCustomerAssignsValuationMultipleTimes(NRP nrpModel) {
		for (Customer c : nrpModel.getCustomers()) {
			Set<Requirement> requirementSet = new HashSet<>();
//			for (Valuation v : c.getAssigns()) {
//				if (!requirementSet.add(v.getRequirement())) {
//					System.out.println("Multiple valuations for same customer-requirement combination.");
//				}
//			}
		}
		System.err.println("Multiple valuations for same customer-requirement combination.");
	}
	
	private static void checkMinimalCostForEachArtifact(NRP nrpModel, double value) {
		Map<SoftwareArtifact, Double> violations = nrpModel.getAvailableArtifacts().stream()
				.filter(a -> !a.getCosts().isEmpty() && a.getCosts().get(0).getAmount() < value).collect(Collectors.toMap(a->a, a->a.getCosts().get(0).getAmount()));
		if(!violations.isEmpty()) {
			System.err.println("The model contains artifacts violating minimum costs:\n" + violations);
		}
	}
	
	private static void checkMinimumNumberOfRequirementsPerCustomer(NRP nrpModel) {
		List<Customer> violations = nrpModel.getCustomers().stream().filter(c -> c.getAssigns().isEmpty()).collect(Collectors.toList());
		if (!violations.isEmpty()) {
			System.err.println("Customers without valuations exists: " + violations);
		}
	}
}
