package com.revature.projectreimbursement.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.projectreimbursement.ui.MainMenu.Status;
import com.revature.projectreimbursement.ui.MainMenu.Type;

public class Ticket {
	//rearrange to meet db table layout merely for convenience
	private int id; //this is the ticket ID
	private int employeeId;
	private String title;
	private Type type;
	private double amount;
	private String description;
	//there is time stamp when ticket is created
	private LocalDate ticketTimeStamp;
	private Status status;
	// and there is time stamp when status is updated
	private LocalDate statusTimeStamp;		


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
	
	public Ticket(int employeeId,String title,Type type,double amount,String description,
			LocalDate ticketTimeStamp,Status status){

		this.employeeId = employeeId;
		this.title = title;
		this.type = type;
		this.amount = amount;
		this.description = description;
		this.status = status;
		this.ticketTimeStamp = ticketTimeStamp;
	}
	
	public Ticket(int id, int employeeId, String title, Type type, double amount, String description, 
			LocalDate ticketTimeStamp, Status status, LocalDate statusTimeStamp) {
		
		this(employeeId,title,type,amount,description,ticketTimeStamp, status);
		this.id = id;
		this.statusTimeStamp = statusTimeStamp;
	}
//Getters and Setters
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTicketTimeStamp() {
		return ticketTimeStamp;
	}

	public void setTicketTimeStamp(LocalDate ticketTimeStamp) {
		this.ticketTimeStamp = ticketTimeStamp;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDate getStatusTimeStamp() {
		return statusTimeStamp;
	}

	public void setStatusTimeStamp(LocalDate statusTimeStamp) {
		this.statusTimeStamp = statusTimeStamp;
	}
	
	//tostring format
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", employeeId=" + employeeId + ", title=" + title + ", type=" + type + ", amount="
				+ amount + ", description=" + description + ", ticketTimeStamp=" + ticketTimeStamp + ", status="
				+ status + ", statusTimeStamp=" + statusTimeStamp + "]";
	}
	
// end of class	
}
