import java.awt.Color;
public class Parc extends Decoration{
	private int prix;
	
	public Parc(){
		super("parc", (int)(Math.random()*(500-10+1)+10));
		prix = getSurface() * 500;
	}
	
	//Getter
	public int getPrix(){
		return prix;
	}
	
	public Color getColor(){
		return new Color(52,201,36); //Vert pomme
	}
	
	//Set
	public Color setColor(int r, int g, int b){
		return new Color(r,g,b);
	}
	
	//Clone
	public Item clone(){
		Parc clone = new Parc();
		clone.setSurface(getSurface());
		clone.setType(getType());
		clone.setID(getID());
		return clone;
	}
}