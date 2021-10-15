/**
 */
package nextReleaseProblem;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>NRP</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link nextReleaseProblem.NRP#getCustomers <em>Customers</em>}</li>
 *   <li>{@link nextReleaseProblem.NRP#getAvailableArtifacts <em>Available Artifacts</em>}</li>
 *   <li>{@link nextReleaseProblem.NRP#getRequirements <em>Requirements</em>}</li>
 *   <li>{@link nextReleaseProblem.NRP#getSolutions <em>Solutions</em>}</li>
 *   <li>{@link nextReleaseProblem.NRP#getBudget <em>Budget</em>}</li>
 * </ul>
 *
 * @see nextReleaseProblem.NextReleaseProblemPackage#getNRP()
 * @model
 * @generated
 */
public interface NRP extends EObject {
	/**
	 * Returns the value of the '<em><b>Customers</b></em>' containment reference list.
	 * The list contents are of type {@link nextReleaseProblem.Customer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Customers</em>' containment reference list.
	 * @see nextReleaseProblem.NextReleaseProblemPackage#getNRP_Customers()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Customer> getCustomers();

	/**
	 * Returns the value of the '<em><b>Available Artifacts</b></em>' containment reference list.
	 * The list contents are of type {@link nextReleaseProblem.SoftwareArtifact}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Available Artifacts</em>' containment reference list.
	 * @see nextReleaseProblem.NextReleaseProblemPackage#getNRP_AvailableArtifacts()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<SoftwareArtifact> getAvailableArtifacts();

	/**
	 * Returns the value of the '<em><b>Requirements</b></em>' containment reference list.
	 * The list contents are of type {@link nextReleaseProblem.Requirement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requirements</em>' containment reference list.
	 * @see nextReleaseProblem.NextReleaseProblemPackage#getNRP_Requirements()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Requirement> getRequirements();

	/**
	 * Returns the value of the '<em><b>Solutions</b></em>' containment reference list.
	 * The list contents are of type {@link nextReleaseProblem.Solution}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Solutions</em>' containment reference list.
	 * @see nextReleaseProblem.NextReleaseProblemPackage#getNRP_Solutions()
	 * @model containment="true"
	 * @generated
	 */
	EList<Solution> getSolutions();

	/**
	 * Returns the value of the '<em><b>Budget</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Budget</em>' attribute.
	 * @see #setBudget(double)
	 * @see nextReleaseProblem.NextReleaseProblemPackage#getNRP_Budget()
	 * @model required="true"
	 * @generated
	 */
	double getBudget();

	/**
	 * Sets the value of the '{@link nextReleaseProblem.NRP#getBudget <em>Budget</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Budget</em>' attribute.
	 * @see #getBudget()
	 * @generated
	 */
	void setBudget(double value);

} // NRP
