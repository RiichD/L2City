import java.awt.Color;
public class FeteForaine extends Decoration{
	private int prix;
	
	public FeteForaine(){
		super("feteForaine", (int)(Math.random()*(500-10+1)+10));
		prix = getSurface() * 100;
	}
	
	//Getter
	public int getPrix(){
		return prix;
	}
	
	public Color getColor(){
		return new Color(253,108,158); //Rose
	}
	
	//Set
	public Color setColor(int r, int g, int b){
		return new Color(r,g,b);
	}
	
	//Clone
	public Item clone(){
		FeteForaine clone = new FeteForaine();
		clone.setSurface(getSurface());
		clone.setType(getType());
		clone.setID(getID());
		return clone;
	}
}