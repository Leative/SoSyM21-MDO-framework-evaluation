package transformations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.emf.henshin.interpreter.impl.LoggingApplicationMonitor;
import org.junit.jupiter.api.Test;

public class moveWorkItemToNewSprintRespectingMaxNotCausingEmptySprintTest extends AbstractTransformationTest {
	
	private static final String MODEL_ROOT = "../src/test/resources/transformations/"
			+ "moveWorkItemToNewSprintRespectingMaxNotCausingEmptySprint/";

	public moveWorkItemToNewSprintRespectingMaxNotCausingEmptySprintTest() {
		super("sprint-mutation.henshin", "moveWorkItemToNewSprintRespectingMaxNotCausingEmptySprint");
		
	}
	
	@Test
	public void testMaximumSprintsExceeded() {
		setTestModel(MODEL_ROOT + "maxSprintsReached.xmi");
		assertFalse(unitApp.execute(new LoggingApplicationMonitor()), "Rule should not be applicable");
	}
	
	@Test
	public void testNoEmptySprintIsCaused() {
		setTestModel(MODEL_ROOT + "sprintWithSingleItem.xmi");
		assertFalse(unitApp.execute(new LoggingApplicationMonitor()), "Rule should not be applicable");
	}
	
	@Test
	public void testNewSprintIsCreated() {
		setTestModel(MODEL_ROOT + "sprintWithTwoItemsMaxNotReached.xmi");
		assertTrue(unitApp.execute(new LoggingApplicationMonitor()), "Rule should be applicable");
	}
}
