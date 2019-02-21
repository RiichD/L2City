import java.awt.*;
import javax.swing.*;
public class TestVilleGraph{
	private static final int TAILLE_CASE=20;
	private static final int NB_CASES=20;
	public static void main (String[] args) throws InterruptedException{
		JFrame f=new JFrame();//Création fenêtre graphique
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Création de la ville(qui est aussi un panneau)
		VilleJPanel villeJPanel = new VilleJPanel("L2City",NB_CASES,TAILLE_CASE);
		villeJPanel.afficherVille();
		f.setContentPane(villeJPanel);//Ajout du panneau à la fenêtre
		f.pack();//Adaptation de la fenêtre au panneau
		f.setVisible(true);//Affichage de la fenêtre
		
		for(int i=0;i<10;i++){
			Thread.sleep(100);//Ralenti l’affichage
			villeJPanel.ajouter(new Maison());
			villeJPanel.ajouter(new Usine());
			//...
			villeJPanel.repaint();//Redessine le graphique
		}
	}
}
