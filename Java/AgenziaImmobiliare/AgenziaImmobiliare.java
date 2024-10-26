public class AgenziaImmobiliare{


	public static void main(String args[]){
		Immobili immobili = new Immobili();

		ThreadGestione threadGestore = new ThreadGestione(immobili);
		ThreadReport threadReport = new ThreadReport(immobili);
		
		threadGestore.start();
		threadReport.start();
	}

}
