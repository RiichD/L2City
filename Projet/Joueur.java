import java.awt.Color;
public class Joueur{
	private String pseudo;
	private VilleJPanel laVille;
	private int porteMonnaie;
	private static final int TAILLE_CASE=25;
	private static final int NB_CASES=4;
	
	public Joueur(String pseudo, String laVille){
		this.pseudo = pseudo;
		this.laVille = new VilleJPanel(laVille,NB_CASES, TAILLE_CASE);
		porteMonnaie = 1000000;
	}
	
	//Afficher
	public void afficherVille(){
		laVille.afficherVille();
	}
	
	public void afficherCommercetoString(){ //avec toString
		laVille.afficherCommercetoString();
	}
	
	public void afficherBatimenttoString(){ //avec toString
		laVille.afficherBatimenttoString();
	}
	
	//Getter
	public String getPseudo(){
		return pseudo;
	}
	
	public int getTailleVille(){
		return laVille.getNbCases()*laVille.getNbCases();
	}
	
	public int getNbCases(){
		return laVille.getNbCases();
	}
	
	public int getPorteMonnaie(){
		return porteMonnaie;
	}
	
	public int getNbHabitants(){
		return laVille.compterHabitants();
	}
	
	public Item getItemFromString(String n){
		return laVille.getItemFromString(n);
	}
	
	public VilleJPanel getVille(){
		return laVille;
	}
	
	//Set
	public void setPorteMonnaie(int monnaie){
		porteMonnaie = monnaie;
	}
	
	//Ajouter
	public void ajouterPorteMonnaie(int monnaie){
		porteMonnaie += monnaie;
	}
	
	public boolean ajouterItem(Item i){ //Position aléatoire
		if (porteMonnaie - i.getPrix()>= 0){
			if (laVille.ajouter(i)){ //la ville est ajoutée dans la condition
				porteMonnaie -= i.getPrix();
				System.out.println("Ajout du batiment " + i + ", monnaie restant : " + porteMonnaie + " pims");
				return true;
			} else {
				System.out.println("Ajout impossible car l'emplacement occupe ");
				return false;
			}
		} else {
			i.retirerUnStatic();
			System.out.println("L'ajout du batiment " + i.getStringID() + " n'a pas pu etre fait car vous n'avez pas assez de monnaie : " + porteMonnaie + " pims restant");
			return false;
		}
	}
	
	public boolean ajouter(Item i, int x, int y){ //Position choisie, notamment pour les parcs, décorations, etc.
		if (porteMonnaie - i.getPrix()>=0){
			if (laVille.ajouter(i,x,y)){
				porteMonnaie -= i.getPrix();
				System.out.println("Ajout du batiment " + i + ", monnaie restant : " + porteMonnaie + " pims");
				return true;
			} else {
				System.out.println("Ajout impossible car l'emplacement occupe ");
				return false;
			}
		} else {
			i.retirerUnStatic();
			System.out.println("L'ajout de l'item " + i.getStringID() + " n'a pas pu etre fait car vous n'avez pas de assez de monnaie");
			return false;
		}
	}
	
	public void ajouterProduit(String n, Produit produit){
		((Boutique)getItemFromString(n)).ajouterProduit(produit);
	}
	
	//Retirer
	public void enleverItem(String b){
		porteMonnaie +=  (int)(( (Item)laVille.getItemFromString(b) ).getPrix()*0.2);
		laVille.enleverItem(b);
	}
	
	public void retirerProduit(String n, String nprod){
		((Boutique)getItemFromString(n)).retirerProduit( ((Boutique)getItemFromString(n)).getProduit(nprod));
	}
	
	//Autres
	public int releverLoyer(){
		return laVille.releverLoyer();
	}
	
	public void agrandirVille(){
		laVille.agrandirVille();
	}
	
	public int produitsConsommer(){
		int somme = 0;
		int nbHabs = laVille.getNbHabitants();
		int i, j;
		for (i=0;i<laVille.getNbCases();i++){
			for (j=0;j<laVille.getNbCases();j++){
				if (laVille.caseDeLaVille(i,j) instanceof Boutique){
					somme += (int)( ((Boutique)laVille.caseDeLaVille(i,j)).produitsConsommer(nbHabs)/3 );
					nbHabs -= ((Boutique)laVille.caseDeLaVille(i,j)).getNbClientMax();
				}
			}
		}
		ajouterPorteMonnaie(somme);
		return somme;
	}
	
	public void update(){
		//Actualisation des commerces au niveau production/réception UNIQUEMENT !!!
		int i, j;
		int n;
		
		for (i=0;i<laVille.getNbCases();i++){
			for (j=0;j<laVille.getNbCases();j++){
				if (laVille.caseDeLaVille(i,j) instanceof Commerce){
					if (laVille.caseDeLaVille(i,j) instanceof Boutique){ //Actualise la réception
						for (n=0;n<((Boutique)laVille.caseDeLaVille(i,j)).getNbProduits();n++){ //Nombre de produits qu'a la boutique
							if (((Boutique)laVille.caseDeLaVille(i,j)).getProduit(n) != null){ //Récupération du premier produit de la liste de la boutique
								if ( laVille.rechUsineCommerciale( (((Boutique)laVille.caseDeLaVille(i,j)).getProduit(n)) ) != null ){
									//Réception des produits de l'usine
									//Boutique : laVille.caseDeLaVille(i,j).reception( UsineCommercial )
									//Produit : (((Boutique)laVille.caseDeLaVille(i,j)).getProduit(n))
									//UsineCommercial : laVille.rechUsineCommercial( Produit ) et aussi livrer()
									( (Boutique)laVille.caseDeLaVille(i,j) ).reception( (laVille).rechUsineCommerciale( (((Boutique)laVille.caseDeLaVille(i,j)).getProduit(n)) ) );
									//Possible : laVille.rechUsineCommerciale( (((Boutique)laVille.caseDeLaVille(i,j)).getProduit(n)) ).livrer();
								}
							}
						}
					} else if (laVille.caseDeLaVille(i,j) instanceof UsineCommerciale){ //Actualise la production
						((UsineCommerciale)laVille.caseDeLaVille(i,j)).production();
					}
				}
			}
		}
	}
}