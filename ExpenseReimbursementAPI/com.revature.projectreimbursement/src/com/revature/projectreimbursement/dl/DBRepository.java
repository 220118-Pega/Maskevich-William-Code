package com.revature.projectreimbursement.dl;

import java.time.LocalDate;
import java.util.List;

import com.revature.projectreimbursement.models.Ticket;
import com.revature.projectreimbursement.ui.InputClass.Status;

public class DBRepository implements IRepository{
	
	//This is where I will declare my list of tickets,
	//and make that list available througout the program
	private IDAO<Ticket, Integer> ticketDAO;
	
	public DBRepository(IDAO<Ticket, Integer> ticketDAO) {
		 this.ticketDAO=ticketDAO;
		
	}
	
	public Ticket findById(Integer id) {
		return ticketDAO.findById(id);
		
	}
	



	@Override
	public void addTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		ticketDAO.add(ticket);
		

	}

	@Override
	public List<Ticket> getTickets() {
		// TODO Auto-generated method stub
		return ticketDAO.getTickets();
	}

	@Override
	public int sizeOfTicketList() {
		// TODO Auto-generated method stub
		return 0;
		//return issueDAO.sizeOfTicketList();
	}

	@Override
	public void updateTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		ticketDAO.update(ticket);
	}
	
	

}
