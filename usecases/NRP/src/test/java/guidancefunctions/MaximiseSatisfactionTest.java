package guidancefunctions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextReleaseProblem.NextReleaseProblemPackage;
import testhelper.ModelLoader;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class MaximiseSatisfactionTest {

	final private static double DELTA = 1e-3;
	private EObject model;
	private MaximiseSatisfaction objective = new MaximiseSatisfaction();
	
	@BeforeAll
	public static void register() {
		EObject metamodel = NextReleaseProblemPackage.eINSTANCE;
	}
	
	@Test
	@DisplayName("Get all valuations of the dependencies of a requirement. ")
	public void testCollectingValuationsOfHierarchicalRequirement() {
		model = ModelLoader.loadModel("guidancefunctions/simple-dependencies.xmi");
		EObject customer = objective.getReferenceFeature(model, "customers").iterator().next();
		EObject requirement = objective.getReferenceFeature(model, "requirements").iterator().next();
		List<Double> expected = Arrays.asList(4.0, 1.0);
		
		List<Double> actual = new ArrayList<Double>();
		for (EObject obj : objective.getDependencyValuations(requirement, customer)) {
			actual.add((Double)objective.getFeature(obj, "value"));
		}
		assertIterableEquals(expected, actual);
		
	}
	
	@Test
	@DisplayName("Fitness: Direct requirement has multiple valuations of the same customer. Only the highest should be considered.")
	public void testFitnessOfMultiValuationsWithoutDependencies() {
		model = ModelLoader.loadModel("guidancefunctions/direct-requirement-with-multiple-valuations-for-same-customer.xmi");
		assertEquals(-0.9d, objective.computeFitness(new Solution(model)), DELTA);
	}
	
	@Test
	@DisplayName("Fitness: Requirement has a dependency with multiple valuations of the same customer. Only the highest should be considered.")
	public void testFitnessOfMultiValuationsWithDependencies() {
		model = ModelLoader.loadModel("guidancefunctions/dependencies-with-multiple-valuations-for-same-customer.xmi");
		assertEquals(-0.9d, objective.computeFitness(new Solution(model)), DELTA);
	}
	
	@Test
	@DisplayName("Fitness: Direct requirement has multiple partial realisations. Only the highest should be considered.")
	public void testMultipleRealisations() {
		model = ModelLoader.loadModel("guidancefunctions/multiple-realisations.xmi");
		assertEquals(-0.8d, objective.computeFitness(new Solution(model)), DELTA);
	}
	
	@Test
	@DisplayName("Fitness: Requirement has multiple dependencies with different valuations.")
	public void testSimpleDependencies() {
		model = ModelLoader.loadModel("guidancefunctions/simple-dependencies.xmi");
		assertEquals(-0.9d, objective.computeFitness(new Solution(model)), DELTA);
	}
	
	@Test
	@DisplayName("Fitness: No artifact is selected for the solution.")
	public void testEmptySolution() {
		model = ModelLoader.loadModel("guidancefunctions/empty-solution.xmi");
		assertEquals(0.0d, objective.computeFitness(new Solution(model)), DELTA);
	}
	
	@Test
	@DisplayName("Fitness: Artifact of single artifact realisation is not selected.")
	public void testSingleArtifactRealisationMissingArtifact() {
		model = ModelLoader.loadModel("guidancefunctions/single-artifact-realisation-missing-artifact.xmi");
		assertEquals(-0.4d, objective.computeFitness(new Solution(model)), DELTA);
	}
	
	@Test
	@DisplayName("Fitness: Artifact of multiple artifact realisation is not selected.")
	public void testMultipleArtifactRealisationMissingArtifact() {
		model = ModelLoader.loadModel("guidancefunctions/multiple-artifact-realisation-missing-artifact.xmi");
		assertEquals(-0.5d, objective.computeFitness(new Solution(model)), DELTA);
	}
	
	@Test
	@DisplayName("Fitness: Artifact which is direct requirement of a needed artifact of realisation is not selected.")
	public void testRealisationMissingDirectRequirementOfNeededArtifact() {
		model = ModelLoader.loadModel("guidancefunctions/realisation-missing-direct-requirement-of-needed-artifact.xmi");
		assertEquals(0.0d, objective.computeFitness(new Solution(model)), DELTA);
	}	
	
	@Test
	@DisplayName("Fitness: Artifact which is indirect requirement of a needed artifact of realisation is not selected.")
	public void testRealisationMissingHierarchicalRequirementOfNeededArtifact() {
		model = ModelLoader.loadModel("guidancefunctions/realisation-missing-hierarchical-requirement-of-needed-artifact.xmi");
		assertEquals(0.0d, objective.computeFitness(new Solution(model)), DELTA);
	}
	
	@Test
	@DisplayName("Fitness: Multiple customers have different importance.")
	public void testMultipleCustomersWithDifferentImportance() {
		model = ModelLoader.loadModel("guidancefunctions/multiple-customers-with-different-importance.xmi");
		assertEquals(-5.0d, objective.computeFitness(new Solution(model)), DELTA);
	}
}
