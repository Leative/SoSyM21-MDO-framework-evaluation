/**
 */
package SprintPlanning.impl;

import SprintPlanning.Backlog;
import SprintPlanning.Plan;
import SprintPlanning.SprintPlanningPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Plan</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link SprintPlanning.impl.PlanImpl#getBacklog <em>Backlog</em>}</li>
 *   <li>{@link SprintPlanning.impl.PlanImpl#getMaxTeamVelocity <em>Max Team Velocity</em>}</li>
 *   <li>{@link SprintPlanning.impl.PlanImpl#getDerivedMinSprintCount <em>Derived Min Sprint Count</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PlanImpl extends MinimalEObjectImpl.Container implements Plan {
	/**
	 * The cached value of the '{@link #getBacklog() <em>Backlog</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBacklog()
	 * @generated
	 * @ordered
	 */
	protected Backlog backlog;

	/**
	 * The default value of the '{@link #getMaxTeamVelocity() <em>Max Team Velocity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxTeamVelocity()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_TEAM_VELOCITY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaxTeamVelocity() <em>Max Team Velocity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxTeamVelocity()
	 * @generated
	 * @ordered
	 */
	protected int maxTeamVelocity = MAX_TEAM_VELOCITY_EDEFAULT;

	/**
	 * The cached setting delegate for the '{@link #getDerivedMinSprintCount() <em>Derived Min Sprint Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDerivedMinSprintCount()
	 * @generated
	 * @ordered
	 */
	protected EStructuralFeature.Internal.SettingDelegate DERIVED_MIN_SPRINT_COUNT__ESETTING_DELEGATE = ((EStructuralFeature.Internal)SprintPlanningPackage.Literals.PLAN__DERIVED_MIN_SPRINT_COUNT).getSettingDelegate();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PlanImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SprintPlanningPackage.Literals.PLAN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Backlog getBacklog() {
		return backlog;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBacklog(Backlog newBacklog, NotificationChain msgs) {
		Backlog oldBacklog = backlog;
		backlog = newBacklog;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SprintPlanningPackage.PLAN__BACKLOG, oldBacklog, newBacklog);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBacklog(Backlog newBacklog) {
		if (newBacklog != backlog) {
			NotificationChain msgs = null;
			if (backlog != null)
				msgs = ((InternalEObject)backlog).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SprintPlanningPackage.PLAN__BACKLOG, null, msgs);
			if (newBacklog != null)
				msgs = ((InternalEObject)newBacklog).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SprintPlanningPackage.PLAN__BACKLOG, null, msgs);
			msgs = basicSetBacklog(newBacklog, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SprintPlanningPackage.PLAN__BACKLOG, newBacklog, newBacklog));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaxTeamVelocity() {
		return maxTeamVelocity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxTeamVelocity(int newMaxTeamVelocity) {
		int oldMaxTeamVelocity = maxTeamVelocity;
		maxTeamVelocity = newMaxTeamVelocity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SprintPlanningPackage.PLAN__MAX_TEAM_VELOCITY, oldMaxTeamVelocity, maxTeamVelocity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDerivedMinSprintCount() {
		return (Integer)DERIVED_MIN_SPRINT_COUNT__ESETTING_DELEGATE.dynamicGet(this, null, 0, true, false);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDerivedMinSprintCount(int newDerivedMinSprintCount) {
		DERIVED_MIN_SPRINT_COUNT__ESETTING_DELEGATE.dynamicSet(this, null, 0, newDerivedMinSprintCount);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SprintPlanningPackage.PLAN__BACKLOG:
				return basicSetBacklog(null, msgs);
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
			case SprintPlanningPackage.PLAN__BACKLOG:
				return getBacklog();
			case SprintPlanningPackage.PLAN__MAX_TEAM_VELOCITY:
				return getMaxTeamVelocity();
			case SprintPlanningPackage.PLAN__DERIVED_MIN_SPRINT_COUNT:
				return getDerivedMinSprintCount();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SprintPlanningPackage.PLAN__BACKLOG:
				setBacklog((Backlog)newValue);
				return;
			case SprintPlanningPackage.PLAN__MAX_TEAM_VELOCITY:
				setMaxTeamVelocity((Integer)newValue);
				return;
			case SprintPlanningPackage.PLAN__DERIVED_MIN_SPRINT_COUNT:
				setDerivedMinSprintCount((Integer)newValue);
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
			case SprintPlanningPackage.PLAN__BACKLOG:
				setBacklog((Backlog)null);
				return;
			case SprintPlanningPackage.PLAN__MAX_TEAM_VELOCITY:
				setMaxTeamVelocity(MAX_TEAM_VELOCITY_EDEFAULT);
				return;
			case SprintPlanningPackage.PLAN__DERIVED_MIN_SPRINT_COUNT:
				DERIVED_MIN_SPRINT_COUNT__ESETTING_DELEGATE.dynamicUnset(this, null, 0);
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
			case SprintPlanningPackage.PLAN__BACKLOG:
				return backlog != null;
			case SprintPlanningPackage.PLAN__MAX_TEAM_VELOCITY:
				return maxTeamVelocity != MAX_TEAM_VELOCITY_EDEFAULT;
			case SprintPlanningPackage.PLAN__DERIVED_MIN_SPRINT_COUNT:
				return DERIVED_MIN_SPRINT_COUNT__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);
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
		result.append(" (maxTeamVelocity: ");
		result.append(maxTeamVelocity);
		result.append(')');
		return result.toString();
	}

} //PlanImpl
