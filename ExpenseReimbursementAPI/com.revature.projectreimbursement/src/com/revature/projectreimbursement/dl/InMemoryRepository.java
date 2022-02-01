package com.revature.projectreimbursement.dl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.revature.projectreimbursement.models.Ticket;
import com.revature.projectreimbursement.ui.InputClass.Status;
import com.revature.projectreimbursement.ui.InputClass.Type;

/**
 * This DL class is a Repository that connects to 
 * 		in-memory storage, i.e, a static list of tickets
 * @author MyCompWF
 *
 */

public class InMemoryRepository implements IRepository{
	
	//This is where I will declare my list of tickets,
	//and make that list available througout the program
	private static List<Ticket>listOfTickets;
	
	//and to resolve issue for auto generation if ids, 
	private static int latestId;
	
	//constructor:
	public InMemoryRepository() {
		//I will set my initial value of latestId to Zero as no tickets are defined.

		//its going to be cumbersome to have to keep adding tickets to test other functions/menu items
		//So, I'm seeding my list of tickets
		Type sampleType = Type.OTHER;
		Status sampleStatus = Status.PENDING;
		latestId = 0;
		//add for no initialization

		listOfTickets = new ArrayList<Ticket>() {{
			add(new Ticket(0,0, "Sample Title1A", Type.OTHER, 123, "Sample description 1A", null, Status.PENDING,null));
			add(new Ticket(1,0, "Sample Title1B", Type.OTHER,  456, "Sample description 1B", null, Status.PENDING, null));
			add(new Ticket(2,0, "Sample Title1C", Type.OTHER,  789, "Sample description 1C", null, Status.PENDING, null));
			add(new Ticket(3,1, "Sample Title2", Type.OTHER,  643, "Sample description 2", null, Status.PENDING, null));
			add(new Ticket(4,2, "Sample Title3", Type.OTHER,  787, "Sample description 3", null, Status.PENDING, null));
		}};
		latestId = 5;
	}

	//Methods: to add a ticket to and to view the list of tickets
	@Override
	public void addTicket(Ticket newTicket) {
		// to add a ticket to storage we have to assign an ID to the record/object ticket
		newTicket.setId(latestId);
		listOfTickets.add(newTicket);
		latestId++;
	}

	public void updateTicket(int idToUpdate, Status newStatus, LocalDate statusTimeStamp) {
		// TODO Auto-generated method stub
		for(Ticket ticket : getTickets()) {
			if(idToUpdate == ticket.getId()) {
				ticket.setStatus(newStatus);
				ticket.setStatusTimeStamp(LocalDate.now());
			}
		}
	}

	@Override
	public List<Ticket> getTickets() {
		return this.listOfTickets;
	}
	
	@Override
	public int sizeOfTicketList() {
		// TODO Auto-generated method stub
		int size = listOfTickets.size();
		return size;
	}

	@Override
	public void updateTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ticket findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
