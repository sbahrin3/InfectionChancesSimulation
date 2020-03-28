package sb.sim;

import java.util.Random;

public class GameOfChancesSimulation {
	
	static long initialSize = 100l;
	static int totalDays = 100;
	
	
	public static void main(String[] args) {
		
		Population population = new Population();
		population.setChancesToBeInfected(10);
		population.setChancesToBeHealed(50);
		
		for ( long i = 0; i < initialSize; i++ ) {
			population.addOrganisme(Organisme.birth(i));
		}
		
		Thread inspectThread = new Thread(new Inspect(population));
		inspectThread.start();
		

		
	}
	
	
	static class Inspect implements Runnable {
		
		Population population;
		Random r = new Random();
		long min = 0;
		long max = initialSize;
		
		public Inspect(Population population) {
			this.population = population;
		}

		@Override
		public void run() {
			
			long countHealthy = 0;
			long countInfected = 0;
			long countHealed = 0;
			
			System.out.print("\n\n\n\n");
			System.out.println("==================================================");
			System.out.println("GAME OF CHANCES: DISEASE INFECTION IN A POPULATION");
			System.out.println("==================================================");
			System.out.println("Population size: " + population.getOrganismes().size());
			System.out.println("Chances to get infected: " + population.getChancesToBeInfected());
			System.out.println("Chances to get healed after infection: " + population.getChancesToBeHealed());
			System.out.println("H: Healthy, S: Sick, R: Recovered");
			System.out.println("-----------------");
			
			long ind = 0;
			int days = 0;
			
			System.out.print("Day " + days + ":   ");
			while ( days < totalDays ) {
				try {
					Thread.sleep(1);
					
					Organisme organisme = population.getIndividual(ind);
					organisme.show();
					
					if ( !organisme.isInfected() ) {
						countHealthy++;
					} else {
						countInfected++;
					}
					
					if ( organisme.isHealed()) {
						countHealed++;
					}
					
					ind++;
					if ( ind == max ) {
						ind = 0;
						days++;
	
						System.out.print(" H: " + countHealthy + " S: " + countInfected + " R: " + countHealed);
						System.out.println("  ");
						System.out.print("\r");
						
						System.out.print("Day " + days + ": ");
						if ( days < 10 ) System.out.print("  ");
						else if ( days > 9 && days < 100 ) System.out.print(" ");
												
						countHealthy = 0;
						countInfected = 0;
						countHealed = 0;
												
					}
					
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			//Kill all organismes in this population
			population.getOrganismes().stream().forEach(organisme->{
				organisme.killMe();
			});
			System.out.println();
			
		}

		
		public Organisme getRandomIndividual() {
			long n = r.longs(min, (max)).findFirst().getAsLong();
			return population.getIndividual(n);
		}
		
		
		
		
	}
	


}


