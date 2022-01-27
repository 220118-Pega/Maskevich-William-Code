package com.revature.projectreimbursement.ui;

import java.time.LocalDateTime;
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
		LODGING, TRAVEL, FOOD, OTHER
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
			/*
			 * case "n": System.out.println("Creating a new ticket."); //Ticket newTicket =
			 * new Ticket(); //used for progress test //tets as well:
			 * //System.out.println(newTicket); createTicket(); break;
			 */	
			case "x":
				System.out.println("Exiting the system.");
				keepGoing = false;
				System.exit(0);
				//break;
			default:
				System.out.println("Sorry, wrong input. Please try again.");
				break;
			}
		} while (keepGoing);
		
	}

	private void manageTickets() {
		// TODO Auto-generated method stub

		System.out.println("Which ticket would you like to manage?");
		int myID = Integer.parseInt(myscanner.nextLine());
		int lastID = ticketBL.numberOfTickets();
		//  cycle through tickets to find ticket of interest
		if (myID < lastID) {
			for (Ticket ticket : ticketBL.getTickets()) {
				if (myID == ticket.getId()) {
					System.out.println(ticket);
					// so I found the ticket of interest, it exists, so I have assign new status.					
					Status mgrStatus = adjustTicket(myID);
					//now , the ticket already exists, so send it over for updating.
					ticketBL.updateTicket(myID, mgrStatus);
				}
			}
		} else {//if ticket does not exist, prompt for ticket in proper range
			System.out.println("That ticket number does not exist;");
			System.out.println("enter a number between 0 and " + lastID);
			manageTickets();
		}
		// so I found the ticket of interest, it exists, so I have to now manage the ticket.
		//also go back and verify manager status

	}

	private Status adjustTicket(int myID) {
		// Here the mgr can approve the ticket or not
		//Should there be a "comment" field?
		//copy the menu for status and adjust it to reflect desired option (ie, change Type
		// method to present select-from list for the "Type" of expense
		
		Status theStatus = Status.PENDING;
		boolean keepGoing = true;
		//in the future, I want to incorporate this method (to get an enum value) in the declared enum!x
		

		do {
			for (Status thestatus : Status.values()) {
				System.out.println(" To update the status of this ticket: " );
				System.out.println(" enter " + thestatus.ordinal() + " for " + thestatus);
			}
			// Capture the user's menu selection
			int getStatusOrd = myscanner.nextInt();
			myscanner.nextLine();
			// Determine user's selection and then call or perform the appropriate action
			switch (getStatusOrd) {
			case 0:
				theStatus = Status.PENDING;
				System.out.println("You chose 0 for PENDING.");
				keepGoing = false;
				break;
				//return (Type.valueOf("LODGING"));
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

	private void getMyTickets() {
		// Ok, so filter the list of tickets by employeeID
		//but first obtain the employee ID!
		System.out.println("Please enter your employeeID:");
		int myID = Integer.parseInt(myscanner.nextLine());
		for (Ticket ticket : ticketBL.getTickets()) {
			if(myID == ticket.getEmployeeId())	{	//the ticket matches the employee ID
				System.out.println(ticket);
			}
		}
	}

	private void getTickets() {
		//
		for (Ticket ticket : ticketBL.getTickets()) {
			System.out.println(ticket);
		}
	}

	private void createTicket() {
		// create a ticket


		//this set amount to $123L, fix actual conversion to long later
		//need to fix the date,time stamp
		System.out.println("Enter your first name:");
		String fName = myscanner.nextLine();
		System.out.println("Enter your last name:");
		String lName = myscanner.nextLine();
		//I used nextInt() similar to Long for amount below
		//but I'm going to try and parse the string for an integer
		System.out.println("Enter your employee ID:");
		System.out.println("Your Id is a number between 1 and 10");
		int employeeId = Integer.parseInt(myscanner.nextLine());
/*		int employeeId = myscanner.nextInt();
		myscanner.nextLine();
*/
		System.out.println("Enter a title for your ticket:");
		String title = myscanner.next();
		System.out.println("Enter the type of expense you seek reimbursement of:");
		Type type = getType();
		//Status is assigned as pending for a new ticket.
		Status status = Status.PENDING;
		//Here I am using nextLong() see int employeeId above
		System.out.println("Enter the amount you are requesting:");
		long amount = myscanner.nextLong();
		myscanner.nextLine();
		System.out.println("Enter a description of your reimbursement:");
		String description = myscanner.nextLine();
		description = "this is my description";
		
		//LocalDateTime myTimeStamp = LocalDateTime;
		
		//new ticket instantiated
		Ticket newTicket = new Ticket(fName,lName, employeeId, title, type, status, amount, description);

		//test print of newTicket, which data made it into the ticket	
		//System.out.println(newTicket);
		// code to save this newly created ticket
		ticketBL.addTicket(newTicket);

	}

	private Type getType() {
		// method to present select-from list for the "Type" of expense
		Type theType = Type.OTHER;
		boolean keepGoing = true;
		//in the future, I want to incorporate this method (to get an enum value) in the declared enum!x
		

		do {
			for (Type myType : Type.values()) {
				System.out.println(" Enter " + myType.ordinal() + " for " + myType);
			}
						// Capture the user's menu selection
			int myType = myscanner.nextInt();
			// Determine user's selection and then call or perform the appropriate action
			switch (myType) {
			case 0:
				theType = Type.LODGING;
				System.out.println("You chose 0 for Lodging.");
				keepGoing = false;
				break;
				//return (Type.valueOf("LODGING"));
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
		} while (keepGoing);
		
		return	theType; //valueOf("OTHER");

	}
	
//end of class bracket follow!!	
}
