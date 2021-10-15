package testhelper;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;

import SprintPlanning.SprintPlanningPackage;

public class ModelLoader {
	public static EObject loadModel(String modelPath) {
		SprintPlanningPackage metamodelPackage = SprintPlanningPackage.eINSTANCE;
		HenshinResourceSet resourceSet = new HenshinResourceSet("src/test/resources");
		return resourceSet.getResource(modelPath).getContents().get(0);
	}
}
