package Bracket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

/**
 * Class that constructs a bracket from a provided list of teams
 * 
 * @author Jonathan Bown
 */
public class BasketballBracket {

	public Game[] firstRound, second32, sweet16, elite8, final4, lastRound;

	// Keeps track of the correct picks
	private int wins, wins64, wins32, wins16, wins8, wins4, wins2;
	
	
	//Keeps track of round totals for calculation of statistics
	private int total64, total32, total16, total8, total4, total2;
	
	//keeps track of maximums
	private int max64, max32, max16, max8, max4;

	private String[] actualWinners;

	public BasketballBracket(String[] teamArray, String[] winnerArray) {
		if (teamArray.length == 0) {
			JOptionPane.showMessageDialog(null, "You must provide a list of teams");
		}

		// Initialize Lists
		actualWinners = new String[63];
		for (int i = 0; i < winnerArray.length; i++) {
			actualWinners[i] = winnerArray[i];
		}

		firstRound = new Game[teamArray.length / 2];
		second32 = new Game[firstRound.length / 2];
		sweet16 = new Game[second32.length / 2];
		elite8 = new Game[sweet16.length / 2];
		final4 = new Game[elite8.length / 2];
		lastRound = new Game[final4.length / 2];
		int index = 0;
		for (int i = 1; i < teamArray.length; i++) {

			if (i % 2 != 0) { // If I have two teams added make a game and
								// add it to the first round
				firstRound[index] = new Game(teamArray[i - 1], teamArray[i]);
				index++;
			}

		}

	}

	public void simulateBracket() {
		// First Round of 64
		String[] winners64 = new String[firstRound.length];
		int gameIndex = 0;
		for (int i = 0; i < 32; i++) {
			winners64[i] = firstRound[i].playGame(flip());
			if (i % 2 != 0) {
				second32[gameIndex] = new Game(winners64[i - 1], winners64[i]);
				gameIndex++;
			}
			if (winners64[i].equals(actualWinners[i])) {
				wins64++;
			}

		}
		gameIndex = 0;
		System.out.print(wins64);
		// Build the winners of the round of 32
		String[] winners32 = new String[second32.length];
		for (int i = 0; i < 16; i++) {
			winners32[i] = second32[i].playGame(flip()); //Play the game, determine the winner
			if (i % 2 != 0) {
				sweet16[gameIndex] = new Game(winners32[i - 1], winners32[i]); //Create the next round of games
				gameIndex++; //Only have half the items as second32, must keep track of separate index
			}
			if (winners32[i].equals(actualWinners[32 + i])) {
				wins32++;
			}

		}
		gameIndex = 0;
		System.out.print("," + wins32);
		// build the winners of the sweet16
		String[] winners16 = new String[sweet16.length];
		for (int i = 0; i < 8; i++) {
			winners16[i] = sweet16[i].playGame(flip());
			if (i % 2 != 0) {
				elite8[gameIndex] = new Game(winners16[i - 1], winners16[i]);
				gameIndex++;
			}
			if (winners16[i].equals(actualWinners[48 + i])) {
				wins16++;
			}
		}
		gameIndex = 0;
		System.out.print("," + wins16);
		// Build the winners of the elite 8
		String[] winners8 = new String[elite8.length];
		for (int i = 0; i < 4; i++) {
			winners8[i] = elite8[i].playGame(flip());
			if (i % 2 != 0) {
				final4[gameIndex] = new Game(winners8[i - 1], winners8[i]);
				gameIndex++;
			}
			if (winners8[i].equals(actualWinners[56 + i])) {
				wins8++;
			}

		}
		gameIndex = 0;
		System.out.print("," + wins8);
		// Build the winners of the final 4
		String[] winners4 = new String[final4.length];
		for (int i = 0; i < 2; i++) {
			winners4[i] = final4[i].playGame(flip());
			if (i % 2 != 0) {
				lastRound[gameIndex] = new Game(winners4[i - 1], winners4[i]);
				gameIndex++;
			}
			if (winners4[i].equals(actualWinners[60 + i])) {
				wins4++;
			}

		}
		gameIndex = 0;
		// Build the winner of the final
		System.out.print("," + wins4);
		String[] winners2 = new String[lastRound.length];
		for (int i = 0; i < 1; i++) {
			winners2[i] = lastRound[i].playGame(flip());

			if (winners2[i].equals(actualWinners[62])) {
				wins2++;
			}
		}
		wins = wins64 + wins32 + wins16 + wins8 + wins4 + wins2;
	
		System.out.println("," + wins2 + "," + wins);
		
		
	}

	public Game[] getFirstRound() {
		return firstRound;
	}

	public Game[] get32Round() {
		return second32;
	}

	public Game[] get16Round() {
		return sweet16;
	}

	public Game[] get8Round() {
		return elite8;
	}

	public Game[] get4Round() {
		return final4;
	}

	public Game[] getFinalRound() {
		return lastRound;
	}

	public int getWins() {
		return wins;
	}

	/**
	 * Returns the total wins from this bracket
	 * @param round string of the round, 64,32,16,8,4,2
	 * @return
	 */
	public int getRoundTotal(String round){
		switch(round){
		case "64":
			return wins64;
		case "32":
			return wins32;
		case "16":
			return wins16;	
		case "8":
			return wins8;
		case "4":
			return wins4;
		case "2":
			return wins2;
			default:
				return 0;
		}
	}
	/**
	 * Simulates flipping a coin to determine the winner of the game. The random
	 * numbers are uniformly distrubuted, meaning the possible values of 0 and 1
	 * are equally likely. Which is the same probability as a coin.
	 * 
	 * @return
	 */
	public int flip() {
		int result = 2;
		Random randomNum = new Random();
		result = randomNum.nextInt(2);
		return result;
	}

	public static String[] readFile(String filename) {
		ArrayList<String> results = new ArrayList<String>();
		try (BufferedReader input = new BufferedReader(new FileReader(filename))) {
			while (input.ready()) {
				results.add(input.readLine());
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results.toArray(new String[results.size()]);
	}

}
