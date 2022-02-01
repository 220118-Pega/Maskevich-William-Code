package com.revature.projectreimbursement.dl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.projectreimbursement.models.Ticket;
import com.revature.projectreimbursement.ui.InputClass.Status;
import com.revature.projectreimbursement.ui.InputClass.Type;

/**
 * Our TicketDAO goes in the DL because, despite part of its name, it's used to
 * interact w/ the dB
 * 
 * @author WilliamMaskevich
 *
 */
public class TicketDAO implements IDAO {
	// So now, proceed with implementing each of the interface methods.

	private final Logger logger = LogManager.getLogger(this.getClass());
	// we will use try w/ resources so as to close a connection after executing the
	// method.

	@Override
	public Ticket findById(Integer myId) {
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String query = "select * from tickets where ticket_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, myId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String theType = rs.getString("type_reimbursement");
				Type thisType = Type.valueOf(theType);
				String theStatus = rs.getString("status_ticket");
				Status thisStatus = Status.valueOf(theStatus);
				Date thisDate = (rs.getDate("ticket_timestamp"));
				LocalDate thisJavaDate = thisDate.toLocalDate();
				Date statusDate = (rs.getDate("ticket_timestamp"));
				LocalDate statusJavaDate = statusDate.toLocalDate();
				return (new Ticket(rs.getInt("ticket_id"), rs.getInt("employee_id"), rs.getString("title_ticket"),
						thisType, rs.getDouble("amount_reimbursement"), rs.getString("description_ticket"),
						thisJavaDate, thisStatus, statusJavaDate));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Something went wrong", e);
		}
		return null;
	}

	@Override
	public List<Ticket> getTickets() {
		// Ok, so find all tickets
		ArrayList<Ticket> tickets = new ArrayList<Ticket>(); // 1. make a list for our list of tickets
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String query = "select * from tickets"; // 2. write a query to get all tickets
			PreparedStatement pstmt = conn.prepareStatement(query); // 3. create an instance or prepare statement w/ our
																	// query
			ResultSet rs = pstmt.executeQuery(); // 4. get the result set of the query
			while (rs.next())// while there is a next item in the result set
			{ // need to cast strings for type and status as Enums
				String theType = rs.getString("type_reimbursement");
				Type thisType = Type.valueOf(theType);
				String theStatus = rs.getString("status_ticket");
				Status thisStatus = Status.valueOf(theStatus);
				Date thisDate = (rs.getDate("ticket_timestamp"));
				LocalDate thisJavaDate = thisDate.toLocalDate();
				Date statusDate = (rs.getDate("ticket_timestamp"));
				LocalDate statusJavaDate = statusDate.toLocalDate();

				tickets.add(new Ticket(rs.getInt("ticket_id"), rs.getInt("employee_id"), rs.getString("title_ticket"),
						thisType, rs.getDouble("amount_reimbursement"), rs.getString("description_ticket"),
						thisJavaDate, thisStatus, statusJavaDate));
			}
		} catch (SQLException e) {

			e.printStackTrace();
			logger.error("Something went wrong", e);
		}

		return tickets;
	}

	@Override
	public void add(Object newObject) {
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			// 1. write a query to add a ticket to the tickets table
			String query = "INSERT INTO tickets (employee_id, title_ticket, type_reimbursement, amount_reimbursement, description_ticket, "
					+ "ticket_timestamp, status_ticket, status_timestamp ) " + "VALUES ( ?,?,?,?,?,?,?,?  )";
			// 2. create an instance or prepare statement w/ our query
			PreparedStatement pstmt = conn.prepareStatement(query); 
			// 3. prepare the place holders:
			pstmt.setInt(1, ((Ticket) newObject).getEmployeeId());
			pstmt.setString(2, ((Ticket) newObject).getTitle());
			Type aType = ((Ticket) newObject).getType();// so I have the value of Type for this ticket
			String thisType = aType.name(); // convert it to
			pstmt.setString(3, thisType);
			pstmt.setDouble(4, ((Ticket) newObject).getAmount());
			pstmt.setString(5, ((Ticket) newObject).getDescription());
			// Well, it is round-a-bout but here is a date to put in the DB
			LocalDate aDate = ((Ticket) newObject).getTicketTimeStamp();
			String whatDate = aDate.toString();
			Date date = new Date(0000 - 00 - 00);
			pstmt.setDate(6, date.valueOf(whatDate));
			Status aStatus = ((Ticket) newObject).getStatus();// so I have the value of Type for this ticket
			String thisStatus = aStatus.name(); // convert it to
			pstmt.setString(7, thisStatus);
			pstmt.setDate(8, date.valueOf(whatDate));
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Something went wrong", e);
		}
	}

	@Override
	public void update(Object newObject) {
		// TODO Auto-generated method stub
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			// 1. write a query to add a ticket to the tickets table
			String query = "Update tickets Set status_ticket = ?,  status_timestamp = ? where ticket_id = ?";
			
			//employee_id, title_ticket, type_reimbursement, amount_reimbursement, description_ticket, ticket_timestamp, 
			// 2. create an instance or prepare statement w/ our query
			PreparedStatement pstmt = conn.prepareStatement(query); 
			// 3. prepare the place holders:
			
			Status aStatus = ((Ticket) newObject).getStatus();//so I have the value of Status for this ticket
			String thisStatus = aStatus.name();		//convert it to 
			pstmt.setString(1, thisStatus);
			
			LocalDate aDate = ((Ticket) newObject).getStatusTimeStamp();
			String whatDate = aDate.toString();
			Date date = new Date(0000-00-00);
			date.valueOf(whatDate);
			pstmt.setDate(2, date );
			pstmt.setInt(3, ((Ticket) newObject).getId());
			pstmt.execute(); 
		} catch (SQLException e) {
		e.printStackTrace();
		logger.error("Something went wrong", e);
		}
	}

//end of class bracket
}
