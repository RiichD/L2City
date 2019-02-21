import java.awt.Color;
public abstract class Produit{
	private String nom;
	private int quantite;
	
	public Produit(String nom){
		this.nom = nom;
		quantite = (int)(Math.random()*100);
	}
	
	//Getter
	public String getNom(){
		return nom;
	}
	
	public int getQuantite(){
		return quantite;
	}
	
	public abstract int getPrix();
	
	//Set
	public void setNom(String n){
		nom = n;
	}
	
	public void setQuantite(int q){
		quantite = q;
	}
	
	//toString
	public String toString(){
		return "Le produit " + nom + " est en quantite de " + quantite + " " + nom;
	}
	
	//Clone
	public abstract Produit clone();
}