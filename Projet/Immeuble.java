import java.awt.Color;
public class Immeuble extends Habitation implements AEtage{
	public final int MAX_ETAGES = 10;
	private int nbEtages;
	private int nbHabitants;
	
	public Immeuble(){
		super("immeuble", 100);
		nbEtages = (int)(Math.random()*(MAX_ETAGES +1));
		this.nbHabitants = (nbEtages * getSurface())/30;
	}
	
	//Getter
	public int getNbHabitants(){
		return nbHabitants;
	}
	
	public int getNbEtages(){
		return nbEtages;
	}
	
	public Color getColor(){
		return new Color(31,160,85); //Vert foncé
	}
	
	//Set
	public Color setColor(int r, int g, int b){
		return new Color(r,g,b);
	}
	
	//toString
	public String toString(){
		return "Immeuble " + super.toString() + " et " + getNbEtages() + " etages ";
	}
	
	//Clone
	public Item clone(){
		Immeuble clone = new Immeuble();
		clone.setSurface(getSurface());
		clone.setType(getType());
		clone.setID(getID());
		clone.nbHabitants = getNbHabitants();
		clone.setClasseEnergie(getClasseEnergie());
		return clone;
	}
}