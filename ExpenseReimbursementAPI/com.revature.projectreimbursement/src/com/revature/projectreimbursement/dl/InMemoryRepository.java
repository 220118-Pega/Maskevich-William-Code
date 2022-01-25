package com.revature.projectreimbursement.dl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.revature.projectreimbursement.models.Ticket;
import com.revature.projectreimbursement.ui.MainMenu.Status;
import com.revature.projectreimbursement.ui.MainMenu.Type;

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
		
		listOfTickets = new ArrayList<Ticket>() {{
			add(new Ticket(0, "William", "Me", 0, "Sample Title1A", sampleType, sampleStatus, 123, "Sample description 1A", null));
			add(new Ticket(1, "Bill", "Me", 0, "Sample Title1B", sampleType, sampleStatus, 456, "Sample description 1B", null));
			add(new Ticket(2, "Billy", "Me", 0, "Sample Title1C", sampleType, sampleStatus, 789, "Sample description 1C", null));
			add(new Ticket(3, "Will", "Me", 1, "Sample Title2", sampleType, sampleStatus, 643, "Sample description 2", null));
			add(new Ticket(4, "Willy", "Me", 2, "Sample Title3", sampleType, sampleStatus, 787, "Sample description 3", null));
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

	@Override
	public void updateTicket(int idToUpdate, Status newStatus) {
		// TODO Auto-generated method stub
		for(Ticket ticket : getTickets()) {
			if(idToUpdate == ticket.getId()) {
				ticket.setStatus(newStatus);
			}
		}
	}

	@Override
	public List<Ticket> getTickets() {
		//
		return this.listOfTickets;
	}
	
	@Override
	public int sizeOfTicketList() {
		// TODO Auto-generated method stub
		
		int size = listOfTickets.size();
		
		return size;
	}

	
	

}
