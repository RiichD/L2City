import java.awt.*;
import javax.swing.*;
public class VilleJPanel extends JPanel{
	private String nom;
	private Item[][] terrain;
	private int tailleCase;
	
	public VilleJPanel(String nom, int nbCases){ //Ancien constructeur (Avant passage de Ville à VilleJPanel
		this.nom = nom;
		terrain = new Item[nbCases][nbCases];
	}
	
	public VilleJPanel(String nom, int nbCases, int tailleCase){
		this.nom = nom;
		terrain = new Item[nbCases][nbCases];
		setPreferredSize(new Dimension(nbCases*tailleCase, nbCases*tailleCase));
		this.tailleCase = tailleCase;
	}
	
	//Afficher
	public void afficherVille(){
		int i, j;
		for (j=0;j<terrain.length; j++){
			for (i=0;i<terrain.length;i++){
				if (terrain[i][j] instanceof Item){
					System.out.print(terrain[i][j].getStringID());
				} else {
					System.out.print(" . ");
				}
			}
			System.out.println();
		}
	}
	
	public void afficherBatimenttoString(){
		int i, j;
		for (j=0;j<terrain.length; j++){
			for (i=0;i<terrain.length;i++){
				if (terrain[i][j] instanceof Batiment){
					System.out.println(terrain[i][j].toString());
				}
			}
		}
	}
	
	public void afficherCommercetoString(){
		int i, j;
		for (j=0;j<terrain.length; j++){
			for (i=0;i<terrain.length;i++){
				if (terrain[i][j] instanceof Commerce){
					System.out.println(terrain[i][j].toString());
				}
			}
		}
	}
	
	public void afficherBatimentAEtage(int nbE){
		int i, j;
		for (j = 0 ; j< terrain.length ; j++){
			for (i = 0 ; i < terrain.length ; i++){
				if ( (terrain[i][j] instanceof Immeuble) && ((Immeuble)terrain[i][j]).getNbEtages()>= nbE){
					System.out.println(((Immeuble)terrain[i][j]).getStringID() + " possede : " + ((Immeuble)terrain[i][j]).getNbEtages() + " etages");
				} else if (terrain[i][j] instanceof Usine && ((Usine)terrain[i][j]).getNbEtages()>= nbE){
					System.out.println(((Usine)terrain[i][j]).getStringID() + " possede : " + ((Usine)terrain[i][j]).getNbEtages() + " etages");
				}
			}
		}
	}
	
	//Getter
	public int getTailleCase(){
		return tailleCase;
	}
	
	public Item getItemFromString(String b){
		int i, j;
		for (j = 0 ; j< terrain.length ; j++){
			for (i = 0 ; i < terrain.length ; i++){
				if ( terrain[i][j] instanceof Item && ( (terrain[i][j].getStringID()).equals(b) ) ){
					return terrain[i][j];
				}
			}
		}
		
		return null;
	}
	
	public int getNbHabitants(){
		int i, j;
		int somme = 0;
		for (j = 0 ; j< terrain.length ; j++){
			for (i = 0 ; i < terrain.length ; i++){
				if ( terrain[i][j] instanceof Habitation){
					somme += ((Habitation)terrain[i][j]).getNbHabitants();
				}
			}
		}
		
		return somme;
	}
	
	public int getNbCases(){
		return terrain.length;
	}
	
	//Ajouter
	public boolean ajouter(Item i){
		int x = (int)(Math.random()*terrain.length);
		int y = (int)(Math.random()*terrain.length);
		if (terrain[x][y] instanceof Item){
			i.retirerUnStatic();
			return false;
		}
		terrain[x][y] = i;
		return true;
	}
	
	public boolean ajouter(Item i, int x, int y){
		if (x >= terrain.length || y >= terrain.length) return false;
		if (terrain[x][y] instanceof Item){
			i.retirerUnStatic();
			return false;
		}
		terrain[x][y] = i;
		return true;
	}
	
	//toString
	public String toString(){
		return "Ville " + nom + " nbHab = " + compterHabitants();
	}
	
