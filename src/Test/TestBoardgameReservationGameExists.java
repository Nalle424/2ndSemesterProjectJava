package Test;
import static org.junit.Assert.*;

import org.junit.*;
import java.util.LinkedList;

import control.*;
import model.Boardgame;

public class TestBoardgameReservationGameExists {
	
	BoardgameReservationCtr RCon;
	private BoardgameCtr BCon;
	LinkedList<Boardgame> res;

	@Before
	public void setUp() throws DataAccessException {
		RCon = new BoardgameReservationCtr();
		BCon = new BoardgameCtr();
		Boardgame bg = new Boardgame("vikings", 1, "Vikinger spillet", "strategy", "30+", "3+");
		BCon.insertBoardgame(bg);
	}
	
	@Test
	public void testCaseGameExists() throws DataAccessException {
		//Arrange
		res = new LinkedList<Boardgame>();		
		String expectedName = "vikings";
		//Act
		res = RCon.searchBoardgame(expectedName);
		//Assert
		assertEquals("Name should be settlers", expectedName, res.getFirst().getName());
	}

	@After
	public void cleanUp() throws DataAccessException {
		BCon.deleteBoardgames(res.getFirst().getGameID());
	}
	

}
