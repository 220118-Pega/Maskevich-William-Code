package com.revature.projectreimbursement.ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.projectreimbursement.bl.ITicketBL;
import com.revature.projectreimbursement.bl.TicketBL;
import com.revature.projectreimbursement.models.Ticket;
import com.revature.projectreimbursement.ui.MainMenu.Status;
import com.revature.projectreimbursement.ui.MainMenu.Type;

public class MainMenu {
	// We will instantiate a Scanner to obtain input and to display a menu!
	private Scanner myscanner;
	private TicketBL ticketBL;
	private final Logger logger = LogManager.getLogger(this.getClass());

	public enum Status {
		PENDING, APPROVED, REJECTED;
	}

	public enum Type {
		LODGING, TRAVEL, FOOD, OTHER;
	}

	public MainMenu(Scanner myscanner, TicketBL ticketBL) {
		this.myscanner = myscanner;
		this.ticketBL = ticketBL;
	}

	public void start() {
		boolean keepGoing = true;
		// These options are subject to change and update as program progresses
		do {
			System.out.println("Welcome to Expense Reimbursement Menu.");
			System.out.println("What would you like to do?");
			System.out.println("[0] Create a new ticket");
			System.out.println("[1] View or get the list of tickets");
			System.out.println("[2] View only your past tickets");
			System.out.println("[3] Access management system (for Finance Managers only)");
			System.out.println("[x] Exit");
			// Capture the user's menu selection
			String userInput = myscanner.nextLine();
			// Determine user's selection and then call or perform the appropriate action
			switch (userInput) {
			case "0":
				System.out.println("Creating a new ticket.");
				createTicket();
				break;
			case "1":
				System.out.println("Getting the list of all tickets.");
				getTickets();
				break;
			case "2":
				System.out.println("Getting the list of my tickets.");
				getMyTickets();
				break;
			case "3":
				System.out.println("Accessint the management system (Finance Managers only");
				manageTickets();
				break;
			case "x":
				System.out.println("Exiting the system.");
				keepGoing = false;
				System.exit(0);
				break;
			default:
				System.out.println("Sorry, wrong input. Please try again.");
				break;
			}
		} while (keepGoing);

	}

	private void createTicket() {
		// create a ticket
		System.out.println("Enter your first name:");
		String fName = myscanner.nextLine();
		System.out.println("Enter your last name:");
		String lName = myscanner.nextLine();
		System.out.println("Enter your employee ID:");
		System.out.println("Your Id is a number between 1 and 10");
		int employeeId = InputClass.getInputInt("Please enter your employee ID");
		System.out.println("Enter a title for your ticket:");
		String title = myscanner.nextLine();
		System.out.println("Enter the type of expense you seek reimbursement of:");
		Type type = getType();
		// Status is assigned as pending for a new ticket.
		Status status = Status.PENDING;
		// Here I am using nextLong()
		System.out.println("Enter the amount you are requesting:");
		double amount = InputClass.getInputDouble("Enter a numeric only");
		System.out.println("Enter a description of your reimbursement:");
		String description = myscanner.nextLine();
		LocalDate ticketTimeStamp = LocalDate.now();

		// new ticket instantiation:
		Ticket newTicket = new Ticket(employeeId, title, type, amount, description, ticketTimeStamp, status);
		// code to save this newly created ticket
		ticketBL.addTicket(newTicket);
	}

	private Type getType() {
		// method to present select-from list for the "Type" of expense
		Type theType = Type.OTHER;
		boolean keepGoing = true;
		do {
			for (Type myType : Type.values()) {
				System.out.println(" Enter " + myType.ordinal() + " for " + myType);
			}
			// Capture the user's menu selection
			int myType = InputClass.getInputInt("The ticket ID is a number, please try again.");
			// Determine user's selection and then assign the type of ticket.
	
			switch (myType) {
			case 0:
				theType = Type.LODGING;
				System.out.println("You chose 0 for Lodging.");
				keepGoing = false;
				break;
			case 1:
				theType = Type.TRAVEL;
				System.out.println("You chose 1 for Travel.");
				keepGoing = false;
				break;
			case 2:
				theType = Type.FOOD;
				System.out.println("You chose 2 for Food.");
				keepGoing = false;
				break;
			case 3:
				theType = Type.OTHER;
				System.out.println("You chose 3 for Other.");
				keepGoing = false;
				break;
			default:
				System.out.println("Sorry, wrong input. Please try again.");
				break;
			}
			//0myscanner.nextLine();
		} while (keepGoing);
		return theType;
	}