	//Clone
	public Item[][] clone(Item[][] terrain){
		Item[][] tmp = new Item[terrain.length+1][terrain.length+1];
		int i, j;
		for (j = 0 ; j< tmp.length ; j++){
			for (i = 0 ; i < tmp.length ;i++){
				if (i < terrain.length && j < terrain.length){
					if (terrain[i][j] instanceof Item){
						tmp[i][j] = ((Item)(terrain[i][j])).clone();
					}
				}
			}
		}
		return tmp;
	}
	
	//Autres
	public int releverLoyer(){
		int somme = 0;
		int i, j;
		for (j = 0 ; j< terrain.length ; j++){
			for (i = 0 ; i < terrain.length ; i++){
				//if (( !(terrain[i][j] instanceof Route) && !(terrain[i][j] instanceof Decoration) && !(terrain[i][j] instanceof Route) && !(terrain[i][j] instanceof Commerce)) && terrain[i][j] instanceof Batiment){
				if (terrain[i][j] instanceof Maison || terrain[i][j] instanceof Immeuble){
					somme = somme + ((Batiment)terrain[i][j]).getLoyer();
				}
			}
		}
		return somme;
	}
	
	public int compterHabitants(){
		int nbHabs = 0;
		int i, j;
		for (j = 0 ; j< terrain.length ; j++){
			for (i = 0 ; i < terrain.length ; i++){
				if (terrain[i][j] instanceof Habitation){
					nbHabs = nbHabs + ((Habitation)terrain[i][j]).getNbHabitants();
				}
			}
		}
		return nbHabs;
	}
	
	public int LoyerHabitation(){ //On récupère le loyer de tout habitations
		int somme = 0;
		int i, j;
		for (j = 0 ; j< terrain.length ; j++){
			for (i = 0 ; i < terrain.length ; i++){
				if ( (terrain[i][j] instanceof Habitation)){
					somme += ((Habitation)terrain[i][j]).getLoyer();
				}
			}
		}
		return somme;
	}
	
	public void enleverItem(String b){
		int i, j;
		for (j = 0 ; j< terrain.length ; j++){
			for (i = 0 ; i < terrain.length ; i++){
				if ( terrain[i][j] instanceof Item && ( (terrain[i][j].getStringID()).equals(b) ) ){
					terrain[i][j] = null;
				}
			}
		}
	}
	
	public void agrandirVille(){
		//terrain = clone(terrain);

		Item[][] tmp = new Item[terrain.length+1][terrain.length+1];
		for (int j = 0;j < terrain.length; j++){
			for (int i = 0; i<terrain.length; i++){
				if (terrain[i][j] instanceof Item) {
					tmp[i][j] = terrain[i][j];
				}
			}
		}
		terrain = tmp;
	}
	
	public Item caseDeLaVille(int x, int y){
		if (x < getNbCases() && y < getNbCases()){
			return terrain[x][y];
		} else {
			return null;
		}
	}
	
	public UsineCommerciale rechUsineCommerciale(Produit produit){
		int i, j;
		for (j=0;j<terrain.length; j++){
			for (i=0;i<terrain.length;i++){
				if (terrain[i][j] instanceof UsineCommerciale && ( ( ( (UsineCommerciale)terrain[i][j] ).getProduit() ).getNom() ).equals(produit.getNom())){
					return (UsineCommerciale)terrain[i][j];
				}
			}
		}
		
		return null;
	}
	
	public void paintComponent(Graphics g){
		for (int i = 0; i<terrain.length;i++){
			for (int j=0;j<terrain[i].length;j++){
				if (terrain[i][j] instanceof Batiment || terrain[i][j] instanceof Route){
					g.setColor(terrain[i][j].getColor());
					g.fillRect(i*tailleCase, j*tailleCase, tailleCase, tailleCase);
					g.setColor(new Color(0,0,0));
					g.drawString( terrain[i][j].getStringID() , i*tailleCase,  j*(tailleCase) + tailleCase/2);
				} else if (terrain[i][j] instanceof Decoration){
					g.setColor(terrain[i][j].getColor());
					g.fillOval(i*tailleCase, j*tailleCase, tailleCase,tailleCase);
					g.setColor(new Color(0,0,0));
					g.drawString( terrain[i][j].getStringID() , i*tailleCase,  j*(tailleCase) + tailleCase/2);
				} else {
					g.setColor(new Color(255,255,255));
					g.fillRect(i*tailleCase, j*tailleCase, tailleCase, tailleCase);
				}
			}
		}
	}
}