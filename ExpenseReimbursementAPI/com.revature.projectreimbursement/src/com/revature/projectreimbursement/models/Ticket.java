package com.revature.projectreimbursement.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.projectreimbursement.ui.MainMenu.Status;
import com.revature.projectreimbursement.ui.MainMenu.Type;

public class Ticket {
	
	private int id; //this is the ticket ID
	private String fName;
	private String lName;
	private int employeeId;
	private String title;
	private Type type;
	private Status status;
	private long amount;
	private String description;
	private LocalDateTime myTimeStamp;

	/*
	 * In the getters/ setters:
	 * 
	 * id should be automatically set to the next index
	 * Time stamp should be automatically generated
	 * 
	 * the amount needs to be converted from String input into a long numerical value
	 * 
	 * program automatically set lName to Smith; but for project its not really  needed at this stage

	*/
	public Ticket() { }
	
	public Ticket(String fName, String lName, int employeeId, String title, Type type, Status status, 
			long amount, String description ) {

		this.fName = fName;
		this.lName = lName;
		this.employeeId = employeeId;
		this.title = title;
		this.type = type;
		this.status = status;
		this.amount = amount;
		this.description = description;

	}
	
	
	public Ticket(int id, String fName, String lName, int employeeId, String title, Type type, Status status, 
			long amount, String description, LocalDateTime myTimeStamp  ) {
		
		this(fName, lName, employeeId, title, type, status, amount, description);
		
		this.id = id;
		this.myTimeStamp = myTimeStamp;

			
	}
	
//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		this.lName = "Smith";
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getMyTimeStamp() {
		return myTimeStamp;
	}

	public void setMyTimeStamp(LocalDateTime myTimeStamp) {
		this.myTimeStamp = myTimeStamp;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", fName=" + fName + ", lName=" + lName + ", employeeId=" + employeeId + ", title="
				+ title + ", type=" + type + ", status=" + status + ", amount=" + amount + ", description="
				+ description + ", myTimeStamp=" + myTimeStamp + "]";
	}


	
}
