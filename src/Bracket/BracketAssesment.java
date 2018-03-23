package Bracket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
/**
 * Class that uses an instance of a bracket to simulate real results. 
 * @author Jonathan Bown
 */
public class BracketAssesment {
	
	
	private static int total64, total32, total16, total8, total4, total2, total;
	
	private static int max64, max32, max16, max8, max4, max2, maxWins, countWinner;
	
	
	public static void main(String[] args){
		
		String[] teams = readFile("2016Teams");
		String[] winners = readFile("2016Winners");
		max64 = 0; max32 = 0; max16 = 0; max8=0; max4 =0; max2 = 0; maxWins = 0;
		System.out.println("First_Rount, Second_Round, Sweet_Sixteen, Elite_8_Round, Final_4_Round, Final_Round, Total");
	for(int i = 0; i< 50; i++){
			BasketballBracket bracket = new BasketballBracket(teams, winners);
			bracket.simulateBracket();
			
			//Aggregate the total wins
			
			//Return Max wins
			if(max64 < bracket.getRoundTotal("64")){
				max64 = bracket.getRoundTotal("64");
			}
			total64+=bracket.getRoundTotal("64");
			if(max32 < bracket.getRoundTotal("32")){
				max32 = bracket.getRoundTotal("32");
			}
			total32+=bracket.getRoundTotal("32");
			if(max16 < bracket.getRoundTotal("16")){
				max16 = bracket.getRoundTotal("16");
			}
			total16+=bracket.getRoundTotal("64");
			if(max8 < bracket.getRoundTotal("8")){
				max8 = bracket.getRoundTotal("8");
			}
			total8+=bracket.getRoundTotal("64");
			if(max4 < bracket.getRoundTotal("4")){
				max4 = bracket.getRoundTotal("4");
			}
			total4+=bracket.getRoundTotal("4");
			if(max2 < bracket.getRoundTotal("2")){
				max2 = bracket.getRoundTotal("2");
			}
			if(bracket.getRoundTotal("2") > 0){
				countWinner++;
			}
			total2+=bracket.getRoundTotal("2");
			if(maxWins < bracket.getWins()){
				maxWins = bracket.getWins();
			}
			total+=bracket.getWins();
		}	
	
	double meanWins = total/50;
	
	System.out.println("Average Wins" + " " + meanWins);
	System.out.println("Maximums:" + " " + "64 - " + max64 + "\n" + "32 - " + max32 + "\n" + "16 - " + max16 + "\n" + "8 - " + max8
			+ "\n" + "4 - " + max4 + "\n" + "2 - " + max2 + "\n" + "Total - " + maxWins + "\n" + "Predicted Correct Winner " + countWinner + " times" );
	
	
	//Totals, max, min, averages have to be calculated outside the bracket assesment. 
	}
	
	
	
	
	
	/**
	 * Reads the file from the given file name
	 * @param filename
	 * @return
	 */
    public static String[] readFile (String filename)
    {
        ArrayList<String> results = new ArrayList<String>();
        try (BufferedReader input = new BufferedReader(new FileReader(filename)))
        {
            while (input.ready())
            {
                results.add(input.readLine());
            }
            input.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return results.toArray(new String[results.size()]);
    }
	
	
	

}
