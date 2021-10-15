package transformations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.emf.henshin.interpreter.Match;
import org.eclipse.emf.henshin.interpreter.RuleApplication;
import org.eclipse.emf.henshin.interpreter.impl.LoggingApplicationMonitor;
import org.eclipse.emf.henshin.interpreter.impl.MatchImpl;
import org.eclipse.emf.henshin.model.Node;
import org.eclipse.emf.henshin.model.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextReleaseProblem.NRP;
import nextReleaseProblem.SoftwareArtifact;

public class addArtifactRespectingHierarchyAndBudgetTest extends AbstractTransformationTest {
	
	public addArtifactRespectingHierarchyAndBudgetTest() {
		super("nrp-mutation.henshin", "addArtifactRespectingHierarchyAndBudget");
	}
	
	@BeforeEach
	public void useSameModelforAllTests() {
		setTestModel("../src/test/resources/"
				+ "addArtifactRespectingHierarchyAndBudget/"
				+ "common.xmi");
	}
		
	@Test
	@DisplayName("Cannot add artifact for which required artifact is not in solution.")
	public void testAddArtifactWithMissingRequirement() {
		setArtifactToAdd("SAmissingReq");
		assertFalse(unitApp.execute(new LoggingApplicationMonitor()), "Rule should not be applicable");
	}
	
	@Test
	@DisplayName("Can add artifact which has no requirements.")
	public void testAddArtifactLackingRequirements() {
		setArtifactToAdd("SAnoReq");		
		assertTrue(unitApp.execute(new LoggingApplicationMonitor()), "Rule should be applicable");
	}
	
	@Test
	@DisplayName("Cannot add artifact with costs exceeding budget.")
	public void testAddArtifactWithCostsExeedingBudget() {
		setArtifactToAdd("SAcostsExceedBudget");
		assertFalse(unitApp.execute(new LoggingApplicationMonitor()), "Rule should not be applicable");
	}
	
	@Test
	@DisplayName("Cannot add artifact as total costs would exceed budget.")
	public void testAddArtifactWithTotalCostsExceedBudget() {
		setArtifactToAdd("SAtotalCostsExceedBudget");
		assertFalse(unitApp.execute(new LoggingApplicationMonitor()), "Rule should not be applicable");
	}
	
	@Test
	@DisplayName("Can add artifact with fulfilled requirements.")
	public void testAddCheapArtifactWithFulfilledRequirement() {
		setArtifactToAdd("SAcheapFulfilledReq");
		assertTrue(unitApp.execute(new LoggingApplicationMonitor()), "Rule should be applicable");
	}
	
	@Test
	@DisplayName("Can add artifact with fulfilled requirements even if there is a cheaper option.")
	public void testAddExpensiveArtifactWithFulfilledRequirement() {
		setArtifactToAdd("SAexpensiveFulfilledReq");
		assertTrue(unitApp.execute(new LoggingApplicationMonitor()), "Rule should be applicable");
	}
	
	@Test
	@DisplayName("Cannot add artifact twice.")
	public void testAddSameArtifactTwice() {
		setArtifactToAdd("SAinSolution");
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
