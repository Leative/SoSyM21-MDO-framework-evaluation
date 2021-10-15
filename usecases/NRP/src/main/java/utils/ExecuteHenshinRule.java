package utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.UnitApplication;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.interpreter.impl.EngineImpl;
import org.eclipse.emf.henshin.interpreter.impl.LoggingApplicationMonitor;
import org.eclipse.emf.henshin.interpreter.impl.UnitApplicationImpl;
import org.eclipse.emf.henshin.model.Module;
import org.eclipse.emf.henshin.model.Unit;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;

import nextReleaseProblem.Cost;
import nextReleaseProblem.NRP;
import nextReleaseProblem.NextReleaseProblemFactory;
import nextReleaseProblem.SoftwareArtifact;
import nextReleaseProblem.Solution;

public class ExecuteHenshinRule {
		
	public static void main(String[] args) {
		EObject modelRoot = LoadModel.loadModel("bla");
		/* 
		 * Generally it is a good idea to use the same ResourceSet for loading
		 * the model and the Henshin rules as it is less error prone. 
		 */		
		HenshinResourceSet rs = (HenshinResourceSet) modelRoot.eResource()
				.getResourceSet();
		
		/*
		 * Static rules need to be used when working with static models. 
		 * When creating a .henshin file, make sure to add the metamodel
		 * "From Registry" and not via the .ecore file.
		 * To add the metamodel to the Registry, start a new Eclipse
		 * instance (right click on project -> Run as -> Eclipse Application)
		 * from the workspace containing you metamodel classes. In that new
		 * instance you should be able to create henshin diagrams using
		 * the "From Registry" button to load the model.
		 * 
		 * You might want to compare rulesStatic and rulesDynamic in a text
		 * editor to learn about the differences.
		 */
		Module rules = rs.getModule("nrp-mutation.henshin", true);
		
		/* For performance reasons you should reuse the Engine and EGraph in 
		 * your code when possible. However, do NOT reuse UnitApplications if
		 * you don't have a specific reason for that. UnitApplication keep some
		 * state of former rule executions. Reusing them can lead to unintended
		 * behavior.
		 */
		Engine engine = new EngineImpl();
		
		
		EGraph graph = new EGraphImpl(modelRoot);
		
		/* 
		 * If multiple matches for a rule exists in a model, the following 
		 * allows a rule to select randomly where it is applied.
		 */ 
		engine.getOptions().put(Engine.OPTION_DETERMINISTIC, false);
		//engine.getOptions().put(Engine.OPTION_SORT_VARIABLES, false);
		
		Unit testRule = rules.getUnit("createAttribute");
		UnitApplication application = new UnitApplicationImpl(engine, graph, testRule, null);
		application.execute(new LoggingApplicationMonitor());
		System.out.println(application.getResultParameterValue("c"));
		System.out.println(application.getResultParameterValue("x"));
		System.out.println(((NRP)modelRoot).getAvailableArtifacts().get(0).getCosts().get(0).getAmount());
		System.out.println(((NRP)modelRoot).getAvailableArtifacts().get(0).getCosts().get(1).getAmount());
		
		NextReleaseProblemFactory fac = NextReleaseProblemFactory.eINSTANCE;
		NRP nrp = fac.createNRP();
		nrp.setBudget(100);
		Solution sol = fac.createSolution();
		SoftwareArtifact sa = fac.createSoftwareArtifact();
		Cost cost = fac.createCost();
		cost.setAmount(10);
		sa.getCosts().add(cost);
		sol.getSelectedArtifacts().add(sa);
		nrp.getSolutions().add(sol);
		nrp.getAvailableArtifacts().add(sa);

		Resource res = rs.createResource("testOutput.xmi");
		res.getContents().add(modelRoot);

		try {
			res.save(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	
	private void executePerformanceTest(Engine engine, EGraph graph, Module rules) {
		Unit testRule = rules.getUnit("addArtifactRespectingHierarchyAndBudget");
		Unit resetRule = rules.getUnit("deleteSelection");
		
		long startTime=System.nanoTime();
		
		int i = 0;
		while(i < 15) {
			UnitApplication application = new UnitApplicationImpl(engine, graph, testRule, null);
				//application.setParameterValue("name", "b");
			
				/*
				 *  If you want to analyse the execution of a rule you can use a
				 *  LoggingApplicationMonitor to get some feedback on console.
				 *  Otherwise use null as parameter. 
				 */	
			if (!application.execute(null)) {
				System.out.println("Stopped prematurely inner loop: " + i);
				UnitApplication resetApplication = new UnitApplicationImpl(engine, graph, resetRule, null);
				if (!resetApplication.execute(null)) {
					System.out.println("Could not reset: " + i );
				}
			}//new LoggingApplicationMonitor());
			i++;
		}
		long elapsed = System.nanoTime() - startTime;
		System.out.printf("Elapsed time: %d ms\n", elapsed/1000000);
	}
	
	private void showArtifactContributions(NRP nrp) {
		Map<Integer, Integer> contribDistrMap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> contribSelectionMap = new HashMap<Integer, Integer>();
		contribDistrMap = new HashMap<Integer, Integer>();
		for (SoftwareArtifact art : nrp.getAvailableArtifacts()) {
			int contrCount = art.getContributesTo().size();
			contribDistrMap.compute(contrCount, (k, v) -> (v==null) ? 1 : v+1);
			if (!art.getSolutions().isEmpty()) {
				contribSelectionMap.compute(contrCount, (k,v) -> (v==null) ? 1 : v+1);
			}
			
		}
		System.out.println("Contribution counts:");
		for (Entry<Integer, Integer> entry : contribDistrMap.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		System.out.println("Selections based on contribution count:");
		for (Entry<Integer, Integer> entry : contribSelectionMap.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}		
	}
}

/*
 * For more information on using the Henshin API visit
 * https://wiki.eclipse.org/Henshin/Interpreter.
 */