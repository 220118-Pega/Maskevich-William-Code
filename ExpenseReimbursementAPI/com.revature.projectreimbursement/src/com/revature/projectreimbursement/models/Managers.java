package com.revature.projectreimbursement.models;

/** While a list of managers would have an explicit list of those able to
 * change status of a ticket, can for this project just achieve that with a
 * menu question of yes or no.
 * @author MyCompWF
 *
 */
public class Managers {

	private int id;	
	private int mgr_id;	
	private String department;
	
	public Managers() {
		
	}
	
	public Managers(int mgr_id, String department) {
		
		this.mgr_id = mgr_id;
		this.department = department;
	}
	
	public Managers( int id, int mgr_id, String department) {
		
		this(mgr_id, department);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMgr_id() {
		return mgr_id;
	}

	public void setMgr_id(int mgr_id) {
		this.mgr_id = mgr_id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Managers [id=" + id + ", mgr_id=" + mgr_id + ", department=" + department + "]";
	}
	

}
