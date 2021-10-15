/**
 */
package SprintPlanning;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Work Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link SprintPlanning.WorkItem#getEffort <em>Effort</em>}</li>
 * </ul>
 *
 * @see SprintPlanning.SprintPlanningPackage#getWorkItem()
 * @model
 * @generated
 */
public interface WorkItem extends EObject {
	/**
	 * Returns the value of the '<em><b>Effort</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Effort</em>' attribute.
	 * @see #setEffort(int)
	 * @see SprintPlanning.SprintPlanningPackage#getWorkItem_Effort()
	 * @model required="true"
	 * @generated
	 */
	int getEffort();

	/**
	 * Sets the value of the '{@link SprintPlanning.WorkItem#getEffort <em>Effort</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Effort</em>' attribute.
	 * @see #getEffort()
	 * @generated
	 */
	void setEffort(int value);

} // WorkItem
