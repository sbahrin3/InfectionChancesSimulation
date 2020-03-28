package sb.sim;

import java.util.Random;

public class Chance {
	static int min = 0;
	static int max = 100;
	static Random r = new Random();

	public static boolean hit(int value) {
		int n = r.ints(min, (max)).findFirst().getAsInt();
		return n <= value;
	}



	public static void main(String[] args) {

		int chanceValue = 0;

		for ( int x = 0; x < 50; x++ ) {
			int numberTrue = 0;
			int numberFalse = 0;
			
			for ( int i=0; i< 100; i++ ) {
				boolean result = hit(chanceValue);
				if ( result ) {
					numberTrue++;
				}
				else {
					numberFalse++;
				}
			}
			System.out.println("YES : " + numberTrue);
			System.out.println("NO  : " + numberFalse);
			System.out.println();
		}



	}

}
