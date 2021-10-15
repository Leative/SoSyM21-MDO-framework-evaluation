package guidancefunctions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import testhelper.ModelLoader;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class RespectBudgetTest {
	
	final private static double DELTA = 1e-3;
	EObject model;
	
	@Test
	@DisplayName("")
	public void testLimitNotExceeded() {
		model = ModelLoader.loadModel("guidancefunctions/artifact-selected-with-all-dependencies.xmi");
		NotExceedingBudget rb = new NotExceedingBudget();
		assertEquals(0.0d, rb.computeFitness(new Solution(model)), DELTA);
	}
	
	@Test
	@DisplayName("")
	public void testLimitExceeded() {
		model = ModelLoader.loadModel("guidancefunctions/artifact-selected-but-missing-direct-and-transitive-dependency.xmi");
		NotExceedingBudget rb = new NotExceedingBudget();
		assertEquals(1.0d, rb.computeFitness(new Solution(model)), DELTA);
	}
}
