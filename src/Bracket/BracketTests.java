package Bracket;

import static org.junit.Assert.*;

import org.junit.Test;

public class BracketTests {

	@Test
	public void testGamePlayer1() {
		Game play1 = new Game("Villanova", "UConn");
		String winner = play1.playGame(0);
		assertEquals("Villanova", winner);
	}
	
	@Test
	public void testGamePlayer2() {
		Game play1 = new Game("Villanova", "UConn");
		String winner = play1.playGame(1);
		assertEquals("UConn", winner);
	}

	
	//Test Game class
	
	
	
	//Test bracket to find bugs
}
