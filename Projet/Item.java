import java.awt.Color;
public interface Item {
	public abstract String getStringID();
	public abstract Color getColor();
	public abstract Color setColor(int r, int g, int b);
	public abstract Item clone();
	public abstract int getPrix();
	public abstract void retirerUnStatic(); //Si l'ajout d'un Item a échoué, on n'incrémente pas. Pour compenser les constructeurs qui incrémente l'id, on fait une méthode pour décrémenter.
}
