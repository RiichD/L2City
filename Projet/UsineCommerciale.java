import java.awt.Color;
public class UsineCommerciale extends Commerce{
	private Produit produit;
	private int paquet;
	private int prod_max;
	private int prod_min;
	
	public UsineCommerciale(String nom, Produit produit, int paquet, int prod_max, int prod_min){
		super(nom);
		this.produit = produit;
		this.paquet = paquet;
		this.prod_max = prod_max;
		this.prod_min = prod_min;
	}
	
	//Getter
	public int getPrixProduit(){
		return (produit.getPrix());
	}
	
	public int getQuantite(){
		return produit.getQuantite();
	}
	
	public int getPaquet(){
		return paquet;
	}
	
	public int getProdMax(){
		return prod_max;
	}
	
	public Produit getProduit(){
		return produit;
	}
	
	public int getProdMin(){
		return prod_min;
	}
	
	public Color getColor(){
		return new Color(240,195,0); //Ambre
	}
	
	//Set
	public Color setColor(int r, int g, int b){
		return new Color(r,g,b);
	}
	
	//Ajouter
	public void ajouterPaquet(int p){
		paquet += p;
	}
	
	public void ajouterProductionMax(int p){
		prod_max += p;
	}
	
	public void ajouterProductionMin(int p){
		if (prod_min + p <= prod_max ) prod_min += p;
		else System.out.println("Augmentation de prod_min echouee. Augmentez la prod_max !");
	}
	
	//toString
	public String toString(){
		return super.getStringID() + " , au nom de " + getNom() + ". Usine commerciale " + super.toString() + " possede " + getQuantite() + " " + produit.getNom();
	}
	
	//Clone
	public Item clone(){
		UsineCommerciale clone = new UsineCommerciale(getNom(), getProduit(), getPaquet(), getProdMax(), getProdMin());
		clone.setSurface(getSurface());
		clone.setType(getType());
		clone.setID(getID());
		return clone;
	}
	
	//Autres
	public void livrer(){ //Retire x quantite car le produit est livré à une boutique
		if (produit.getQuantite() - paquet>=0) {
			produit.setQuantite(produit.getQuantite() - paquet); //Livre par quantité de paquet
			System.out.println("Livraison reussie de " + getStringID());
		} else {
			System.out.println("La quantite est insuffisante pour la livraison");
		}
	}
	
	public void production(){
		produit.setQuantite(produit.getQuantite() + (int)(Math.random()*(prod_max-prod_min+1)+prod_min));
	}
}