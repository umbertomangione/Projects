public abstract class UnitaImmobiliare{
	private String identificativo;
	private String descrizione;
	private String indirizzo;
	private String codiceFiscale;
	private float mq;
	private int numeroVani;
	private float prezzoVendita;

	public UnitaImmobiliare(String id, String desc, String ind, String cf, float metri, int nv, float pv){
		identificativo = id;
		descrizione = desc;
		indirizzo = ind;
		codiceFiscale = cf;
		mq = metri;
		numeroVani = nv;
		prezzoVendita = pv;
	}

	public String toString(){

		return ("Identificativo: " 	+ identificativo		+ "\n" +
						"Descrizione: " 		+ descrizione 			+ "\n" +
						"Indirizzo: " 			+ indirizzo 				+ "\n" +
						"Codice Fiscale: " 	+ codiceFiscale 		+ "\n" +
						"Dimensione(mq): " 	+ mq						 		+ "\n" +
						"Numero vani: "		 	+ numeroVani		 		+ "\n" +
						"Prezzo vendita: "	+ prezzoVendita			
					);
	}

	public String getCodiceFiscale(){
		return codiceFiscale;
	}	

	public float getPrezzoVendita(){
		return prezzoVendita;
	}
}
