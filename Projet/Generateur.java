import java.awt.Color;
public class Generateur{
	public static Batiment getNewBatiment(){
		double gen = Math.random()*100;
		if (gen < 30 ){ //30%
			return new Maison();
		} else if (gen < 60){ //30%
			return new Immeuble();
		} else { //30
			return new Usine();
		}
	}
}