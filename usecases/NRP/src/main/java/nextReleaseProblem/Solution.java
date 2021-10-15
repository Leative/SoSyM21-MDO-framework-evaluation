/**
 */
package nextReleaseProblem;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Solution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link nextReleaseProblem.Solution#getSelectedArtifacts <em>Selected Artifacts</em>}</li>
 *   <li>{@link nextReleaseProblem.Solution#getTotalCosts <em>Total Costs</em>}</li>
 * </ul>
 *
 * @see nextReleaseProblem.NextReleaseProblemPackage#getSolution()
 * @model
 * @generated
 */
public interface Solution extends EObject {
	/**
	 * Returns the value of the '<em><b>Selected Artifacts</b></em>' reference list.
	 * The list contents are of type {@link nextReleaseProblem.SoftwareArtifact}.
	 * It is bidirectional and its opposite is '{@link nextReleaseProblem.SoftwareArtifact#getSolutions <em>Solutions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Selected Artifacts</em>' reference list.
	 * @see nextReleaseProblem.NextReleaseProblemPackage#getSolution_SelectedArtifacts()
	 * @see nextReleaseProblem.SoftwareArtifact#getSolutions
	 * @model opposite="solutions"
	 * @generated
	 */
	EList<SoftwareArtifact> getSelectedArtifacts();

	/**
	 * Returns the value of the '<em><b>Total Costs</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Total Costs</em>' attribute.
	 * @see #setTotalCosts(double)
	 * @see nextReleaseProblem.NextReleaseProblemPackage#getSolution_TotalCosts()
	 * @model default="0" required="true"
	 * @generated
	 */
	double getTotalCosts();

	/**
	 * Sets the value of the '{@link nextReleaseProblem.Solution#getTotalCosts <em>Total Costs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Total Costs</em>' attribute.
	 * @see #getTotalCosts()
	 * @generated
	 */
	void setTotalCosts(double value);

} // Solution
