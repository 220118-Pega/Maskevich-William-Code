package com.revature.projectreimbursement.models;

public class Associates extends Employees{

	public Associates(String fName, String lName, int employeeId) {
		super(fName, lName, employeeId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Employees [fName=" + fName + ", lName=" + lName + ", employeeId=" + employeeId + "]";
	}
	
	
}
