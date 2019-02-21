import java.awt.Color;
public class RouteEnTerre extends Route{
	private int prix;
	
	public RouteEnTerre(int surface, int prix){
		super("terre", surface);
		this.prix = prix;
	}
	
	//Getter
	public int getPrix(){
		return prix;
	}
	
	public Color getColor(){
		return new Color(126, 51, 0); //Marron caramel
	}
	
	//Set
	public Color setColor(int r, int g, int b){
		return new Color(r,g,b);
	}
	
	//Clone
	public Item clone(){
		RouteEnTerre clone = new RouteEnTerre(getSurface(), prix);
		clone.setSurface(getSurface());
		clone.setType(getType());
		clone.setID(getID());
		return clone;
	}
}