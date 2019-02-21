public class Pain extends Produit{
	private int prix;
	
	public Pain(int prix){
		super("pain");
		this.prix = prix;
	}
	
	//Getter
	public int getPrix(){
		return prix;
	}
	
	//Clone
	public Produit clone(){
		Pain clone = new Pain(prix);
		clone.setNom(getNom());
		clone.setQuantite(getQuantite());
		return clone;
	}
}