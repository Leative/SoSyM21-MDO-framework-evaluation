package testhelper;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;

import nextReleaseProblem.NextReleaseProblemPackage;

public class ModelLoader {
	public static EObject loadModel(String modelPath) {
		NextReleaseProblemPackage metamodelPackage = NextReleaseProblemPackage.eINSTANCE;
		HenshinResourceSet resourceSet = new HenshinResourceSet("src/test/resources");
		return resourceSet.getResource(modelPath).getContents().get(0);
	}
}
