import java.io.*;

public class ThreadInterattivo extends Thread{
	Eventi gestoreEventi;
	BufferedReader tastiera;	

	public ThreadInterattivo(Eventi e){
		gestoreEventi = e;	
		tastiera = new BufferedReader(new InputStreamReader(System.in));
	}

	public int menu(){
		int scelta = -1;
		try{
			System.out.print(	"****Menu\n" +
													"1. Inserimento nuovo evento\n" + 
													"2. Inserimento prenotazione\n" +
													"3. Cancellazione evento\n" 		+
													"4. Ricerca prenotazioni\n" 		+
													"5. Stampa report\n" 						+
													"6. Stampa\n" 									+
													"7. Uscita\n"										+
													"--->Inserire la scelta: ");
			scelta = Integer.parseInt(tastiera.readLine());
		}catch(IOException e){
			System.out.println("Errore di I/O!!!");
			System.exit(-1);
		}
		return scelta;
	}


	public void run(){
		String nomeFileEventi, nomeFilePrenotazioni;
		
		try{
			System.out.print("Inserire il nome del file degli eventi: ");
			nomeFileEventi = tastiera.readLine();
			System.out.print("Inserire il nome del file delle prenotazioni: ");
			nomeFilePrenotazioni = tastiera.readLine();
			gestoreEventi.caricaEventi(nomeFileEventi);
			gestoreEventi.caricaPrenotazioni(nomeFilePrenotazioni);
			
			while (true){
				switch(menu()){
					case 1:
									try{
										gestoreEventi.aggiungiEvento();
										break;
									}catch(DuplicatedEventException e){
										System.out.println("Inserito un evento con identificativo già presente");
									}
					case 2:
									try{
										gestoreEventi.aggiungiPrenotazione();
										break;
									}catch(MaxPrenotazioniException e){
										System.out.println("Inserito un evento con identificativo già presente");
									}
					case 3:
									gestoreEventi.cancellaEvento();
									break;
					case 4:
									gestoreEventi.ricercaPrenotazioni();
									break;
					case 5:
									gestoreEventi.stampaReport();
									break;
					case 6:
									gestoreEventi.stampa();
									break;
					case 7:
									System.out.println("Bye Bye!!!");
									System.exit(1);
					default:
									System.out.println("Scelta non consentita!!!");
				}
			}
		}catch(IOException e){
			System.out.println("Errore di I/O!!!");
			System.exit(-1);
		}	


	}

}
