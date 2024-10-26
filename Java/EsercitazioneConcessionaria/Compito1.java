/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;

/**
 *
 * @author giuseppe
 */



public class Compito1 {

		static Concessionaria creaConcessionaria(BufferedReader bf){
			String nomeConcessionaria;
			int numVeicoli;

			try{
				System.out.println("Inserire il nome della concessionaria");
				nomeConcessionaria = bf.readLine();
				System.out.println("Inserire il numero massimo di vecioli della concessionaria");
				numVeicoli = Integer.parseInt(bf.readLine());
				return (new Concessionaria(nomeConcessionaria, numVeicoli));
			}catch(IOException e){
				System.out.println("ERRORE durante la lettura da tastiera");
				System.exit(-1);
			}
			return null;
		}

		static void aggiungiVeicolo(BufferedReader bf, Concessionaria []c, int nc){
			String nome;
			String targa;
			String marca;
    	String modello;
    	int cilindrata;
    	int anno;
    	float prezzo;
			int numeroPorte;
    	int numeroPosti;
			int carico;
			int scelta;

			try{
				System.out.println("Inserisci il nome della concessionaria a cui aggiungere il veicolo: ");
				nome = bf.readLine();
				for (int i = 0; i < nc; i++){
					if (c[i].getNome().equals(nome)){
						System.out.println("Inserire i dati del veicolo:");			
						System.out.println("Targa:");
						targa = bf.readLine();			
						System.out.println("Marca:");			
						marca = bf.readLine();			
						System.out.println("Modello:");			
						modello = bf.readLine();			
						System.out.println("Cilindrata:");			
						cilindrata = Integer.parseInt(bf.readLine());			
						System.out.println("Anno:");			
						anno = Integer.parseInt(bf.readLine());			
						System.out.println("Prezzo:");			
						prezzo = Float.parseFloat(bf.readLine());			
						System.out.println("Inserisci 0 per Automobile e 1 per Autocarro: ");
						scelta = Integer.parseInt(bf.readLine());
						if (scelta == 0){
								System.out.println("Numer Porte:");			
								numeroPorte = Integer.parseInt(bf.readLine());			
								System.out.println("Numero Posti:");			
								numeroPosti = Integer.parseInt(bf.readLine());			
								c[i].aggiungiVeicolo(new Automobile(targa, marca, modello, cilindrata, anno, prezzo, numeroPorte, numeroPosti));
						}
						else if (scelta == 1){
								System.out.println("Capacita di carico (kg):");			
								carico = Integer.parseInt(bf.readLine());
								c[i].aggiungiVeicolo(new Autocarro(targa, marca, modello, cilindrata, anno, prezzo, carico));
						}
						else{
								System.out.println("Scelta non corretta");			
						}
					}
				}
			}catch(IOException e){
				System.out.println("ERRORE durante la lettura da tastiera");
				System.exit(-1);
			}
		}

		static public int menu(BufferedReader bf){
			try{
				System.out.println("1. Creare una nuova concessionaria");
				System.out.println("2. Aggiungere un veicolo ad una concessionaria");
				System.out.println("3. Stampare la concessionaria con il maggior numero di veicoli");
				System.out.println("4. Stampare la concessionaria con l'autoveicolo più costoso");
				System.out.println("5. Rimuovere una concessionaria");
				System.out.println("6. Stampa le concessionarie");
				System.out.println("7. Uscita");
				System.out.println("-----> Inserisci la tua scelta");
				return (Integer.parseInt(bf.readLine()));
			}catch(IOException e){
				System.out.println("ERRORE durante la lettura da tastiera");
				System.exit(-1);
			}
			return -1;
		}

		public static void stampaConcessionariaMaggiorNumeroVeicoli(Concessionaria []c, int nc){
			int max, imax, nv;

			if (nc == 0){
				System.out.println("Non ci sono concessionarie attualmente disponibili");
				return;
			}

			max = c[0].getNumVeicoli();
			imax = 0;
			for (int i = 1; i < nc; i++){
				nv = c[i].getNumVeicoli();
				if (nv > max){
					max = nv;
					imax = i; 
				}
			}
			System.out.println("La concessionaria con il maggiore numero di veicoli è: " + c[imax].getNome());	
		}


		public static void stampaConcessionariaAutoveicoloCostoso(Concessionaria []c, int nc){
			int imax;
			float max, pv;

			if (nc == 0){
				System.out.println("Non ci sono concessionarie attualmente disponibili");
				return;
			}

			max = c[0].getPrezzoVeicoloCostoso();
			imax = 0;
			for (int i = 1; i < nc; i++){
				pv = c[i].getPrezzoVeicoloCostoso();
				if (pv > max){
					max = pv;
					imax = i; 
				}
			}
			System.out.println("La concessionaria con il veciolo più costoso è: " + c[imax].getNome());	
		}
			

		public static int rimuoviConcessionaria(BufferedReader bf, Concessionaria []c, int nc){
			String nome;

			try{
				System.out.println("Inserire il nome della concessionaria: ");
				nome = bf.readLine();
				for (int i = 0;  i < nc; i++){
					if (c[i].getNome().equals(nome)){
						c[i] = c[nc - 1];
						c[nc - 1] = null;
						return --nc;
					}
				}				
			}catch(IOException e){
				System.out.println("ERRORE durante la lettura da tastiera");
				System.exit(-1);
			}
			return -1;
		}


		public static void stampaConcessionarie( Concessionaria []c, int nc){
			System.out.println("Stampa di tutte le concessionarie:");
			for (int i = 0;  i < nc; i++)
				System.out.println(c[i]);
		}


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			Concessionaria c;
			Concessionaria concessionarie []; 
			int M = 0, scelta, numConcessionarie = 0;
			String str;

			System.out.println("Inserisci il numero M di concessionarie da gestire: ");
			try{
				str = bf.readLine();
				M = Integer.parseInt(str);
			}catch(IOException e){
				System.out.println("ERRORE durante la lettura da tastiera");
				System.exit(-1);
			}
			concessionarie = new Concessionaria[M];
			
			do{
				scelta = menu(bf);
				switch(scelta){
					case 1: c = creaConcessionaria(bf);
									concessionarie[numConcessionarie++] = c;
									break;

					case 2:	aggiungiVeicolo(bf, concessionarie, numConcessionarie);
									break;

					case 3:	stampaConcessionariaMaggiorNumeroVeicoli(concessionarie, numConcessionarie);
									break;

					case 4:	stampaConcessionariaAutoveicoloCostoso(concessionarie, numConcessionarie);
									break;

					case 5: numConcessionarie = rimuoviConcessionaria(bf, concessionarie, numConcessionarie);
									break;

					case 6:	stampaConcessionarie(concessionarie, numConcessionarie);
									break;

					case 7: System.out.println("Uscita dal programma.");
									break;

					default: 
								System.out.println("Inserire una scelta tra 1 e 7");
								break;
				}
			}while(scelta != 7);
    }
}
