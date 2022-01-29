package com.revature.projectreimbursement.driver;

import java.util.Scanner;

import com.revature.projectreimbursement.bl.ITicketBL;
import com.revature.projectreimbursement.bl.TicketBL;
import com.revature.projectreimbursement.dl.DBRepository;
import com.revature.projectreimbursement.dl.InMemoryRepository;
import com.revature.projectreimbursement.models.Ticket;
import com.revature.projectreimbursement.ui.MainMenu;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Will need to access the MainMenu() class for file storage.
		MainMenu menu = new MainMenu(new Scanner(System.in), new TicketBL(new InMemoryRepository()));
		//But this option utilizes database storage:
		//MainMenu menu = new MainMenu(new Scanner(System.in), new TicketBL(new DBRepository()));
		
		//Now, call the start() method to begin the app.
		menu.start();

	}

}
