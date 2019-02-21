import java.awt.*;
import java.util.Scanner;
import javax.swing.*;
public class TestL2City{
	private static final int TAILLE_CASE=20;
	private static final int NB_CASES=4;
	public static void main (String[] args) throws InterruptedException{
		JFrame f=new JFrame();//Création fenêtre graphique
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Variables de Scanner
		Scanner scan = new Scanner(System.in);
		Scanner scanproduit = new Scanner(System.in);
		
		//Début de partie avec pseudo, nom de ville et init du joueur
		System.out.println("Saisissez le pseudo :");
		String pseudo = scan.nextLine();
		System.out.println("Bienvenue sur L2City " + pseudo);
		System.out.println("Saisissez le nom de la ville :");
		String ville = scan.nextLine();
		System.out.println("Votre ville s'appelle " + ville);
		
		//Création du joueur et de sa ville
		Joueur J1 = new Joueur(pseudo, ville);
		
		System.out.println("Vous possedez au depart " + J1.getPorteMonnaie() + " pims qui est la monnaie du jeu");
		System.out.println("La taille de votre ville est de " + J1.getTailleVille() + " cases");
		
		//Variables enregistrant les Scanner
		String saisie, saisie2;
		int saisieEntier;
		
		//Variables "globales" qui peuvent être modifiés pour changer les paramètres par défaut du jeu	
		//Aller dans Joueur.java pour modifier la taille des cases et le nombre de cases au départ
		int Jour = 1;
		int prix_agrandirville = 500000;
		int prix_pain = 5;
		int prix_cereale = 9;
		int paquet = 20;
		int prod_max = 50;
		int prod_min = 20;
		int prix_amelioration = 300000;
		
		int porteMonnaie_depart = 1000000; //1000000 assez difficile, 3000000 normal, 5000000 facile
		int cout_entretien = 250000;
		
		int prix_RouteEnBitume = 150;
		int prix_RouteEnTerre = 50;
		int surface_RouteEnBitume = 50;
		int surface_RouteEnTerre = 50;
		
		//Variables à ne pas modifier
		int monnaie_precedente;
		String code_forme = "Les carres sont des batiments et routes, les cercles sont des decorations, parcs, fete foraine";
		String code_couleur_nonBatiment = "Parc : vert pomme\nFete Foraine : rose\nRoute en bitume : gris\nRoute en terre : marron";
		String code_couleur = code_forme + " Maison : vert\nImmeuble : vert fonce\nUsine : jaune\nBoutique : bleu \nUsine commercial : ambre" + " " +code_couleur_nonBatiment;
		int tmpX, tmpY;
		int erreur; //Détection d'erreur pour éviter les crashs (plantages)
		int randomizer; //Variables randomizer et randomtmp servent aux événements aléatoires 
		int randomtmp;
		
		//Application des paramètres
		J1.setPorteMonnaie(porteMonnaie_depart);
		
		System.out.println("\nTips : \nLaissez la saisie vide pour faire un retour");
		System.out.println("Les noms des commerces doivent commencer par une majuscule pour differencier les Batiments non commercial");
		System.out.println("\nCode couleur: ");
		System.out.println(code_couleur);
		System.out.println(); //Saut de ligne
		
		//Thread.sleep(5000);
		
		//Règles du jeu
		System.out.println("Le but du jeu est de ne pas avoir un solde inferieur a 0.");
		System.out.println("Vous perdez " + cout_entretien + " pims tous les 7 jours pour l'entretien");
		System.out.println("Des evenements aleatoires peuvent survenir");
		//Thread.sleep(5000);
		
		//Graphimes
		f.setContentPane(J1.getVille());//Ajout du panneau à la fenêtre
		f.setSize(900,900);
		//f.pack();//Adaptation de la fenêtre au panneau
		f.setVisible(true);//Affichage de la fenêtre
		
		do {
			System.out.println("Jour " + Jour);
			
			//Reset des variables
			monnaie_precedente = J1.getPorteMonnaie();
			tmpX = 0;
			tmpY = 0;
			erreur = 0;
			randomizer = (int)(Math.random()*(100+1));
			randomtmp = (int)(Math.random()*100);
			
			do {
				System.out.println("Affichage de votre ville :\n");
				J1.afficherVille();
				System.out.println(); //Saut de ligne
				
				//Affichage de tous les commerces
				J1.afficherCommercetoString();
				System.out.println(); //Saut de ligne
				
				System.out.println("-----------------------------------------------------------");
				System.out.println("Vous avez les choix suivants : ");
				System.out.println("Saisir build pour creer un nouveau batiment");
				System.out.println("Saisir showall pour afficher tous les batiments");
				System.out.println("Saisir commerce pour creer ou gerer un batiment commercial");
				System.out.println("Saisir route pour ajouter des routes");
				System.out.println("Saisir decoration pour ajouter des parcs, fete foraine");
				System.out.println("Saisir loyer pour relever le loyer des batiments");
				System.out.println("Saisir pims pour afficher vos pims");
				System.out.println("Saisir bulldozer pour detruire un batiment, vous recupererez 20% du prix d'achat");
				System.out.println("Saisir terrain pour voir les options d'agrandir le terrain");
				System.out.println("Saisir help pour voir les codes couleur, etc.");
				System.out.println("Saisir fin pour terminer le jour");
				System.out.println("Fermez la fenetre graphique pour quitter le jeu");
				//System.out.println("Saisir exit pour quitter le jeu");
				System.out.println("-----------------------------------------------------------");
				System.out.println("Votre solde : " + J1.getPorteMonnaie() + " pims");
				saisie = scan.nextLine();
				
				//Construction des bâtiments
				if (saisie.equals("build")){
					do{
						System.out.println("Que voulez-vous construire ? 'maison', 'immeuble' ou 'usine' ?");
						System.out.println("batiment 'aleatoire' ? Vous paierez 10% moins cher");
						saisie = scan.nextLine();
						if (saisie.equals("maison")){
							J1.ajouterItem(new Maison());
						} else if (saisie.equals("immeuble")){
							J1.ajouterItem(new Immeuble());
						} else if (saisie.equals("usine")){
							J1.ajouterItem(new Usine());
						} else if (saisie.equals("aleatoire")){
							Batiment generateur = Generateur.getNewBatiment();
							if (J1.ajouterItem(generateur)){
								J1.ajouterPorteMonnaie((int)(generateur.getPrix()*0.1));
							}
						} else {
							System.out.println("Saisie du batiment a construire incorrect. Ressaisissez");
						}
						(J1.getVille()).repaint();
					} while ( !( (saisie.equals("")) || ( saisie.equals("exit") ) ) );
				}
				
				//Showall
				if (saisie.equals("showall")){
					System.out.println("Affichage de showall : ");
					J1.afficherBatimenttoString();
				}
				
				//Construction de commerce
				if (saisie.equals("commerce")){
					do {
						J1.afficherVille();
						J1.afficherCommercetoString();
						System.out.println("Quel type de commerce voulez-vous creer ou gerer? 'boutique' ou 'usinecommerciale'?");
						saisie = scan.nextLine();
						if (saisie.equals("boutique")){
							System.out.println("creer ou gerer?");
							saisie = scan.nextLine();
							if (saisie.equals("creer")){
								System.out.println("Saisir le nom de la boutique");
								saisie = scan.nextLine();
								while (saisie.length() < 1 && saisie != "exit"){ //Si la saisie est vide, c'est incorrect
									System.out.println("Nom incorrect. Ressaisissez ou saisissez back pour faire un retour");
									saisie = scan.nextLine();
									continue;
								}
								if (!(saisie.equals("back")) ) J1.ajouterItem(new Boutique(saisie.toUpperCase()));
							} else if (saisie.equals("gerer")){
								System.out.println("Que voulez-vous faire ? 'ajouterproduit', 'retirerproduit', 'listeproduits' ?");
								saisie = scan.nextLine();
								if (saisie.equals("ajouterproduit")){
									J1.afficherVille();
									J1.afficherCommercetoString();
									System.out.println("Saisir l'ID de la boutique");
									saisie = scan.nextLine();
									if (J1.getItemFromString(saisie) != null && J1.getItemFromString(saisie) instanceof Boutique){
										System.out.println("Choisissez un produit que vous voulez ajouter parmis ces produits: ");
										System.out.println("'pain' , 'cereale' ?");
										saisie2 = scanproduit.nextLine();
										if (saisie2.equals("pain")){
											J1.ajouterProduit(saisie, new Pain(prix_pain));
										} else if (saisie2.equals("cereale")){
											J1.ajouterProduit(saisie, new Cereale(prix_cereale));
										}
									} else {
										System.out.println("Selection de la boutique incorrecte");
									}
								} else if (saisie.equals("retirerproduit")){
									J1.afficherVille();
									J1.afficherCommercetoString();
									System.out.println("Saisir l'ID de la boutique");
									saisie = scan.nextLine();
									if (J1.getItemFromString(saisie) != null && J1.getItemFromString(saisie) instanceof Boutique){
										System.out.println("Choisissez un produit que vous voulez retirer parmis ces produits: ");
										System.out.println("'pain' , 'cereale' ?");
										saisie2 = scanproduit.nextLine();
										if (saisie2.equals("pain")){
											J1.retirerProduit(saisie, saisie2);
										} else if (saisie2.equals("cereale")){
											J1.retirerProduit(saisie, saisie2);
										} else {
											System.out.println("Retrait du produit inexistant");
										}
									} else {
										System.out.println("Selection de la boutique incorrecte");
									}
								} else if (saisie.equals("listeproduits")){
									System.out.println("Liste des produits de quelle boutique ?");
									saisie = scan.nextLine();
									if (J1.getItemFromString(saisie) != null && J1.getItemFromString(saisie) instanceof Boutique) System.out.println( ( (Boutique)(J1.getItemFromString(saisie)) ).listeProduits() );
									else System.out.println("Boutique inexistante");
								} else {
									System.out.println("Saisie incorrecte");
								}
							} else {
								System.out.println("Saisie incorrecte. Recommencez");
							}
						} else if (saisie.equals("usinecommerciale")){
						System.out.println("creer ou gerer?");
							saisie = scan.nextLine();
							if (saisie.equals("creer")){
								J1.afficherVille();
								System.out.println("Saisir le nom de l'usine commerciale");
								saisie = scan.nextLine(); //Conserve le nom de l'usine commerciale
								System.out.println("Choisissez un produit que vous souhaitez produire parmis ces produits: ");
								System.out.println("'pain' , 'cereale' ?");
								saisie2 = scanproduit.nextLine(); //Entrée du produit
								if (saisie2.equals("pain")){
									J1.ajouterItem(new UsineCommerciale(saisie.toUpperCase(), new Pain(prix_pain), paquet, prod_max, prod_min));
								} else if (saisie2.equals("cereale")){
									J1.ajouterItem(new UsineCommerciale(saisie.toUpperCase(), new Cereale(prix_cereale), paquet, prod_max, prod_min));
								} else {
									System.out.println("Nom incorrect ou produit inexistant");
								}
							} else if (saisie.equals("gerer")){
								J1.afficherVille();
								J1.afficherCommercetoString();
								System.out.println("Selectionnez l'usine commerciale(ID)");
								saisie = scan.nextLine(); //Conserve l'ID de l'usine commerciale
								if (J1.getItemFromString(saisie) != null && J1.getItemFromString(saisie) instanceof UsineCommerciale){
									do {
										System.out.println("Que voulez-vous faire ? Changer le 'paquet', la 'prod_max', la 'prod_min', d'une usine commerciale de 5 ?");
										System.out.println("Chaque amelioration de caracteristique de l'usine commerciale coute " + prix_amelioration + " pims. Vous avez " + J1.getPorteMonnaie() + " pims");
										saisie2 = scan.nextLine();
										if (J1.getPorteMonnaie() >= prix_amelioration){
											if (saisie2.equals("paquet")){
												((UsineCommerciale)J1.getItemFromString(saisie)).ajouterPaquet(5);
												J1.setPorteMonnaie(J1.getPorteMonnaie() - prix_amelioration);
												System.out.println("Augmentation de paquet par 5");
											} else if (saisie2.equals("prod_max")){
												((UsineCommerciale)J1.getItemFromString(saisie)).ajouterProductionMax(5);
												J1.setPorteMonnaie(J1.getPorteMonnaie() - prix_amelioration);
												System.out.println("Augmentation de la production maximale de 5");
											} else if (saisie2.equals("prod_min")){
												((UsineCommerciale)J1.getItemFromString(saisie)).ajouterProductionMin(5);
												J1.setPorteMonnaie(J1.getPorteMonnaie() - prix_amelioration);
												System.out.println("Augmentation de la production minimale de 5");
											} else {
												System.out.println("Saisie incorrecte");
											}
										} else {
											System.out.println("Vous n'avez pas assez de pims pour ameliorer une usine commerciale");
											saisie2 = "";
										}
									} while ( !( (saisie2.equals("")) || ( saisie2.equals("exit") || (J1.getPorteMonnaie() < prix_amelioration) ) ) ); 
								} else {
									System.out.println("Selection de l'usine commerciale incorrecte");
								}
							} else {
								System.out.println("Saisie incorrecte");
							}
						} else {
							System.out.println("Saisie incorrecte. Ressaisissez");
						}
						(J1.getVille()).repaint();
					} while ( !( (saisie.equals("")) || ( saisie.equals("exit") ) ) );
				}
				
				//Ajout de route
				if (saisie.equals("route")){
					do {
						erreur = 0; //Reset au cas où
						J1.afficherVille();
						J1.afficherCommercetoString();
						System.out.println("Ajouter une route a quelle position x ?");
						saisie = scan.nextLine();
						try {
							tmpX = Integer.parseInt(saisie);
						} catch (NumberFormatException e){
							System.out.println("x : Ce n'est pas un entier !");
							erreur = 1;
						}
						if (erreur==0){
							System.out.println("A quelle position y ?");
							saisie = scan.nextLine();
							try {
								tmpY = Integer.parseInt(saisie);
							} catch (NumberFormatException e){
								System.out.println("y : Ce n'est pas un entier !");
								erreur = 1;
								continue;
							}
						}
						if ( (erreur == 0) && (tmpX < J1.getNbCases() && tmpY < J1.getNbCases())){
							System.out.println("Quel type de route voulez-vous ?");
							System.out.println("'bitume' a " + prix_RouteEnBitume + " pims ? 'terre' a " + prix_RouteEnTerre + " pims ?");
							saisie = scan.nextLine();
							if (saisie.equals("bitume")){
								J1.ajouter(new RouteEnBitume(surface_RouteEnBitume, prix_RouteEnBitume), tmpX, tmpY);
							} else if (saisie.equals("terre")){
								J1.ajouter(new RouteEnTerre(surface_RouteEnTerre, prix_RouteEnTerre), tmpX, tmpY);
							} else {
								System.out.println("Choix de la route incorrecte");
							}
						} else {
							if (erreur == 0){
								System.out.println("La position (x,y) est hors de la ville. Agrandissez la ville !");
							} else {
								System.out.println("Veuillez re-entrer les valeurs x et y");
							}
						}
						(J1.getVille()).repaint();
					} while ( !( (saisie.equals("")) || ( saisie.equals("exit") ) ) );
				}
				
				//Decoration de la ville
				if (saisie.equals("decoration")){
					do {
						erreur = 0; //Reset au cas où
						J1.afficherVille();
						J1.afficherCommercetoString();
						System.out.println("Saisir la position souhaitee en x");
						saisie = scan.nextLine();
						try {
							tmpX = Integer.parseInt(saisie);
							erreur = 0;
						} catch (NumberFormatException e){
							System.out.println("x : Ce n'est pas un entier !");
							erreur = 1;
							continue;
						}
						System.out.println("Saisir la position souhaitee en y");
						saisie = scan.nextLine();
						try {
							tmpY = Integer.parseInt(saisie);
							erreur = 0;
						} catch (NumberFormatException e){
							System.out.println("y : Ce n'est pas un entier !");
							erreur = 1;
							continue;
						}
						if ( erreur == 0 ){
							System.out.println("Que voulez-vous ajouter ?");
							System.out.println("'parc' , 'feteforaine' ?");
							saisie = scan.nextLine();
							if (saisie.equals("parc")){
								if ( (erreur == 0) && (tmpX < J1.getNbCases() && tmpY < J1.getNbCases())){
									J1.ajouter(new Parc(), tmpX, tmpY);
								} else {
									System.out.println("La position (x,y) est hors de la ville. Agrandissez la ville !");
								}
							} else if (saisie.equals("feteforaine")){
								if ( (erreur == 0) && (tmpX < J1.getNbCases() && tmpY < J1.getNbCases())){	
									J1.ajouter(new FeteForaine(), tmpX, tmpY);
								} else {
									System.out.println("La position (x,y) est hors de la ville. Agrandissez la ville !");
								}
							} else {
								System.out.println("Saisie de la decoration incorrecte");
							}
						} else {
							if (erreur == 0){
								System.out.println("La position (x,y) est hors de la ville. Agrandissez la ville !");
							} else {
								System.out.println("Veuillez re-entrer les valeurs x et y");
							}
						}
						(J1.getVille()).repaint();
					} while ( !( (saisie.equals("")) || ( saisie.equals("exit") ) ) );
				}
				
				
				//Relever de loyer
				if (saisie.equals("loyer")){
					System.out.println("Que voulez-vous faire ? 'relever' le loyer des batiments ?");
					saisie = scan.nextLine();
					if (saisie.equals("relever")){
						System.out.println("Relever de loyer : " + J1.releverLoyer());
						Thread.sleep(1500);
					} else {
						System.out.println("Saisie incorrecte");
					}
				}
				
				//Afficher pims
				if (saisie.equals("pims")){
					System.out.println("Votre solde : " + J1.getPorteMonnaie() + " pims");
					Thread.sleep(1000);
				}
				
				//Bulldozer
				if (saisie.equals("bulldozer")){
					System.out.println("Quel batiment voulez-vous detruire ?");
					J1.afficherVille();
					saisie = scan.nextLine();
					if (J1.getItemFromString(saisie) != null) {
						System.out.println("Item detruit");
						J1.enleverItem(saisie);
					} else {
						System.out.println("Item inexistant");
					}
				}
				
				//Terrain
				if (saisie.equals("terrain")){
					do {
						J1.afficherVille();
						J1.afficherCommercetoString();
						System.out.println("Vous allez agrandir de 1x1 le terrain, pour 500000 pims, etes-vous sur ? 'oui' ou 'non' ?");
						saisie = scan.nextLine();
						if (saisie.equals("oui")){
							if (J1.getPorteMonnaie() - prix_agrandirville >= 0 ){
								J1.agrandirVille();
								J1.ajouterPorteMonnaie(-prix_agrandirville);
							} else {
								System.out.println("Vous n'avez pas assez de pims pour agrandir la ville");
							}
						}
						(J1.getVille()).repaint();
					} while ( !( (saisie.equals("")) || ( saisie.equals("exit") ) || ( saisie.equals("non") ) ) );
				}
				
				//Help
				if (saisie.equals("help")){
					System.out.println("Couleurs par defaut :\n" + code_couleur + "\n");
					System.out.println("Laissez la saisie vide pour faire un retour sans mettre fin au jour");
					System.out.println("Les noms des commerces doivent commencer par une majuscule pour differencier les Batiments non commercial");
					Thread.sleep(5000);
				}
				
				//Exit
				if (saisie.equals("exit")){
					break;
				}
				
				//Graphismes
				(J1.getVille()).repaint(); //Met à jour l'affichage
				
				System.out.println(); //Saut de ligne
			} while ( !(saisie.equals("fin")) && !(J1.getPorteMonnaie()<0)); //Fin
		
		
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
		System.out.println("Les produits consommes vous ont rapporte: "+J1.produitsConsommer() + " pims");
		J1.update();
		
		//Frais
		if (Jour%8 == 0) {
			System.out.println("Vous payez le cout d'entretien");
			J1.ajouterPorteMonnaie(-cout_entretien);
		}
		
		//Evenement aleatoire
		if (randomizer < 1){ //1%
			System.out.println("C'est votre jour de chance, vous venez de gagner 25000 pims");
			J1.ajouterPorteMonnaie(25000);
		} else if (randomizer < 5){ //4%
			System.out.println("Une tornade vient de passer, vous payez 100000 pims pour les reparations");
			J1.ajouterPorteMonnaie(-100000);
		} else if (randomizer < 15 ){ //9%
			System.out.println("Il y a une invasion de zombie !");
			if (J1.getItemFromString("m" + randomtmp) != null && J1.getItemFromString("m" + randomtmp) instanceof Maison){
				System.out.println("La maison " + (J1.getItemFromString("m" + randomtmp)).toString() + " vient d'etre detruite par les zombies !!!");
				J1.enleverItem("m" + randomtmp);
			} else {
				System.out.println("Heureusement qu'aucune maison n'etait a l'endroit de l'invasion");
			}
		}
		
		System.out.println("************************************************************************************************");
		
		Thread.sleep(2000);
		if (saisie.equals("exit")){
			break;
		}
		} while ( !(saisie.equals("exit")) && !(J1.getPorteMonnaie()<0));
		
		if (J1.getPorteMonnaie()<0){
			System.out.println("Partie perdue");
		}
		System.out.println("Au revoir. A tres bientot :D !");
	}
}
