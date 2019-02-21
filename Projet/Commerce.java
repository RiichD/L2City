import java.awt.Color;
public abstract class Commerce extends Batiment{
	private String nom;
	private int prix;
	
	public Commerce(String nom){ //nom du commerce
		super(nom, (int)(Math.random()*(300-20+1)+20));
		this.nom = nom;
		prix = getSurface() * 2500;
	}
	
	//Getter
	public String getNom(){
		return nom;
	}
	
	public int getPrix(){
		return prix;
	}
	
	public abstract Color getColor();
	
	//Set
	public abstract Color setColor(int r, int g, int b);
	
	//Clone
	public abstract Item clone();
}