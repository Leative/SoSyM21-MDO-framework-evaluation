package utils;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;

import SprintPlanning.Plan;
import SprintPlanning.SprintPlanningPackage;

public class LoadModel {
	
	// The dir used by the HenshinResourceSet to resolve relative paths.
	private static final String BASEDIR = "models";
	
	public static void main(String[] args) {
				
		EObject modelRoot = loadModel();
		
		/* 
		 * If you registered the metamodel, you should have an object with the 
		 * type of your model root. In that case, you can cast to the concrete
		 * type if you need to access model elements.
		 */
		System.out.println("Type of the root: " + modelRoot.getClass());
		//Plan p = (Plan) modelRoot;
		EObject b = (EObject) modelRoot.eGet(modelRoot.eClass().getEStructuralFeature("backlog"));
		EList<EObject> w = (EList<EObject>) b.eGet(b.eClass().getEStructuralFeature("workitems"));
		EObject wi = (EObject) w.get(0);
		Object plannedFor = wi.eGet(wi.eClass().getEStructuralFeature("isPlannedFor"));
		
		System.out.println(plannedFor==null);
	}
	
	public static EObject loadModel() {
		
		HenshinResourceSet rs = new HenshinResourceSet(BASEDIR);
		/*
		 * Metamodels need to be registered in order to use instance models in
		 * a static way (using the classes with their specific getters 
		 * generated for your model. This can be done by registering the 
		 * package of your model in the package registry of the ResourceSet 
		 * which you use to load instance models. 
		 * In that case, it does not matter if the loaded model has been 
		 * created as a static model (e.g. by using the api as shown in 
		 * CreateAndStoreModel.java) or if it is a dynamic model created by, 
		 * e.g., right clicking the root in the .ecore file and choosing 
		 * "Create Dynamic Instance...".
		 * 
		 * You might want to compare instanceStatic and instanceDynamic in a
		 * text editor to learn about the differences.
		 */ 
		//rs.registerDynamicEPackages("SprintPlanning.ecore");
		rs.getPackageRegistry().put(SprintPlanningPackage.eINSTANCE.getNsURI(), 
				SprintPlanningPackage.eINSTANCE);
		//Resource res = rs.getResource("sprint-planning-model-5-stakeholders-119-items.xmi");
		Resource res = rs.getResource("sprint-planning-model-10-stakeholders-254-items.xmi");
		//Resource res = rs.getResource("test_rules_input.xmi");
		
		/* 
		 * Usually there is only one model root stored in an xmi file. So
		 * getContents() should return a list with one element.
		 */
		EObject modelRoot = res.getContents().get(0);
		 
		
		/* WARNING:
		 * If the metamodel is not registered before loading, you are working
		 * with dynamic models. In that case you can access all model elements 
		 * only by very generic reflective accessor methods. If you think you
		 * need dynamic EMF more info can be found here:
		 * https://www.ibm.com/developerworks/library/os-eclipse-dynamicemf
		 */
		
		return modelRoot;
	}
}
