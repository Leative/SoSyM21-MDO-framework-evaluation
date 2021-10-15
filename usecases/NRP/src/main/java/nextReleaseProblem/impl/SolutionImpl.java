/**
 */
package nextReleaseProblem.impl;

import java.util.Collection;

import nextReleaseProblem.NextReleaseProblemPackage;
import nextReleaseProblem.SoftwareArtifact;
import nextReleaseProblem.Solution;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Solution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link nextReleaseProblem.impl.SolutionImpl#getSelectedArtifacts <em>Selected Artifacts</em>}</li>
 *   <li>{@link nextReleaseProblem.impl.SolutionImpl#getTotalCosts <em>Total Costs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SolutionImpl extends MinimalEObjectImpl.Container implements Solution {
	/**
	 * The cached value of the '{@link #getSelectedArtifacts() <em>Selected Artifacts</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSelectedArtifacts()
	 * @generated
	 * @ordered
	 */
	protected EList<SoftwareArtifact> selectedArtifacts;

	/**
	 * The default value of the '{@link #getTotalCosts() <em>Total Costs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalCosts()
	 * @generated
	 * @ordered
	 */
	protected static final double TOTAL_COSTS_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getTotalCosts() <em>Total Costs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalCosts()
	 * @generated
	 * @ordered
	 */
	protected double totalCosts = TOTAL_COSTS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SolutionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NextReleaseProblemPackage.Literals.SOLUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SoftwareArtifact> getSelectedArtifacts() {
		if (selectedArtifacts == null) {
			selectedArtifacts = new EObjectWithInverseResolvingEList.ManyInverse<SoftwareArtifact>(SoftwareArtifact.class, this, NextReleaseProblemPackage.SOLUTION__SELECTED_ARTIFACTS, NextReleaseProblemPackage.SOFTWARE_ARTIFACT__SOLUTIONS);
		}
		return selectedArtifacts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getTotalCosts() {
		return totalCosts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTotalCosts(double newTotalCosts) {
		double oldTotalCosts = totalCosts;
		totalCosts = newTotalCosts;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NextReleaseProblemPackage.SOLUTION__TOTAL_COSTS, oldTotalCosts, totalCosts));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NextReleaseProblemPackage.SOLUTION__SELECTED_ARTIFACTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSelectedArtifacts()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NextReleaseProblemPackage.SOLUTION__SELECTED_ARTIFACTS:
				return ((InternalEList<?>)getSelectedArtifacts()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NextReleaseProblemPackage.SOLUTION__SELECTED_ARTIFACTS:
				return getSelectedArtifacts();
			case NextReleaseProblemPackage.SOLUTION__TOTAL_COSTS:
				return getTotalCosts();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case NextReleaseProblemPackage.SOLUTION__SELECTED_ARTIFACTS:
				getSelectedArtifacts().clear();
				getSelectedArtifacts().addAll((Collection<? extends SoftwareArtifact>)newValue);
				return;
			case NextReleaseProblemPackage.SOLUTION__TOTAL_COSTS:
				setTotalCosts((Double)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case NextReleaseProblemPackage.SOLUTION__SELECTED_ARTIFACTS:
				getSelectedArtifacts().clear();
				return;
			case NextReleaseProblemPackage.SOLUTION__TOTAL_COSTS:
				setTotalCosts(TOTAL_COSTS_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case NextReleaseProblemPackage.SOLUTION__SELECTED_ARTIFACTS:
				return selectedArtifacts != null && !selectedArtifacts.isEmpty();
			case NextReleaseProblemPackage.SOLUTION__TOTAL_COSTS:
				return totalCosts != TOTAL_COSTS_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (totalCosts: ");
		result.append(totalCosts);
		result.append(')');
		return result.toString();
	}

} //SolutionImpl
