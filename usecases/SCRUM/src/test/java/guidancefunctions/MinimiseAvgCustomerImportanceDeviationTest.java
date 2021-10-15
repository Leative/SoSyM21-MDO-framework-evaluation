package guidancefunctions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import SprintPlanning.SprintPlanningPackage;
import testhelper.ModelLoader;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class MinimiseAvgCustomerImportanceDeviationTest {
	
	final private static double DELTA = 1e-2;
	private EObject model;
	private MinimiseAvgCustomerImportanceDeviation objective = new MinimiseAvgCustomerImportanceDeviation();
	
	@BeforeAll
	public static void register() {
		EObject metamodel = SprintPlanningPackage.eINSTANCE;		
	}
	
	@Test
	public void testFitnessOfSingleSprint() {
		model = ModelLoader.loadModel("guidancefunctions/single-sprint.xmi");
		assertEquals(0, objective.computeFitness(new Solution(model)), DELTA);
	}
	
	@Test
	public void testFitnessOfSingleStakeholderSingleSprint() {
		model = ModelLoader.loadModel("guidancefunctions/single-stakeholder.xmi");
		assertEquals(0, objective.computeFitness(new Solution(model)), DELTA);
	}
	
	@Test
	public void testFitnessOfSingleStakeholderTwoSprintsDeviating() {
		model = ModelLoader.loadModel("guidancefunctions/single-stakeholder-two-sprints-deviating.xmi");
		// StdDev of sample [6, 10] 
		assertEquals(2.828d, objective.computeFitness(new Solution(model)), DELTA);
	}
	
	@Test
	public void testFitnessOfSingleStakeholderTwoSprintsWithMultipleItemsDeviating() {
		model = ModelLoader.loadModel("guidancefunctions/single-stakeholder-two-sprints-with-multiple-items-deviating.xmi");
		// StdDev of sample [13, 10]
		assertEquals(2.121d, objective.computeFitness(new Solution(model)), DELTA);
	}
	
	@Test
	public void testFitnessOfTwoStakeholdersTwoSprintsWithMultipleItemsDeviating() {
		model = ModelLoader.loadModel("guidancefunctions/two-stakeholders-two-sprints-with-multiple-items-deviating.xmi");
		// StdDev of sample1 [13, 10] = 2.121
		// StdDev of sample2 [80, 171] = 64.347
		// Avg. 
		assertEquals(33.234d, objective.computeFitness(new Solution(model)), DELTA);
	}
}
