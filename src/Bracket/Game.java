package Bracket;

public class Game {
	
	
	private String winner, player1, player2;
	
	private boolean gamePlayed;
	
	
	public Game(String team1, String team2){
		player1 = team1;
		player2 = team2;
		winner = "";
	}
	
	
	public String playGame(int win){
		
		if(!gamePlayed){
		if(win == 0){
			gamePlayed = true;
			winner = player1;
			return winner;
		}
		else if(win == 1){
			gamePlayed = true;
			winner = player2;
			return winner;
		}
		}
		return null;
	}
	
	/**
	 * 
	 * @return The winner of the game
	 */
	public String getWinner(){
		return winner;
	}
	
	/**
	 * 
	 * @return The state of the game. 
	 */
	
	public boolean hasPlayed(){
		return gamePlayed;
	}
}
