/**
 * 
 */
package com.revature.projectreimbursement.dl;

import java.time.LocalDate;
import java.util.List;

import com.revature.projectreimbursement.models.Ticket;
import com.revature.projectreimbursement.ui.InputClass.Status;

/**This is a generic interface for our DAOs
 * @author WilliamMaskevich
 * 
 * @param <T> type of object we're creating a DAO for
 * @param <K> data type of the id of the object 
 */
public interface IDAO<T, K> {
	//T findById(K id); // for when we want to find an object by its ID
	List<T> getTickets();	// for when we want all of a certain property
	void add(T newObject); //for when we want to add an object (a row) to our dB
	void update(T newObject);  // for when we want to modify a field of an object
	Ticket findById(Integer myId);


}
