public class GestioneEventi{

	public static void main(String args[]){
		Eventi e = new Eventi();

		ThreadInterattivo ti = new ThreadInterattivo(e);
		ThreadReport tr = new ThreadReport(e);

		ti.start();
		tr.start();

	}

}
