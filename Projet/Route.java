import java.awt.Color;
public abstract class Route implements Item{
	private int nbRoutes;
	private static int id=0;
	private int cptid;
	private String type;
	private int surface;
	
	public Route(String type, int surface){
		this.type = type;
		this.surface = surface;
		id++;
		cptid = id;
		nbRoutes = id;
	}
	
	//Retirer
	public void retirerUnStatic(){
		id--;
	}
	
	//Getter
	public int getNbRoutes(){
		return id;
	}
	
	public int getSurface(){
		return surface;
	}
	
	public String getStringID(){
		return String.format("%c%02d", type.charAt(0) , cptid);
	}
	
	public String getType(){
		return type;
	}
	
	public int getID(){
		return cptid;
	}
	
	public abstract int getPrix();
	
	//Set
	public void setSurface(int surface){
		this.surface = surface;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public void setID(int id){
		cptid = id;
	}
	
	//toString
	public String toString(){
		return "No " + cptid + " " + surface + "m2 prix = " + getPrix() + " pims";
	}
	
	//Clone
	public abstract Item clone();
}