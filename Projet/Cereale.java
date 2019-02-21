public class Cereale extends Produit{
	private int prix;
	
	public Cereale(int prix){
		super("cereale");
		this.prix = prix;
	}
	
	//Getter
	public int getPrix(){
		return prix;
	}
	
	//Clone
	public Produit clone(){
		Cereale clone = new Cereale(prix);
		clone.setNom(getNom());
		clone.setQuantite(getQuantite());
		return clone;
	}
}