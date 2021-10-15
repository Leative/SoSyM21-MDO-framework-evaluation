package transformations;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.UnitApplication;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.interpreter.impl.EngineImpl;
import org.eclipse.emf.henshin.interpreter.impl.RuleApplicationImpl;
import org.eclipse.emf.henshin.interpreter.impl.UnitApplicationImpl;
import org.eclipse.emf.henshin.model.Module;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.model.Unit;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import SprintPlanning.SprintPlanningPackage;
	
	public class AbstractTransformationTest {
		
		protected static HenshinResourceSet rs;
		protected static Engine engine;
		
		protected String moduleUnderTestName;
		protected Module moduleUnderTest;
		protected String unitUnderTestName;				
		protected UnitApplication unitApp;
		
		protected Resource modelResource;
		protected EObject inputModel;		
		
		public AbstractTransformationTest(String moduleUnderTestName, String unitUnderTestName) {
			this.moduleUnderTestName = moduleUnderTestName;
			this.unitUnderTestName = unitUnderTestName;
		}
	
		/**
		 * Create resource set used in tests, register static metamodel and
		 * create engine used to execute rules.
		 */
		@BeforeAll
		public static void prepareEnvironment() {
			rs = new HenshinResourceSet("models");
			rs.getPackageRegistry().put(SprintPlanningPackage.eNS_URI, 
					SprintPlanningPackage.eINSTANCE);
			engine = new EngineImpl();
			engine.getOptions().put(Engine.OPTION_DETERMINISTIC, false);
		}
		
		/**
		 * Before each new test, the resource holding the input models and the rules need to be 
		 * reset. This is necessary as "modelResource = rs.getResource("xyz.xmi")" will not load 
		 * the model xyz again if it is already loaded by modelResource. As a consequence, changes
		 * we applied to xyz would not be discarded if the model is not unloaded first.
		 * In the same way, changes to the tested rules (e.g. to make them deterministic) are only
		 * reset after the rule is reloaded. 
		 */
		@BeforeEach
		public void resetEnvironment() {
			rs.getResources().forEach(r -> r.unload());
			moduleUnderTest = rs.getModule(moduleUnderTestName);
			Unit unitUnderTest = moduleUnderTest.getUnit(unitUnderTestName);
			if (unitUnderTest instanceof Rule) {
				unitApp = new RuleApplicationImpl(engine);
			} else {
				unitApp = new UnitApplicationImpl(engine);
			}
			unitApp.setUnit(unitUnderTest);
		}
		
		protected void setTestModel(String modelPath) {
			modelResource =  rs.getResource(modelPath);
			inputModel = modelResource.getContents().get(0);		
			unitApp.setEGraph(new EGraphImpl(inputModel));
		}
}
