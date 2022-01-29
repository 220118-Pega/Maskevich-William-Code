package com.revature.projectreimbursement.models;

/**It would have been useful to develop employees first and then make tickets 
 * as lists for each employee.  Alternatively, can filter tickets by employee.
 * @author MyCompWF
 *
 */
public  class Employees {
	
	private int id;
	private int employeeId;
	private String fName;
	private String lName;
	private int mgr_assigned;

	
	public Employees() {
	}
	
	public Employees( int employeeId, String fName) {
		this.employeeId = employeeId;
		this.fName = fName;
	}
	
	public Employees( int employeeId, String fName, String lName) {
		this(employeeId, fName); 
		this.lName = lName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public int getMgr_assigned() {
		return mgr_assigned;
	}

	public void setMgr_assigned(int mgr_assigned) {
		this.mgr_assigned = mgr_assigned;
	}

	@Override
	public String toString() {
		return "Employees [id=" + id + ", employeeId=" + employeeId + ", fName=" + fName + ", lName=" + lName
				+ ", mgr_assigned=" + mgr_assigned + "]";
	}
	
	



	
	
	

}
