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
import org.junit.jupiter.api.Test;

import nextReleaseProblem.NRP;
import nextReleaseProblem.SoftwareArtifact;

public class removeArtifactRespectingHierarchyAndImportanceTest extends AbstractTransformationTest {

	@BeforeEach
	public void useSameModelforAllTests() {
		setTestModel("../src/test/resources/"
				+ "removeArtifactRespectingHierarchyAndImportance/"
				+ "common.xmi");
	}
	
	public removeArtifactRespectingHierarchyAndImportanceTest() {
		super("nrp-mutation.henshin", "removeArtifactRespectingHierarchyAndImportance");
	}
	
	@Test
	public void testRequiredArtifact() {
		setArtifactToRemove("SARequired");
		assertFalse(unitApp.execute(new LoggingApplicationMonitor()), "Rule should not be applicable");
	}
	
	@Test
	public void testArtifactContributesToNoRealisations() {
		setArtifactToRemove("SANotRequiredNoDirectRealisation");
		assertTrue(unitApp.execute(new LoggingApplicationMonitor()), "Rule should be applicable");
	}
	
	@Test
	public void testArtifactContributesToOneRealisation() {
		setArtifactToRemove("SANotRequiredOneDirectRealisation");
		assertTrue(unitApp.execute(new LoggingApplicationMonitor()), "Rule should be applicable");
	}
	
	@Test
	public void testArtifactContributesToTwoRealisations() {
		setArtifactToRemove("SANotRequiredTwoDirectRealisations");
		assertFalse(unitApp.execute(new LoggingApplicationMonitor()), "Rule should not be applicable");		
	}
	
	@Test
	public void testArtifactContributesIndiretclyToTwoRealisations() {
		setArtifactToRemove("SANotRequiredTwoIndirectRealisations");
		assertTrue(unitApp.execute(new LoggingApplicationMonitor()), "Rule should not be applicable");		
	}
	
	private void setArtifactToRemove(String nameOfArtifact) {
		Rule testRule = (Rule)unitApp.getUnit();
		Node removedSANodeOfRule = testRule.getLhs().getNode("a");
		
		NRP nrp = (NRP) inputModel;
		SoftwareArtifact SAOfModelToRemove = nrp.getAvailableArtifacts().stream()
				.filter(sa -> nameOfArtifact.equals(sa.getName())).findFirst().get();
		
		Match partialMatch = new MatchImpl(testRule);
		partialMatch.setNodeTarget(removedSANodeOfRule, SAOfModelToRemove);
		((RuleApplication) unitApp).setPartialMatch(partialMatch);		
	}
	

}
