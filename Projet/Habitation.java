import java.awt.Color;
public abstract class Habitation extends Batiment{
	private char classeEnergie;
	private int prix;
	
	public Habitation(String type, int surface){
		super(type, surface);
		classeEnergie = (char)('A' + Math.random()*7);
		prix = surface * 250 * (10+ 'G' - classeEnergie);
	}
	
	//Getter
	public int getSurface(){
		return super.getSurface();
	}
	
	public int getPrix(){
		return prix;
	}
	
	public char getClasseEnergie(){
		return classeEnergie;
	}
	
	public abstract Color getColor();
	
	public abstract int getNbHabitants();

	//Set
	public void setClasseEnergie(char c){
		classeEnergie = c;
	}
	
	public abstract Color setColor(int r, int g, int b);
	
	//toString
	public String toString(){
		return super.toString() + " classe " + classeEnergie + " nbHab = " + getNbHabitants();
	}
	
	//Clone
	public abstract Item clone();
}