public class TestCommerce{
	public static void main(String[] args){
		Boutique b1 = new Boutique("LAW");
		System.out.println(b1.toString());
		
		Pain pa1 = new Pain(5);
		System.out.println("Nb de pain : " + pa1.getQuantite());
		
		b1.ajouterProduit(pa1);
		UsineCommerciale u1 = new UsineCommerciale("PainProd", new Pain(5), 20, 50, 20);
		System.out.println("Nb prod : " + b1.getNbProduits());
		System.out.println("Liste prod : " + b1.listeProduits());
		
		System.out.println("Usine : " + u1.toString());
		u1.production();
		System.out.println("Usine : " + u1.toString());
		
		
		b1.reception(u1);
		
		System.out.println("Usine : " + u1.toString());
	}
}