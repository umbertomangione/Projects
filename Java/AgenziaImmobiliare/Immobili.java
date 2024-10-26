import java.util.*;
import java.io.*;

public class Immobili{
	private LinkedList<UnitaImmobiliare> listaImmobili;
	private BufferedReader tastiera;
	private int numeroImmobili;
	private float giroAffari;

	public Immobili(){
		listaImmobili = new LinkedList<UnitaImmobiliare>();
		tastiera = new BufferedReader(new InputStreamReader(System.in));
	}

	public synchronized void caricaDati(){
		BufferedReader fp;
		String fileName;
		String tipoImmobile;
		String identificativo;
		String descrizione;
		String indirizzo;
		String codiceFiscale;
		float mq;
		int numeroVani;
		float prezzoVendita;
		int piano, postoAuto;
		int numeroLivelli;
		float mqGiardino;
		boolean piscina;

		try{
			System.out.print("Inserire il nome del file contenente i dati: ");
			fileName = tastiera.readLine();
			fp = new BufferedReader(new FileReader(fileName));
			
			tipoImmobile = fp.readLine();
			while (tipoImmobile != null){
				identificativo = fp.readLine();
				descrizione = fp.readLine();
				indirizzo = fp.readLine();
				codiceFiscale = fp.readLine();
				mq = Float.parseFloat(fp.readLine());
				numeroVani = Integer.parseInt(fp.readLine());
				prezzoVendita = Float.parseFloat(fp.readLine());
				switch(Integer.parseInt(tipoImmobile)){
					case 0:
									piano = Integer.parseInt(fp.readLine());
									postoAuto = Integer.parseInt(fp.readLine());
									listaImmobili.add(new Appartamento(identificativo, descrizione, indirizzo, codiceFiscale, mq, numeroVani, 
																		prezzoVendita, piano, postoAuto));
									break;
					case 1:
									numeroLivelli = Integer.parseInt(fp.readLine());
									mqGiardino = Float.parseFloat(fp.readLine());
									listaImmobili.add(new VillaSchiera(identificativo, descrizione, indirizzo, codiceFiscale, mq, numeroVani, 
																		prezzoVendita, numeroLivelli, mqGiardino));
									break;
					case 3:
									numeroLivelli = Integer.parseInt(fp.readLine());
									mqGiardino = Float.parseFloat(fp.readLine());
									piscina = Boolean.parseBoolean(fp.readLine());
									listaImmobili.add(new VillaSingola(identificativo, descrizione, indirizzo, codiceFiscale, mq, numeroVani, 
																		prezzoVendita, numeroLivelli, mqGiardino, piscina));
									break;															
				}
				tipoImmobile = fp.readLine();		
			}
			fp.close();
		}catch(IOException e){
			System.out.println("ERRORE di I/O");
			System.exit(-1);
		}
	}

	public synchronized void inserimentoImmobile(){
		String tipoImmobile;
		String identificativo;
		String descrizione;
		String indirizzo;
		String codiceFiscale;
		float mq;
		int numeroVani;
		float prezzoVendita;
		int piano, postoAuto;
		int numeroLivelli;
		float mqGiardino;
		boolean piscina;

		try{
			System.out.println("Inserire tutti i dati dell'immobile.");
			System.out.print("Inserire 0 = appartamento, 1 = villa a schiera, 2 = villa singola: ");
			tipoImmobile = tastiera.readLine();
			System.out.print("Identificativo: ");
			identificativo = tastiera.readLine();	
			System.out.print("Descrizione: ");
			descrizione = tastiera.readLine();	
			System.out.print("Indirizzo: ");
			indirizzo = tastiera.readLine();	
			System.out.print("Codice Fiscale: ");
			codiceFiscale = tastiera.readLine();	
			System.out.print("Dimensioni (mq): ");
			mq = Float.parseFloat(tastiera.readLine());	
			System.out.print("Numero vani: ");
			numeroVani = Integer.parseInt(tastiera.readLine());	
			System.out.print("Prezzo di vendita: ");
			prezzoVendita = Float.parseFloat(tastiera.readLine());	
			switch(Integer.parseInt(tipoImmobile)){
				case 0:
									System.out.print("Piano: ");
									piano = Integer.parseInt(tastiera.readLine());	
									System.out.print("Numero posto auto: ");
									postoAuto = Integer.parseInt(tastiera.readLine());	
									listaImmobili.add(new Appartamento(identificativo, descrizione, indirizzo, codiceFiscale, mq, numeroVani, 
																		prezzoVendita, piano, postoAuto));
									break;
				case 1:
									System.out.print("Numero di livelli: ");
									numeroLivelli = Integer.parseInt(tastiera.readLine());	
									System.out.print("Dimensioni giardino (mq): ");
									mqGiardino = Float.parseFloat(tastiera.readLine());	
									listaImmobili.add(new VillaSchiera(identificativo, descrizione, indirizzo, codiceFiscale, mq, numeroVani, 
																		prezzoVendita, numeroLivelli, mqGiardino));
									break;
				case 2:
									System.out.print("Numero di livelli: ");
									numeroLivelli = Integer.parseInt(tastiera.readLine());	
									System.out.print("Dimensioni giardino (mq): ");
									mqGiardino = Float.parseFloat(tastiera.readLine());	
									System.out.print("Piscina presente (true/false)?: ");
									piscina = Boolean.parseBoolean(tastiera.readLine());	
									
									listaImmobili.add(new VillaSingola(identificativo, descrizione, indirizzo, codiceFiscale, mq, numeroVani, 
																		prezzoVendita, numeroLivelli, mqGiardino, piscina));
									break;
			}
		}catch(IOException e){
			System.out.println("ERRORE di I/O");
			System.exit(-1);
		}
	}

	public synchronized void ricercaImmobili(){
		String cf;

		try{
			System.out.print("Inserire il codice fiscale del proprietario degli immobili: ");
			cf = tastiera.readLine();
			
			for (UnitaImmobiliare u: listaImmobili){
				if (u.getCodiceFiscale().equals(cf))
					System.out.println(u);
			}
		}catch(IOException e){
			System.out.println("ERRORE di I/O");
			System.exit(-1);
		}
		
	}

	public synchronized void numeroTotaleImmobili(){
		numeroImmobili = listaImmobili.size(); 
	}

	public synchronized void giroAffariAgenzia(){
		giroAffari = 0.0F;
		
		for (UnitaImmobiliare u: listaImmobili)
			giroAffari += u.getPrezzoVendita();
	}

	public synchronized void stampa(){
		for (UnitaImmobiliare u: listaImmobili)
			System.out.println(u);
	}

	public synchronized void stampaReport(){
		System.out.println("Numero toale immobili gestiti: " + numeroImmobili);
		System.out.println("Giro di affari agenzia immobiliare: " + giroAffari);
	}
}
