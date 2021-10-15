/**
 */
package SprintPlanning;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Plan</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link SprintPlanning.Plan#getBacklog <em>Backlog</em>}</li>
 *   <li>{@link SprintPlanning.Plan#getMaxTeamVelocity <em>Max Team Velocity</em>}</li>
 *   <li>{@link SprintPlanning.Plan#getDerivedMinSprintCount <em>Derived Min Sprint Count</em>}</li>
 * </ul>
 *
 * @see SprintPlanning.SprintPlanningPackage#getPlan()
 * @model
 * @generated
 */
public interface Plan extends EObject {
	/**
	 * Returns the value of the '<em><b>Backlog</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Backlog</em>' containment reference.
	 * @see #setBacklog(Backlog)
	 * @see SprintPlanning.SprintPlanningPackage#getPlan_Backlog()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Backlog getBacklog();

	/**
	 * Sets the value of the '{@link SprintPlanning.Plan#getBacklog <em>Backlog</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Backlog</em>' containment reference.
	 * @see #getBacklog()
	 * @generated
	 */
	void setBacklog(Backlog value);

	/**
	 * Returns the value of the '<em><b>Max Team Velocity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Team Velocity</em>' attribute.
	 * @see #setMaxTeamVelocity(int)
	 * @see SprintPlanning.SprintPlanningPackage#getPlan_MaxTeamVelocity()
	 * @model required="true"
	 * @generated
	 */
	int getMaxTeamVelocity();

	/**
	 * Sets the value of the '{@link SprintPlanning.Plan#getMaxTeamVelocity <em>Max Team Velocity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Team Velocity</em>' attribute.
	 * @see #getMaxTeamVelocity()
	 * @generated
	 */
	void setMaxTeamVelocity(int value);

	/**
	 * Returns the value of the '<em><b>Derived Min Sprint Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Derived Min Sprint Count</em>' attribute.
	 * @see #setDerivedMinSprintCount(int)
	 * @see SprintPlanning.SprintPlanningPackage#getPlan_DerivedMinSprintCount()
	 * @model required="true" volatile="true" derived="true"
	 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot derivation='let totalEffort : ecore::EInt = self.backlog.workitems-&gt;collect(Effort)-&gt;sum(),\n\t\t\t\t\t\t dividedByMaxVelo : ecore::EInt = totalEffort.div(maxTeamVelocity)\n\t\t\t\t\t\tin \n\t\t\t\t\t\t\tif totalEffort.mod(maxTeamVelocity) &gt; 0\t\n\t\t\t\t\t\t\tthen dividedByMaxVelo + 1\n\t\t\t\t\t\t\telse dividedByMaxVelo\n\t\t\t\t\t\t\tendif'"
	 * @generated
	 */
	int getDerivedMinSprintCount();

	/**
	 * Sets the value of the '{@link SprintPlanning.Plan#getDerivedMinSprintCount <em>Derived Min Sprint Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Derived Min Sprint Count</em>' attribute.
	 * @see #getDerivedMinSprintCount()
	 * @generated
	 */
	void setDerivedMinSprintCount(int value);

} // Plan
