package com.revature.projectreimbursement.dl;

import java.util.List;

import com.revature.projectreimbursement.models.Ticket;
import com.revature.projectreimbursement.ui.MainMenu.Status;

public interface IRepository {
	//Here I define the methods necessary for interaction of of DL w/ the BL
	
	//Ill need a method to add a ticket to the list of tickets
	public void addTicket(Ticket ticket);
	
	//So, I'll need to have a list of tickets for storage, and I'll need to be able 
	//to get that list in order to add to it or to view it
	List<Ticket>getTickets();
	
	//want to now number of tickets in my list
	int sizeOfTicketList();
	
	void updateTicket(int idToUpdate, Status newStatus);

}
