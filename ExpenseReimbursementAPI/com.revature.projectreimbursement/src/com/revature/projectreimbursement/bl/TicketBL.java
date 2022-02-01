package com.revature.projectreimbursement.bl;

import java.time.LocalDate;
import java.util.List;

import com.revature.projectreimbursement.dl.IRepository;
import com.revature.projectreimbursement.models.Ticket;
import com.revature.projectreimbursement.ui.InputClass.Status;


public class TicketBL implements ITicketBL {
//Another class, so this is a noun and it is a blueprint for what???For the list of tickets!!!
//What do I need my BL to do? I need to be able to add a newly created ticket to the existing list of tickets
						// I'll want to be able to view the exisiting list of tickets
						//Other stuff will be added later, but this is all for the UI options to date (create a new ticket)
	
	
	//so, I need to recognize and declare this class' dependecny on the DL
	private IRepository repo;
	
	//my constructor for this class:
	public TicketBL(IRepository repo) {
		
		this.repo = repo;
	}
	
	//So, I need my list of tickets (as a property), but that is done via declaring the IRepository datatype above
	
	//MEtods: get list of tickets and add to list of tickets - these are abstracted in the interface
	@Override
	public void addTicket(Ticket ticket) {
		// 
		repo.addTicket(ticket);		
	}

	@Override
	public void updateTicket(Ticket ticket) {
		// Ok, to update a ticket I need to know the ticket ID 
		// and I need to know what values changed.
		repo.updateTicket(ticket);

	}

	@Override
	public List<Ticket> getTickets() {
		// 
		return repo.getTickets();
	}

	@Override
	public int numberOfTickets() {
		//allows UI to access datalayer for number of tickets in the list of tickets
		int howMany = repo.sizeOfTicketList();

		return howMany;
	}

	@Override
	public Ticket getMyTicket(Integer ticket_id) {
		// TODO Auto-generated method stub
		return repo.findById(ticket_id);
	}


	
	
	
	

}
