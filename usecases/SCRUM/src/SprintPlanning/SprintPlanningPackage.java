/**
 */
package SprintPlanning;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see SprintPlanning.SprintPlanningFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/OCL/Import ecore='http://www.eclipse.org/emf/2002/Ecore'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore invocationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot' settingDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot' validationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot'"
 * @generated
 */
public interface SprintPlanningPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "SprintPlanning";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.kcl.ac.uk/mdeoptimiser/sprint-planning";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "planning";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SprintPlanningPackage eINSTANCE = SprintPlanning.impl.SprintPlanningPackageImpl.init();

	/**
	 * The meta object id for the '{@link SprintPlanning.impl.BacklogImpl <em>Backlog</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see SprintPlanning.impl.BacklogImpl
	 * @see SprintPlanning.impl.SprintPlanningPackageImpl#getBacklog()
	 * @generated
	 */
	int BACKLOG = 0;

	/**
	 * The feature id for the '<em><b>Workitems</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BACKLOG__WORKITEMS = 0;

	/**
	 * The number of structural features of the '<em>Backlog</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BACKLOG_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Backlog</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BACKLOG_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link SprintPlanning.impl.WorkItemImpl <em>Work Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see SprintPlanning.impl.WorkItemImpl
	 * @see SprintPlanning.impl.SprintPlanningPackageImpl#getWorkItem()
	 * @generated
	 */
	int WORK_ITEM = 1;

	/**
	 * The feature id for the '<em><b>Effort</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ITEM__EFFORT = 0;

	/**
	 * The number of structural features of the '<em>Work Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ITEM_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Work Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ITEM_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link SprintPlanning.impl.PlanImpl <em>Plan</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see SprintPlanning.impl.PlanImpl
	 * @see SprintPlanning.impl.SprintPlanningPackageImpl#getPlan()
	 * @generated
	 */
	int PLAN = 2;

	/**
	 * The feature id for the '<em><b>Backlog</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN__BACKLOG = 0;

	/**
	 * The feature id for the '<em><b>Max Team Velocity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN__MAX_TEAM_VELOCITY = 1;

	/**
	 * The feature id for the '<em><b>Derived Min Sprint Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN__DERIVED_MIN_SPRINT_COUNT = 2;

	/**
	 * The number of structural features of the '<em>Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link SprintPlanning.Backlog <em>Backlog</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Backlog</em>'.
	 * @see SprintPlanning.Backlog
	 * @generated
	 */
	EClass getBacklog();

	/**
	 * Returns the meta object for the containment reference list '{@link SprintPlanning.Backlog#getWorkitems <em>Workitems</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Workitems</em>'.
	 * @see SprintPlanning.Backlog#getWorkitems()
	 * @see #getBacklog()
	 * @generated
	 */
	EReference getBacklog_Workitems();

	/**
	 * Returns the meta object for class '{@link SprintPlanning.WorkItem <em>Work Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Item</em>'.
	 * @see SprintPlanning.WorkItem
	 * @generated
	 */
	EClass getWorkItem();

	/**
	 * Returns the meta object for the attribute '{@link SprintPlanning.WorkItem#getEffort <em>Effort</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Effort</em>'.
	 * @see SprintPlanning.WorkItem#getEffort()
	 * @see #getWorkItem()
	 * @generated
	 */
	EAttribute getWorkItem_Effort();

	/**
	 * Returns the meta object for class '{@link SprintPlanning.Plan <em>Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Plan</em>'.
	 * @see SprintPlanning.Plan
	 * @generated
	 */
	EClass getPlan();

	/**
	 * Returns the meta object for the containment reference '{@link SprintPlanning.Plan#getBacklog <em>Backlog</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Backlog</em>'.
	 * @see SprintPlanning.Plan#getBacklog()
	 * @see #getPlan()
	 * @generated
	 */
	EReference getPlan_Backlog();

	/**
	 * Returns the meta object for the attribute '{@link SprintPlanning.Plan#getMaxTeamVelocity <em>Max Team Velocity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Team Velocity</em>'.
	 * @see SprintPlanning.Plan#getMaxTeamVelocity()
	 * @see #getPlan()
	 * @generated
	 */
	EAttribute getPlan_MaxTeamVelocity();

	/**
	 * Returns the meta object for the attribute '{@link SprintPlanning.Plan#getDerivedMinSprintCount <em>Derived Min Sprint Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Derived Min Sprint Count</em>'.
	 * @see SprintPlanning.Plan#getDerivedMinSprintCount()
	 * @see #getPlan()
	 * @generated
	 */
	EAttribute getPlan_DerivedMinSprintCount();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SprintPlanningFactory getSprintPlanningFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link SprintPlanning.impl.BacklogImpl <em>Backlog</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see SprintPlanning.impl.BacklogImpl
		 * @see SprintPlanning.impl.SprintPlanningPackageImpl#getBacklog()
		 * @generated
		 */
		EClass BACKLOG = eINSTANCE.getBacklog();

		/**
		 * The meta object literal for the '<em><b>Workitems</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BACKLOG__WORKITEMS = eINSTANCE.getBacklog_Workitems();

		/**
		 * The meta object literal for the '{@link SprintPlanning.impl.WorkItemImpl <em>Work Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see SprintPlanning.impl.WorkItemImpl
		 * @see SprintPlanning.impl.SprintPlanningPackageImpl#getWorkItem()
		 * @generated
		 */
		EClass WORK_ITEM = eINSTANCE.getWorkItem();

		/**
		 * The meta object literal for the '<em><b>Effort</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_ITEM__EFFORT = eINSTANCE.getWorkItem_Effort();

		/**
		 * The meta object literal for the '{@link SprintPlanning.impl.PlanImpl <em>Plan</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see SprintPlanning.impl.PlanImpl
		 * @see SprintPlanning.impl.SprintPlanningPackageImpl#getPlan()
		 * @generated
		 */
		EClass PLAN = eINSTANCE.getPlan();

		/**
		 * The meta object literal for the '<em><b>Backlog</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLAN__BACKLOG = eINSTANCE.getPlan_Backlog();

		/**
		 * The meta object literal for the '<em><b>Max Team Velocity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLAN__MAX_TEAM_VELOCITY = eINSTANCE.getPlan_MaxTeamVelocity();

		/**
		 * The meta object literal for the '<em><b>Derived Min Sprint Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLAN__DERIVED_MIN_SPRINT_COUNT = eINSTANCE.getPlan_DerivedMinSprintCount();

	}

} //SprintPlanningPackage
