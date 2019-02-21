import java.awt.Color;
public class Boutique extends Commerce{
	public final int MAX_PRODUITS=10;
	private Produit[] liste;
	private int nbclient_max;
	
	public Boutique(String nom){ //Nom de la boutique
		super(nom);
		liste = new Produit[MAX_PRODUITS];
		nbclient_max = (int)getSurface()/20;
	}
	
	//Affichage
	public void afficherProduits(){
		int i = 0;
		while ( (i < liste.length)){
			if ((liste[i] instanceof Produit)){
				liste[i].toString();
			}
			i++;
		}
	}
	
	//Getter
	public int getNbProduits(){
		int i = 0;
		int nb = 0;
		while (i < liste.length){
			if (liste[i] instanceof Produit){
				nb++;
			}
			i++;
		}
		return nb;
	}
	
	public int getNbClientMax(){
		return nbclient_max;
	}
	
	public Produit getProduit(int i){
		if (liste[i] instanceof Produit) return liste[i];
		else return null;
	}
	
	public Produit getProduit(String n){
		int i = 0;
		while (i < liste.length){
			if ((liste[i] instanceof Produit)){
				if (liste[i].getNom().equals(n)){ //Si la liste contient déjà le produit, seul la quantite change
					return liste[i];
				}
			}
			i++;
		}
		return null;
	}
	
	public Color getColor(){
		return new Color(0,0,255);
	}
	
	//Set
	public Color setColor(int r, int g, int b){
		return new Color(r,g,b);
	}
	
	//Ajouter
	public void ajouterProduit(Produit produit){
		int i = 0;
		boolean ajout = false;
		boolean dejaajoute = false;
		while ( (i < liste.length) && (ajout == false) ){
			if ((liste[i] instanceof Produit)){
				if (liste[i].getNom().equals(produit.getNom())){ //Si la liste contient déjà le produit, seul la quantite change
					liste[i].setQuantite(liste[i].getQuantite() + produit.getQuantite());
					dejaajoute = true;
					ajout = true;
				}
			} else {
				liste[i] = produit;
				ajout = true;
			}
			i++;
		}
		if (dejaajoute) System.out.println("Le produit est deja dans la boutique, seule la quantite du produit a changee");
		if (ajout) System.out.println("Ajout du produit reussi");
		if (!ajout) System.out.println("Votre boutique est pleine");
	}
	
	//Retirer
	public void retirerProduit(Produit produit){
		int i = 0;
		boolean retrait = false;
		boolean trouve = false;
		while ( (i < liste.length) && (retrait == false) ){
			if ((liste[i] instanceof Produit)){
				if (!trouve && liste[i].equals(produit)){ //Si la liste contient déjà le produit, seul la quantite change
					trouve = true;
					retrait = true;
				}
				if (trouve){
					if (i+1 < liste.length){
						liste[i] = liste[i+1];
						liste[i+1] = null;
					} else {
						liste[i] = null;
					}
				}
			}
			i++;
		}
		if (retrait) System.out.println("Retrait du produit reussi");
		if (!retrait) System.out.println("Retrait echouee");
	}
	
	//toString
	public String listeProduits(){
		String s = "";
		int i = 0;
		while (liste[i] instanceof Produit){
			s += liste[i] + "\n";
			i++;
		}
		return s;
	}
	
	public String toString(){
		return super.getStringID() + " , au nom de " + getNom() + ". Boutique " + super.toString() + ", possede " +  getNbProduits() + " produits";
	}
	
	//Clone
	public Item clone(){
		Boutique clone = new Boutique(getNom());
		clone.setSurface(getSurface());
		clone.setType(getType());
		clone.setID(getID());
		for (int i = 0; i< liste.length; i++){
			if (liste[i] instanceof Produit){
				clone.liste[i] = liste[i].clone();
			} else {
				clone.liste[i] = null;
			}
		}
		return clone;
	}
	
	//Autres
	public void reception(UsineCommerciale u){ //Réception de la livraison de l'usine
		int i;
		for (i = 0; i< liste.length; i++){
			if (liste[i] instanceof Produit){
				if (liste[i].getNom().equals((u.getProduit()).getNom())){
					liste[i].setQuantite( liste[i].getQuantite() + u.getPaquet() );
					u.livrer();
				}
			}
		}
	}
	
	public int produitsConsommer(int nbHabs){ //Retourne le montant total de produits consommés par les habitants
		if (nbHabs > 0){
			int nbclientjour = (int)(Math.random()*(nbHabs+1));
			if (nbclient_max <= nbclientjour){
				nbclientjour = (int)(Math.random()*(nbclient_max+1));
			}
			
			int i;
			int prod_consomme;
			int somme = 0; //Montant total de produits consommés
			
			for (i = 0; i < getNbProduits(); i++){
				if (liste[i] instanceof Produit){
					prod_consomme = (int)(Math.random()*(nbclient_max*2+1));
					if (liste[i].getQuantite()<prod_consomme){
						prod_consomme = liste[i].getQuantite();
					}
					somme += liste[i].getQuantite()*liste[i].getPrix();
					liste[i].setQuantite(liste[i].getQuantite() - prod_consomme);
				}
			}
			return somme;
		} else {
			return 0;
		}
	}
}