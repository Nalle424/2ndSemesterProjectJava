package Test;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import control.*;
import database.*;
import gui.*;

//import static org.junit.Assert.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik Bærbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestDataBaseAccess {
	
	DBConnection con = null;


	@Before
	public void setUp() throws DataAccessException {
		con = DBConnection.getInstance();
	}
	
	
	@Test
	public void wasConnected() throws DataAccessException {
		assertNotNull("Connected - connection cannot be null", con);
		
		DBConnection.disconnect();
		boolean wasNullified = DBConnection.instanceIsNull();
		assertTrue("Disconnected - instance set to null", wasNullified);
		
		con = DBConnection.getInstance();
		assertNotNull("Connected - connection cannot be null", con);		
	}
	
	
	
	
	

	@After
	public void cleanUp() {
		DBConnection.disconnect();
	}	
	

}
