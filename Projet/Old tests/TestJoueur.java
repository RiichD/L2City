import java.util.Scanner;
// Pour tout importer : import java.util.*;
public class TestJoueur{
	public static void main(String[] args){
		
		// Début de partie avec pseudo, nom de ville et init du joueur
		Scanner scan = new Scanner(System.in);
		Scanner scanproduit = new Scanner(System.in);
		System.out.println("Saisissez le pseudo :");
		String pseudo = scan.nextLine();
		System.out.println("Bienvenue sur L2City " + pseudo);
		System.out.println("Saisissez le nom de la ville :");
		String ville = scan.nextLine();
		System.out.println("Votre ville s'appelle " + ville);
		
		Joueur J1 = new Joueur(pseudo, ville);
		
		System.out.println("Vous possedez au depart " + J1.getPorteMonnaie() + " pims qui est la monnaie du jeu");
		System.out.println("La taille de votre ville est de " + J1.getTailleVille() + " cases");
		String saisie, saisie2;
		
		//Variables "globales" qui peuvent être modifiés pour changer les paramètres par défaut du jeu
		int Jour = 1;
		int prix_agrandirville = 500000;
		int prix_pain = 5;
		int paquet = 20;
		int prod_max = 50;
		int prod_min = 20;
		int porteMonnaie_depart = 1000000;
		
		//Variables à ne pas modifier
		int monnaie_precedente;
		
		//Application des paramètres
		J1.setPorteMonnaie(porteMonnaie_depart);
		
		
		System.out.println("Tips : \nLaissez la saisie vide pour faire un retour");
		System.out.println("Les noms des commerces doivent commencer par une majuscule pour differencier les Batiments non commercial");
		System.out.println(); //Saut de ligne
		
		do {
			System.out.println("Jour " + Jour);
			
			//Reset des variables
			monnaie_precedente = J1.getPorteMonnaie();
			do {
				System.out.println("Affichage de votre ville :\n");
				J1.afficherVille();
				System.out.println(); //Saut de ligne
				
				//Affichage de tous les commerces
				J1.afficherCommerce();
				System.out.println(); //Saut de ligne
				
				System.out.println("-----------------------------------------------------------");
				System.out.println("Vous avez les choix suivants : ");
				System.out.println("Saisir build pour creer un nouveau batiment");
				System.out.println("Saisir commerce pour creer ou gerer un batiment commercial.");
				System.out.println("Saisir loyer pour relever le loyer des batiments");
				System.out.println("Saisir pims pour afficher vos pims");
				System.out.println("Saisir bulldozer pour detruire un batiment, vous recupererez 20% du prix d'achat");
				System.out.println("Saisir terrain pour voir les options d'agrandir le terrain");
				System.out.println("Saisir fin pour terminer le jour");
				System.out.println("Saisir exit pour quitter le jeu");
				System.out.println("-----------------------------------------------------------");
				System.out.println("Votre solde : " + J1.getPorteMonnaie() + " pims");
				saisie = scan.nextLine();
				
				// System.out.println("Vous avez saisi " + saisie); //Debug
				
				//Construction des bâtiments
				if (saisie.equals("build")){
					System.out.println("Que voulez-vous construire ? 'maison', 'immeuble' ou 'usine' ?");
					System.out.println("batiment 'aleatoire' ? Vous paierez 10% moins cher");
					saisie = scan.nextLine();
					if (saisie.equals("maison")){
						J1.ajouterBatiment(new Maison());
					} else if (saisie.equals("immeuble")){
						J1.ajouterBatiment(new Immeuble());
					} else if (saisie.equals("usine")){
						J1.ajouterBatiment(new Usine());
					} else if (saisie.equals("aleatoire")){
						Batiment generateur = Generateur.getNewBatiment();
						J1.ajouterBatiment(generateur);
					}
				}
				
				//Construction de commerce
				if (saisie.equals("commerce")){
					System.out.println("Quel type de commerce voulez-vous creer ou gerer? 'boutique' ou 'usinecommercial'?");
					saisie = scan.nextLine();
					if (saisie.equals("boutique")){
						System.out.println("creer ou gerer?");
						saisie = scan.nextLine();
						if (saisie.equals("creer")){
							System.out.println("Saisir le nom de la boutique");
							saisie = scan.nextLine();
							J1.ajouterBatiment(new Boutique(saisie));
						} else if (saisie.equals("gerer")){
							System.out.println("Que voulez-vous faire ? 'ajouterproduit', 'retirerproduit', 'listeproduits' ?");
							saisie = scan.nextLine();
							
							if (saisie.equals("ajouterproduit")){
								System.out.println("Choisissez un produit que vous ajouter parmis ces produits: ");
								System.out.println("'pain' ?");
								saisie2 = scanproduit.nextLine();
								if (saisie2.equals("pain")){
									System.out.println("Dans quelle boutique ?");
									saisie = scan.nextLine();
									J1.ajouterProduit(saisie, new Pain(prix_pain));
								}
							} else if (saisie.equals("listeproduits")){
								System.out.println("Liste des produits de quelle boutique ?");
								saisie = scan.nextLine();
								System.out.println( ( (Boutique)(J1.getBatimentFromString(saisie)) ).listeProduits() );
							}
						}
					} else if (saisie.equals("usinecommercial")){
						System.out.println("creer ou gerer?");
						saisie = scan.nextLine();
						if (saisie.equals("creer")){
							System.out.println("Saisir le nom de l'usine commercial");
							saisie = scan.nextLine(); //Conserve le nom de l'usine
							System.out.println("Choisissez un produit que vous souhaitez produire parmis ces produits: ");
							System.out.println("'pain' ?");
							saisie2 = scanproduit.nextLine(); //Entrée du produit
							if (saisie2.equals("pain")){
								J1.ajouterBatiment(new UsineCommerciale(saisie, new Pain(prix_pain), paquet, prod_max, prod_min));
							}
						} else if (saisie.equals("gerer")){
							
						}
					}
				}
				
				//Relever de loyer
				if (saisie.equals("loyer")){
					System.out.println("Que voulez-vous faire ? 'relever' le loyer des batiments ?");
					saisie = scan.nextLine();
					if (saisie.equals("relever")){
						System.out.println("Relever de loyer : " + J1.releverLoyer());
					}
				}
				
				//Afficher pims
				if (saisie.equals("pims")){
					System.out.println("Votre solde : " + J1.getPorteMonnaie() + " pims");
				}
				
				//Bulldozer
				if (saisie.equals("bulldozer")){
					System.out.println("Quel batiment voulez-vous detruire ?");
					J1.afficherVille();
					saisie = scan.nextLine();
					J1.enleverBatiment(saisie);
				}
				
				//Terrain
				if (saisie.equals("terrain")){
					System.out.println("Vous allez agrandir de 1x1 le terrain, pour 500000 pims, etes-vous sur ? 'oui' ou 'non' ?");
					saisie = scan.nextLine();
					if (saisie.equals("oui")){
						if (J1.getPorteMonnaie() - prix_agrandirville >= 0 ){
							J1.agrandirVille();
							J1.ajouterPorteMonnaie(-prix_agrandirville);
						} else {
							System.out.println("Vous n'avez passez assez de pims pour agrandir la ville");
						}
					}
				}
				
				//Exit
				if (saisie.equals("exit")){
					break;
				}
				
				System.out.println(); //Saut de ligne
			} while ( !(saisie.equals("fin"))); //Fin
		
		
		//Fin du jour
		System.out.println("************************************************************************************************");
		System.out.println("Il y a " + J1.getNbHabitants() + " habitants dans votre ville");
		
		
		
		//Ajout des monnaies liées aux loyers et aux dépenses
		System.out.println("Fin du Jour " + Jour + " : " + J1.releverLoyer() + " benefices");
		System.out.println("Vous avez depense : " + ((int)(J1.getPorteMonnaie()) - monnaie_precedente) + " pims");
		J1.ajouterPorteMonnaie(J1.releverLoyer());
		Jour++;
		
		//Ajout des monnaies liées aux commerces
		System.out.println("Revenu lie au commerce :");
		System.out.println("Produits consommes vous a rapporte: "+J1.produitsConsommer() + " pims");
		J1.update();
		
		//Frais des commerces
		
		System.out.println("\n************************************************************************************************");
		} while ( !(saisie.equals("exit")));
		
		System.out.println("Au revoir. A tres bientot :D !");
	}
}