import java.util.*;

public class ThreadFG extends Thread  {
	private Listasale l;
	public ThreadFG(ListaSale l) {this.l=l;}	
	public void run() {
		l.caricaDati();
		int scelta;
		while (true){
			do {
				System.out.println("1 - inserisci prenotazione");
				System.out.println("2 - cancella prenotazione");
				System.out.println("3 - cancella sala");
				System.out.println("4 - inserisci sala");
				System.out.println("5 - stampa report");
				System.out.println("0 - uscita");
				Scanner tastiera=new Scanner(System.in);
				System.out.println("inserisci scelta");
				try{ 		
					scelta=tastiera.nextInt();
				}
				catch (IOException e) { 
					System.out.println("I/O ERR"); 
					System.exit(-1);
				}
				switch (scelta){
					case 0: System.out.println("FINE...");System.exit(1);
					case 1: l.inserisciPrenotazione();break;
					case 2: l.cancellaPrenotazione();break;
					case 3: l.cancellaSala();break;
					case 4: l.inserisciSala();break;
					case 5: l.stampaReport();break();
					default: System.out.println("Scelta voce di menu non valida");
					}
			}
			while (scelta!=0);
		}
	}
}