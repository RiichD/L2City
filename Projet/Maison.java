import java.awt.Color;
public class Maison extends Habitation{
	private int nbHabitants;
	
	public Maison(){
		super("maison", (int)(Math.random()*(120 - 20 + 1) + 20));
		this.nbHabitants = (int)(getSurface() / 20);
	}
	
	//Getter
	public int getNbHabitants(){
		return nbHabitants;
	}
	
	public Color getColor(){
		return new Color(0,255,0);
	}
	
	//Set
	public Color setColor(int r, int g, int b){
		return new Color(r,g,b);
	}
	
	//toString
	public String toString(){
		return "Maison " + super.toString();
	}
	
	//Clone
	public Item clone(){
		Maison clone = new Maison();
		clone.setSurface(getSurface());
		clone.setType(getType());
		clone.setID(getID());
		clone.nbHabitants = getNbHabitants();
		clone.setClasseEnergie(getClasseEnergie());
		return clone;
	}
}