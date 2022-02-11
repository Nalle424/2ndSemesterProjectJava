package Test;
import static org.junit.Assert.*;

import org.junit.*;
import java.util.LinkedList;

import control.*;
import model.Boardgame;

public class TestBoardgameReservationGameDoesNotExist {
	
	BoardgameReservationCtr RCon;
	LinkedList<Boardgame> res;

	@Before
	public void setUp() throws DataAccessException {
		RCon = new BoardgameReservationCtr();
	}

	@Test
	public void testCaseNoGame() throws DataAccessException {
		//Arrange
		LinkedList<Boardgame> result = new LinkedList<Boardgame>();
		boolean isEmpty = true;
		String searchName = "Victors eventyr";
		//Act
		result = RCon.searchBoardgame(searchName);
		isEmpty = result.isEmpty();
		//Assert
		assertTrue("IsEmpty should be true", isEmpty);
	}

}
