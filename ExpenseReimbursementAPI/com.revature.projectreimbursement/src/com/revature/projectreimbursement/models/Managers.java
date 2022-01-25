package com.revature.projectreimbursement.models;

/** While a list of managers would have an explicit list of those able to
 * change status of a ticket, can for this project just achieve that with a
 * menu question of yes or no.
 * @author MyCompWF
 *
 */
public class Managers extends Employees{

	
	
	private boolean mgrYesOrNo;
	
	public Managers(String fName, String lName, int employeeId) {
		super(fName, lName, employeeId);
		
	}
	
	public Managers(String fName, String lName, int employeeId, boolean mgrYesOrNo) {
		super(fName, lName, employeeId);
		this.mgrYesOrNo = mgrYesOrNo;
		
	}
	
	public boolean isMgrYesOrNo() {
		return mgrYesOrNo;
	}

	public void setMgrYesOrNo(boolean mgrYesOrNo) {
		this.mgrYesOrNo = mgrYesOrNo;
	}

	@Override
	public String toString() {
		return "Managers [fName=" + fName + ", lName=" + lName + ", employeeId=" + employeeId + ", mgrYesOrNo="
				+ mgrYesOrNo + "]";
	}
	
}
