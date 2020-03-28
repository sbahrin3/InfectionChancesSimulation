package sb.sim;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Population {
	
	private int chancesToBeInfected = 70;
	private int chancesToBeHealed = 50;
	
	private List<Organisme> organismes = new ArrayList<>();
	
	public Population() {
		
	}

	public List<Organisme> getOrganismes() {
		return organismes;
	}

	public void setOrganismes(List<Organisme> organismes) {
		this.organismes = organismes;
	}
	
	public void addOrganisme(Organisme organisme) {
		this.organismes.add(organisme);
		organisme.setPopulation(this);
	}

	public Organisme getIndividual(long id) {
		Optional<Organisme> optional = organismes.stream().filter(organisme -> organisme.getId() == id ).findFirst();
		return optional.get();
	}

	public int getChancesToBeInfected() {
		return chancesToBeInfected;
	}

	public void setChancesToBeInfected(int chancesToBeInfected) {
		this.chancesToBeInfected = chancesToBeInfected;
	}

	public int getChancesToBeHealed() {
		return chancesToBeHealed;
	}

	public void setChancesToBeHealed(int chancesToBeHealed) {
		this.chancesToBeHealed = chancesToBeHealed;
	}
	
	
	

}
