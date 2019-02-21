import java.awt.Color;
public class Usine extends Batiment implements AEtage{
	private int prix;
	private int nbEtages;
	public final int MAX_ETAGES = 10;
	
	public Usine(){
		super("usine", (int)(Math.random()*(350 - 50 + 1) + 50));
		prix = getSurface() * 2000;
		nbEtages = (int)(Math.random()*(MAX_ETAGES + 1));
	}
	
	//Getter
	public int getPrix(){
		return prix;
	}
	
	public int getNbEtages(){
		return nbEtages;
	}
	
	public Color getColor(){
		return new Color(255,255,0);
	}
	
	//Set
	public Color setColor(int r, int g, int b){
		return new Color(r,g,b);
	}
	
	//toString
	public String toString(){
		return "Usine " + super.toString() + " et " + getNbEtages() + " etages ";
	}
	
	//Clone
	public Item clone(){
		Usine clone = new Usine();
		clone.setSurface(getSurface());
		clone.setType(getType());
		clone.setID(getID());
		clone.prix = getPrix();
		clone.nbEtages = getNbEtages();
		return clone;
	}
}