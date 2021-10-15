package transformations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.emf.henshin.interpreter.Match;
import org.eclipse.emf.henshin.interpreter.RuleApplication;
import org.eclipse.emf.henshin.interpreter.impl.LoggingApplicationMonitor;
import org.eclipse.emf.henshin.interpreter.impl.MatchImpl;
import org.eclipse.emf.henshin.model.Node;
import org.eclipse.emf.henshin.model.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextReleaseProblem.NRP;
import nextReleaseProblem.SoftwareArtifact;

public class addCheapestArtifactRespectingHierarchyAndBudgetTest extends AbstractTransformationTest {
	
	private static final String MODEL_ROOT = "../src/test/resources/"
			+ "addCheapestArtifactRespectingHierarchyAndBudget/";
	
	public addCheapestArtifactRespectingHierarchyAndBudgetTest() {
		super("nrp-mutation.henshin", "notConsidered_addCheapestArtifactRespectingHierarchyAndBudget");
	}
			
	@Test
	@DisplayName("Cannot add cheap artifact for which required artifact is not in solution.")
	public void testAddArtifactWithMissingRequirement() {
		setTestModel(MODEL_ROOT	+ "common.xmi");
		setArtifactToAdd("SAcheapMissingReq");
		assertFalse(unitApp.execute(new LoggingApplicationMonitor()), "Rule should not be applicable");
	}
	
	@Test
	@DisplayName("Can add cheap artifact which has no Requirements.")
	public void testAddCheapArtifactLackingRequirement() {
		setTestModel(MODEL_ROOT	+ "common.xmi");
		setArtifactToAdd("SAcheapNoReq");
		assertTrue(unitApp.execute(new LoggingApplicationMonitor()), "Rule should be applicable");
	}
	
	@Test
	@DisplayName("Cannot add expensive artifact although it has no requirements.")
	public void testAddArtifactLackingRequirements() {
		setTestModel(MODEL_ROOT	+ "common.xmi");
		setArtifactToAdd("SAexpensiveNoReq");		
		assertFalse(unitApp.execute(new LoggingApplicationMonitor()), "Rule should not be applicable");
	}
	
	@Test
	@DisplayName("Cannot add cheap artifact with costs exceeding budget.")
	public void testAddArtifactWithCostsExeedingBudget() {
		setTestModel(MODEL_ROOT	+ "exceedingCosts.xmi");
		setArtifactToAdd("SAcheapCostsExceedBudget");
		assertFalse(unitApp.execute(new LoggingApplicationMonitor()), "Rule should not be applicable");
	}
	
	@Test
	@DisplayName("Cannot add cheap artifact as total costs would exceed budget.")
	public void testAddArtifactWithTotalCostsExceedBudget() {
		setTestModel(MODEL_ROOT	+ "exceedingTotalCosts.xmi");
		setArtifactToAdd("SAcheapTotalCostsExceedBudget");
		assertFalse(unitApp.execute(new LoggingApplicationMonitor()), "Rule should not be applicable");
	}
	
	@Test
	@DisplayName("Can add cheap artifact with fulfilled requirements.")
	public void testAddCheapArtifactWithFulfilledRequirement() {
		setTestModel(MODEL_ROOT	+ "common.xmi");
		setArtifactToAdd("SAcheapFulfilledReq");
		assertTrue(unitApp.execute(new LoggingApplicationMonitor()), "Rule should be applicable");
	}
	
	@Test
	@DisplayName("Cannot add expensive artifact although requirements are fulfilled.")
	public void testAddExpensiveArtifactWithFulfilledRequirement() {
		setTestModel(MODEL_ROOT	+ "common.xmi");
		setArtifactToAdd("SAexpensiveFulfilledReq");
		assertFalse(unitApp.execute(new LoggingApplicationMonitor()), "Rule should not be applicable");
	}
	
	private void setArtifactToAdd(String nameOfArtifact) {
		Rule testRule = (Rule)unitApp.getUnit();
		Node addedSANodeOfRule = testRule.getLhs().getNode("a");
		
		NRP nrp = (NRP) inputModel;
		SoftwareArtifact SAOfModelToAdd = nrp.getAvailableArtifacts().stream()
				.filter(sa -> nameOfArtifact.equals(sa.getName())).findFirst().get();
		
		Match partialMatch = new MatchImpl(testRule);
		partialMatch.setNodeTarget(addedSANodeOfRule, SAOfModelToAdd);
		((RuleApplication) unitApp).setPartialMatch(partialMatch);		
	}
}
