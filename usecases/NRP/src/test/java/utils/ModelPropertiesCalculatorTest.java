package utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import nextReleaseProblem.NRP;
import nextReleaseProblem.SoftwareArtifact;
import testhelper.ModelLoader;

public class ModelPropertiesCalculatorTest {
	
	EObject model;
	
	@Test
	public void testDependencyDistributionIsCorrect() {
		NRP model = (NRP) ModelLoader.loadModel("utils/dependency-hierarchy.xmi");
		Map<Integer, Integer> dep = ModelPropertiesCalculator.getDependencyCountDistribution(model);
		Map<Integer, Integer> expected = new HashMap<>();
		expected.put(0, 4);
		expected.put(1, 1);
		expected.put(2, 1);
		expected.put(3, 1);
		assertEquals(expected, dep);		
	}
	
	@Test
	public void testIsDependencyDistributionIsCorrect() {
		NRP model = (NRP) ModelLoader.loadModel("utils/dependency-hierarchy.xmi");
		Map<Integer, Integer> dep = ModelPropertiesCalculator.getIsDependencyCountDistribution(model);
		Map<Integer, Integer> expected = new HashMap<>();
		expected.put(0, 2);
		expected.put(1, 4);
		expected.put(2, 1);
		assertEquals(expected, dep);		
	}
	
	@Test
	public void testTransitiveArtifactDependencyCorrect() {
		NRP model = (NRP) ModelLoader.loadModel("utils/realisation_depending_on_three_interdependent_sa_hierarchies_one_hierarchy_having_two_dep_paths_to_same_leaf.xmi");
		Map<SoftwareArtifact, Set<SoftwareArtifact>> deps = ModelPropertiesCalculator.getTransitiveArtifactDependencies(model);
		Map<String, Set<String>> expected = new HashMap<>();
		expected.put("SA_Root_1", new HashSet<>(Arrays.asList("SA_1_1", "SA_shared_between_hierarchies", "SA_1_2")));
		expected.put("SA_1_1", new HashSet<>(Arrays.asList("SA_1_2")));
		expected.put("SA_shared_between_hierarchies", new HashSet<>(Arrays.asList("SA_1_2")));
		expected.put("SA_1_2", new HashSet<>());
		expected.put("SA_Root_2", new HashSet<>(Arrays.asList("SA_2_1", "SA_shared_between_hierarchies", "SA_1_2")));
		expected.put("SA_2_1", new HashSet<>());
		
		Map<String, Set<String>> stringDeps = new HashMap<>();
		deps.forEach((sa, depSet) -> stringDeps.put(sa.getName(), depSet.stream().map(depSa -> depSa.getName()).collect(Collectors.toSet())));
		
		assertEquals(expected, stringDeps);
	}
}
