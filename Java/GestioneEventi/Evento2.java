import java.util.*;

public class Evento{
	private String identificativo;
	private String descrizione;
	private String data;
	private String luogo;
	private float costo;
	private int maxPersone;
	private LinkedList<Prenotazione> listaPrenotazioni;

	public Evento(String id, String desc, String d, String l, float c, int m){
		identificativo = id;
		descrizione = desc;
		data = d;
		luogo = l;
		costo = c;
		maxPersone = m;
		listaPrenotazioni = new LinkedList<Prenotazione>();
	}

	public String getIdentificativo(){
		return identificativo;
	}

	public LinkedList<Prenotazione> getListaPrenotazione(){
		return listaPrenotazioni;
	}

	public int getNumeroPrenotati(){
		return listaPrenotazioni.size();
	}

	public float getCosto(){
		return costo;
	}

	public void aggiungiPrenotazione(Prenotazione p){
		listaPrenotazioni.add(p);
	}

	public boolean isMaxPrenotazioni(){
		return (maxPersone == listaPrenotazioni.size());
	}

	public String toString(){
		String result = "Identificativo: " + identificativo + "\n" +
										"Descrizione: "		 + descrizione + "\n" +
										"Data:"						 + data + "\n" +	
										"Luogo: "					 + luogo + "\n" +
										"Costo: "					 + costo + "\n" +
										"Numero massimo persone: " + maxPersone + "\n";
		result += "Lista dei prenotati per l'evento\n";
		for (Prenotazione p: listaPrenotazioni)
				result += (p + "\n");
		
		return result;
	}

	public boolean equals(Object o){
		return (identificativo.equals(((Evento)o).identificativo));
	}

	public boolean isPrenotato (String cf){
		for (Prenotazione p: listaPrenotazioni){
			if (p.getCodiceFiscale().equals(cf))
				return true;
		}
		return false;
	}
}
