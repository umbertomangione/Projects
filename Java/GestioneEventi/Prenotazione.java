public class Prenotazione{
	private String nomeCognome;
	private String codiceFiscale;

	public Prenotazione(String nc, String cf){
		nomeCognome = nc;
		codiceFiscale = cf;
	}

	public String getCodiceFiscale(){
		return codiceFiscale;
	}

	public String toString(){
		return ("Nome e Cognome: " + nomeCognome + "\n" +
						"Codice Fiscale: " + codiceFiscale);
	}
}
