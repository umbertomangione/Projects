import java.util.*;
import java.io.*;

public class Eventi{
	LinkedList<Evento> listaEventi;
	LinkedList<Prenotazione> listaPrenotazione;
	BufferedReader tastiera;
	String ricavoTotaleEvento;
	String eventiPostiDisponibili;
	int	totalePrenotati;

	public Eventi(){
		listaEventi = new LinkedList<Evento>();
		tastiera = new BufferedReader(new InputStreamReader(System.in));
	}

	public synchronized void caricaEventi(String nomeFile){
		String identificativo;
	 	String descrizione;
		String data;
		String luogo;
		float costo;
		int maxPersone;

		try{
			BufferedReader fp = new BufferedReader(new FileReader(nomeFile));
		
			identificativo = fp.readLine();
			while (identificativo != null){
				descrizione = fp.readLine();
				data = fp.readLine();
				luogo = fp.readLine();
				costo = Float.parseFloat(fp.readLine());
				maxPersone = Integer.parseInt(fp.readLine());
				listaEventi.add(new Evento(identificativo, descrizione, data, luogo, costo, maxPersone));
				identificativo = fp.readLine();
			}	
		}catch(IOException e){
			System.out.println("Errore durante la lettura da file!!!");
			System.exit(-1);
		}
	}

	public synchronized void caricaPrenotazioni(String nomeFile){
		String nomeCognome;
		String codiceFiscale;
		String identificativoEvento;

		try{
			BufferedReader fp = new BufferedReader(new FileReader(nomeFile));
			
			identificativoEvento = fp.readLine();
			while (identificativoEvento != null){
				nomeCognome = fp.readLine();
				codiceFiscale = fp.readLine();
				
				for (Evento e: listaEventi){
					if (e.getIdentificativo().equals(identificativoEvento))
						e.aggiungiPrenotazione(new Prenotazione(nomeCognome, codiceFiscale));	
				}
				identificativoEvento = fp.readLine();
			}
		}catch(IOException e){
			System.out.println("Errore durante la lettura da file!!!");
			System.exit(-1);
		}
	}


	public synchronized void stampa(){
		
		for (Evento e: listaEventi)
			System.out.println(e);
	}

	public synchronized void aggiungiEvento() throws DuplicatedEventException{
		String identificativo;
	 	String descrizione;
		String data;
		String luogo;
		float costo;
		int maxPersone;

		try{
			System.out.println("Inserire i dati dell'evento");		
			System.out.print("Identificativo: ");
			identificativo = tastiera.readLine();
			System.out.print("Descrizione: ");
			descrizione = tastiera.readLine();
			System.out.print("Data: ");
			data = tastiera.readLine();
			System.out.print("Luogo: ");
			luogo = tastiera.readLine();
			System.out.print("Costo: ");
			costo = Float.parseFloat(tastiera.readLine());
			System.out.print("Numero massimo di persone: ");
			maxPersone = Integer.parseInt(tastiera.readLine());
		
			for (Evento e: listaEventi)
				if (e.getIdentificativo().equals(identificativo))
					throw new DuplicatedEventException();
		
			listaEventi.add(new Evento(identificativo, descrizione, data, luogo, costo, maxPersone));
		}catch(IOException e){
			System.out.println("Errore durante la lettura da file!!!");
			System.exit(-1);
		}

	}

	public synchronized void aggiungiPrenotazione() throws MaxPrenotazioniException{
		String nomeCognome;
		String codiceFiscale;
		String identificativoEvento;

		try{
			System.out.print("Inserire identificativo evento della prenotazione:");
			identificativoEvento = tastiera.readLine();
			System.out.print("Nome e Cognome: ");
			nomeCognome = tastiera.readLine();
			System.out.print("Codice Fiscale:");
			codiceFiscale = tastiera.readLine();
			
			for (Evento e: listaEventi){
				if (e.getIdentificativo().equals(identificativoEvento))
					if (e.isMaxPrenotazioni())
						throw new MaxPrenotazioniException();
					else{
						e.aggiungiPrenotazione(new Prenotazione(nomeCognome, codiceFiscale));
						break;
					}
			}
		}catch(IOException e){
			System.out.println("Errore durante la lettura da file!!!");
			System.exit(-1);
		}
	}

	public synchronized void cancellaEvento(){
		String identificativoEvento;
		float perdita;

		try{
			System.out.print("Inserire identificativo evento: ");
			identificativoEvento = tastiera.readLine();
			
			for (Evento e: listaEventi){
				if (e.getIdentificativo().equals(identificativoEvento)){
					listaPrenotazione = e.getListaPrenotazione();
					perdita = e.getCosto() * listaPrenotazione.size();
					System.out.println("Perdita economica dovuta alla cancellazione dell'evento: " + perdita);				
					listaEventi.remove(new Evento(identificativoEvento, "", "", "", 0.0F, 0));
				}
			}			
		}catch(IOException e){
			System.out.println("Errore durante la lettura da file!!!");
			System.exit(-1);
		}
	}

	public synchronized void ricercaPrenotazioni(){
		String codiceFiscale;
		
		try{
			System.out.print("Inserire il codice fiscale: ");
			codiceFiscale = tastiera.readLine();
			
			for (Evento e: listaEventi){
				if (e.isPrenotato(codiceFiscale))
					System.out.println("Evento in cui risulta prenotato: " + e.getIdentificativo());
			}

		}catch(IOException e){
			System.out.println("Errore durante la lettura da file!!!");
			System.exit(-1);
		}
	}

	public synchronized void ricavoPerEvento(){
		float ricavo;
		ricavoTotaleEvento = "";
	
		for (Evento e: listaEventi){
			ricavo = e.getCosto() * e.getNumeroPrenotati();
			ricavoTotaleEvento += ("ricavo evento: " + e.getIdentificativo() + " = " + ricavo + "\n");
		}
	}

	public synchronized void listaEventiConPostiDisponibili(){
		eventiPostiDisponibili = "";
	
		for (Evento e: listaEventi){
			if (!e.isMaxPrenotazioni())
				eventiPostiDisponibili += e.getIdentificativo() + "\n";
		}		
	}
		
	public synchronized void numeroTotalePersonePrenotate(){
		totalePrenotati = 0;

		for (Evento e: listaEventi)
			totalePrenotati += e.getNumeroPrenotati();			
	}

	public synchronized void stampaReport(){
		System.out.println("*****Ricavo per ciascun evento");
		System.out.println(ricavoTotaleEvento);
		System.out.println("*****Eventi con posti disponibili");
		System.out.println(eventiPostiDisponibili);
		System.out.println("*****Numero totale di persone prenotate");
		System.out.println(totalePrenotati);
	}
}