	private void getTickets() {
		// so Ive sought and obtained the list of tickets
		int anyIds = ticketBL.numberOfTickets();
		if (anyIds > 0) {
			for (Ticket ticket : ticketBL.getTickets())
				System.out.println(ticket);
		} else {
			System.out.println("There are no tickets");
		}
	}

	private void getMyTickets() {
// Ok, so filter the list of tickets by employeeID, but first obtain the employee ID!
		int tCounter = 0;
		System.out.println("Please enter your employeeID:");
		int myID = InputClass.getInputInt("Please enter a valid employee ID");
		if (ticketBL.numberOfTickets() > 0) {
			for (Ticket ticket : ticketBL.getTickets()) 
			{
				if (myID == ticket.getEmployeeId()) 
				{ // the ticket matches the employee ID
					System.out.println(ticket);
					tCounter++;
				}
			}
		} 
		if(tCounter ==0) {System.out.println("You currently do not have any tickets!");}
	}

	private void manageTickets() {
		// generally working but re-engineer it so that I return the ticket
		// number to calling meneu, then calling menu goes out to adjust ticket

		// so I found the ticket of interest, if it it exists,
		// so I have to now manage the ticket, but first
		// verify manager status

		int lastID = ticketBL.numberOfTickets();
		// what if there are no tickets?
		if (lastID == 0) {
			System.out.println("There are no tickets to manage.");
		} else {
			System.out.println("Which ticket would you like to manage?");
			int myID = InputClass.getInputInt("Please enter a valid numer ticket number");
			// cycle through tickets to find ticket of interest
			if (myID < lastID && myID > -1) {
				for (Ticket ticket : ticketBL.getTickets()) {
					if (myID == ticket.getId()) {
						System.out.println(ticket);
						Status mgrStatus = adjustTicket(myID);
						ticketBL.updateTicket(myID, mgrStatus);
					}
				}
			} else {// if ticket does not exist, prompt for ticket in proper range
				System.out.println("That ticket number does not exist;");
				System.out.println("enter a number from 0 to " + (lastID - 1));
				manageTickets();
			}
		}
	}

	private Status adjustTicket(int myID) {
		// Here the mgr can approve the ticket or not
		// Should there be a "comment" field?
		Status theStatus = Status.PENDING;
		// Status is an enum represnting the allowable values for ticket status.
		boolean keepGoing = true;
		do {
			for (Status thestatus : Status.values()) {
				System.out.println(" To update the status of this ticket: ");
				System.out.println(" enter " + thestatus.ordinal() + " for " + thestatus);
			}
			// Capture the user's menu selection
			int getStatusOrd = InputClass.getInputInt("Wrong input, please try again.");
			//myscanner.nextLine();
			// Determine user's selection and assign the action to the ticket
			switch (getStatusOrd) {
			case 0:
				theStatus = Status.PENDING;
				System.out.println("You chose 0 for PENDING.");
				keepGoing = false;
				break;
			case 1:
				theStatus = Status.APPROVED;
				System.out.println("You chose 1 for Approved.");
				keepGoing = false;
				break;
			case 2:
				theStatus = Status.REJECTED;
				System.out.println("You chose 2 for REJECTED.");
				keepGoing = false;
				break;
			default:
				System.out.println("Sorry, wrong input. Please try again.");
				break;
			}
		} while (keepGoing);
		return theStatus;
	}

//end of class bracket follow!!	
}
