package sb.sim;

public class Organisme implements Runnable {
	
	private long id;
	private Thread life;
	private int health = 10; //1 - 10
	private boolean infected = false;
	private int canInfect = 70;
	private int canHeal = 50;
	private boolean healed = false;
	private Population population;
	
	
	public Organisme(long id) {
		this.id = id;
		life = new Thread(this);
		life.start();
	}
	
	public static Organisme birth(long id) {
		return new Organisme(id);
	}
	
	public void killMe() {
		this.life = null;
	}

	@Override
	public void run() {
		while ( isAlive() ) {
			try {
				Thread.sleep(1000);
				
				if ( !healed ) {
					if ( !infected ) {
						infected = Chance.hit(canInfect);
					} else {
						infected = !Chance.hit(canHeal);
						healed = !infected;
					}
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean isAlive() {
		return life != null;
	}
	
	public boolean isDead() {
		return life == null;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Thread getLife() {
		return life;
	}

	public void setLife(Thread life) {
		this.life = life;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isInfected() {
		return infected;
	}

	public void setInfected(boolean infected) {
		this.infected = infected;
	}
	
	
	
	public boolean isHealed() {
		return healed;
	}

	public void setHealed(boolean healed) {
		this.healed = healed;
	}

	public Population getPopulation() {
		return population;
	}

	public void setPopulation(Population population) {
		this.population = population;
		this.canInfect = population.getChancesToBeInfected();
		this.canHeal = population.getChancesToBeHealed();
	}

	public void show() {
		String str = Colored.ANSI_BG_BLACK + Colored.ANSI_BRIGHT_GREEN;
		if ( infected ) {
			str = Colored.ANSI_BG_RED + Colored.ANSI_BRIGHT_YELLOW;
		} 
		else if ( healed ){
			str = Colored.ANSI_BG_CYAN + Colored.ANSI_BRIGHT_GREEN;
		}
		str += "X";
		str += Colored.ANSI_RESET;
		str += "";
		System.out.print(str);
	}
	
	
}
