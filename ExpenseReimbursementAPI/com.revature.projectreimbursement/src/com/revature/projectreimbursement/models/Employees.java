package com.revature.projectreimbursement.models;

/**It would have been useful to develop employees first and then make tickets 
 * as lists for each employee.  Alternatively, can filter tickets by employee.
 * @author MyCompWF
 *
 */
public abstract class Employees {
	
	protected String fName;
	protected String lName;
	protected int employeeId;
	
	
	public Employees(String fName, String lName, int employeeId) {
		
		this.fName = fName;
		this.lName = lName;
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


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	@Override
	public String toString() {
		return "Employees [fName=" + fName + ", lName=" + lName + ", employeeId=" + employeeId + "]";
	}
	
	
	
	

}
