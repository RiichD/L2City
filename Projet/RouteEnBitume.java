import java.awt.Color;
public class RouteEnBitume extends Route{
	private int prix;
	
	public RouteEnBitume(int surface, int prix){
		super("bitume", surface);
		this.prix = prix;
	}
	
	//Getter
	public int getPrix(){
		return prix;
	}
	
	public Color getColor(){
		return new Color(96, 96, 96); //Gris
	}
	
	//Set
	public Color setColor(int r, int g, int b){
		return new Color(r,g,b);
	}
	
	//Clone
	public Item clone(){
		RouteEnBitume clone = new RouteEnBitume(getSurface(), prix);
		clone.setSurface(getSurface());
		clone.setType(getType());
		clone.setID(getID());
		return clone;
	}
}