package com.revature.projectreimbursement.bl;

import java.time.LocalDate;
import java.util.List;

import com.revature.projectreimbursement.models.Ticket;
import com.revature.projectreimbursement.ui.InputClass.Status;


public interface ITicketBL {
	//Need this interface to allow interaction between layers, specifically
	//the BL will use this to interface as a go between gor interaction w/ the data layer
	
	
	//I have to be able to add a new ticket to the existing list of tickets
	void	addTicket(Ticket ticket);
	
	//I have to be able to get the list of tickets
	List<Ticket> getTickets();
	
	Ticket getMyTicket(Integer id);
	
	int numberOfTickets();
	
	void updateTicket(Ticket ticket);

}
