public abstract class TestInterface implements AEtage{
	public static void main(String[] args){
		Immeuble Ii1 = new Immeuble();
		System.out.println(Ii1.getNbEtages());
		
		// Même test que dans TestJeu.java
		// Test de Bâtiment avant l'ajout d'abstract
		
		System.out.println("Avant : " + Batiment.getnbBatiments());
		//Batiment B1 = new Batiment("Raven", 100);
		//System.out.println("Apres : " + Batiment.getnbBatiments());
		//System.out.println(B1.toString());
		System.out.println(); //Saut de ligne pour lisibilité
		
		// Test de Habitation avant l'ajout d'abstract
		/*
		Habitation H1 = new Habitation("Tour Berg", 50);
		System.out.println("Nombre d'habitants : " + H1.getNbHabitants());
		System.out.println(H1.toString());
		System.out.println("NbBats : " + Batiment.getnbBatiments());
		System.out.println("Id : " + H1.getStringID());
		System.out.println();
		*/
		// Test de Maison
		
		// Partie 1 - Test de Maison, Immeuble, Usine
		Maison M1 = new Maison();
		System.out.println(M1.toString());
		System.out.println("Loyer : " + M1.getLoyer() + " pims ");
		System.out.println("Id : " + M1.getStringID());
		
		Immeuble I1 = new Immeuble();
		System.out.println(I1.toString());
		System.out.println("NbEtages de l'immeuble: " + I1.getNbEtages());
		System.out.println("Loyer : " + I1.getLoyer() + " pims ");
		System.out.println("Id : " + I1.getStringID());
		
		Usine U1 = new Usine();
		System.out.println(U1.toString());
		System.out.println(U1.getNbEtages());
		System.out.println("Loyer : " + U1.getLoyer() + " pims ");
		System.out.println("Id : " + U1.getStringID());
		
		// Partie 2 - Test de Ville
		Ville V1 = new Ville("L2City", 4);
		V1.ajouter(M1);
		V1.ajouter(I1);
		V1.ajouter(U1);
		V1.afficherVille();
		System.out.println("Somme des loyers des batiments de la ville : " + V1.releverLoyer());
		System.out.println("Nombre d'habitants dans la ville : " + V1.compterHabitants());
		V1.afficherBatimentAEtage(2);
		System.out.println(V1.toString());
		
		System.out.println("\nGeneration : "); //Saut de ligne
		//Q8. Generateur
		Batiment b1 = Generateur.getNewBatiment();
		System.out.println(b1.toString());
		System.out.println(Generateur.getNewBatiment().toString());
	}
}