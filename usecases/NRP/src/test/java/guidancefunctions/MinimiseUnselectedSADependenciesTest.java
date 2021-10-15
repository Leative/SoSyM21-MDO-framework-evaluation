package guidancefunctions;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import testhelper.ModelLoader;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class MinimiseUnselectedSADependenciesTest {
	
	final private static double DELTA = 1e-3;
	private EObject model;
	private NoSAsWithUnselectedDependencies objective = new NoSAsWithUnselectedDependencies();

	@Test
	@DisplayName("All (transitive) dependencies are fulfilled. Constraint should be fulfilled.")
	public void testAllDependenciesOfSelectedArtifactsFulfilled() {
		model = ModelLoader.loadModel("guidancefunctions/artifact-selected-with-all-dependencies.xmi");
		assertEquals(0.0d, objective.computeFitness(new Solution(model)), DELTA);
	}
	
	@Test
	@DisplayName("One transitive dependency is missing. Violation should only count once for the missing direct dependency.")
	public void testTransitiveDependencyOfSelectedArtifactsMissing() {
		model = ModelLoader.loadModel("guidancefunctions/artifact-selected-but-missing-transitive-dependency.xmi");
		assertEquals(1.0d, objective.computeFitness(new Solution(model)), DELTA);
	}

	@Test
	@DisplayName("One direct dependency is missing, transitive dependency is fulfilled. Violation should only count once for the missing direct dependency.")
	public void testDirectDependencyOfSelectedArtifactsMissing() {
		model = ModelLoader.loadModel("guidancefunctions/artifact-selected-but-missing-direct-dependency.xmi");
		assertEquals(1.0d, objective.computeFitness(new Solution(model)), DELTA);
	}
	
	@Test
	@DisplayName("One direct dependency and a transitive dependency are missing. Violation should only count once for the missing direct dependency.")
	public void testDirectAndTransitiveDependencyOfSelectedArtifactsMissing() {
		model = ModelLoader.loadModel("guidancefunctions/artifact-selected-but-missing-direct-and-transitive-dependency.xmi");
		assertEquals(1.0d, objective.computeFitness(new Solution(model)), DELTA);
	}
	
	@Test
	@DisplayName("Multiple direct dependencies are missing. Violation should only count once for the dependent artifact.")
	public void testMultipleDirectDependenciesOfSelectedArtifactsMissing() {
		model = ModelLoader.loadModel("guidancefunctions/artifact-selected-but-missing-multiple-direct-dependencies.xmi");
		assertEquals(1.0d, objective.computeFitness(new Solution(model)), DELTA);
	}
	
	@Test
	@DisplayName("Some of multiple direct dependencies are missing. Violation should still be counted.")
	public void testSomeDirectDependenciesOfSelectedArtifactsMissing() {
		model = ModelLoader.loadModel("guidancefunctions/artifact-selected-but-missing-some-direct-dependencies.xmi");
		assertEquals(1.0d, objective.computeFitness(new Solution(model)), DELTA);
	}
	
	@Test
	@DisplayName("Multiple artifacts depend on same missing artifact. Violation should count for each dependent artifact.")
	public void testMultipleSelectedArtifactsDependOnMissingArtifact() {
		model = ModelLoader.loadModel("guidancefunctions/multiple-artifacts-selected-but-missing-same-dependency.xmi");
		assertEquals(2.0d, objective.computeFitness(new Solution(model)), DELTA);
	}
}
