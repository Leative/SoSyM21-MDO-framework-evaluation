package guidancefunctions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import SprintPlanning.SprintPlanningPackage;
import testhelper.ModelLoader;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class MinimiseCustomerSatisfactionIndexTest {
	
	final private static double DELTA = 1e-3;
	private EObject model;
	private MinimiseCustomerSatisfactionIndex objective = new MinimiseCustomerSatisfactionIndex();
	
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
		StandardDeviation s = new StandardDeviation();
		double[] expectedList = {6.0, 10.0}; 
		assertEquals(s.evaluate(expectedList), objective.computeFitness(new Solution(model)), DELTA);
	}
}
