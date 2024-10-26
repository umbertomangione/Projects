import java.io.*;

public class ThreadGestione extends Thread{
	private Immobili immobili;
	private BufferedReader tastiera;

	public ThreadGestione(Immobili im){
		immobili = im;
		tastiera = new BufferedReader(new InputStreamReader(System.in));
	}


	public int menu(){
	int scelta =-1;

		try{
			System.out.print(	"****Menu\n" 										+ 
											"1. Inserimento nuovo immobile\n" +
											"2. Ricerca immobile\n" 					+
											"3. Stampa report\n" 							+
											"4. Stampa lista immobili\n" 			+ 
											"5. Uscita\n"											+
											"---> Inserisci la tua scelta: ");

			scelta = Integer.parseInt(tastiera.readLine());
		}catch (IOException e){
				System.out.println ("ERRORE di I/O");
				System.exit(-1);
		}	
		return scelta;
	}

	public void run(){

		immobili.caricaDati();

		while(true){
			switch(menu()){
				case 1:
								immobili.inserimentoImmobile();
								break;
				case 2:
								immobili.ricercaImmobili();
								break;
				case 3:
								immobili.stampaReport();
								break;
				case 4:
								immobili.stampa();
								break;	
				case 5: 
								System.out.println ("Uscita dal programma!!!");
								System.exit(1);
				default:
								System.out.println("Scelta voce di menu non valida");			

			}
		}		

	}

}
