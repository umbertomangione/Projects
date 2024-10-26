package ...

import java.io.*;
import java.util.*;

public class ListaSale{
	private LinkedList<Sala> listas;
	private int numprenotazioni;
	private float ricavocomplessivo;
	private BufferedReader tastiera;
	
	public ListaSale() {
		listas=new LinkedList<Sala>();
		numprenotazioni=0;
		ricavocomplessivo=0.0f;
		tastiera=new BufferedReader(new InputStreamReader(System.in)); 
			//tastiera.readLine()
			// int x=Integer.parseInt(tastiera.readLine());
		//tastiera=new Scanner(System.in); 
			// tastiera.nextLine()
			// tastiera.nextInt();
		}
		
	//...get...set...
	
	//public String toString(){ return ...}
	
	public synchronized void caricaDati() {
		String fn;
		BufferedReader fp;
		BufferedReader fpp;
		try{
			System.out.println("nome del file? : ");
			fn = tastiera.readLine();
			fp = new BufferedReader(new FileReader(fn));
			String s;
			s=fp.readLine();
			while(s!=null){
				listas.add(new Sala(s, Integer.parseInt(fp.readLine()), Float.parseFloat(fp.readLine()));
				s=fp.readLine();
				}
			fp.close();
			System.out.println("nome del file prenotazioni? : ");
			fn = tastiera.readLine();
			fpp = new BufferedReader(new FileReader(fn));
			s=fpp.readLine();
			String ns;
			Iterator i;
			Sala sala;
			int posto;
			while(s!=null){
				ns=fpp.readLine();
				posto=Integer.parseInt(fpp.readLine());
				i=listas.iterator();
				while (i.hasNext()) {
					sala=(Sala)i.next();
					if (sala.getnome().equals(ns)) {
						sala.insprenotazione(new Prenotazione (s, posto));
						break();
					}
				} 
				s=fpp.readLine();
			}
		} catch(IOException e){
			//System.out.println("file non trovato, si procede all'inserimento dei valori da tastiera");
			//...tastiera.readLine()...
		}
		....
	}
	public synchronized void inserisciPrenotazione() {
		try{
			System.out.println("inserisci il nome della persona e il suo posto");
			Prenotazione p=new Prenotazione(tastiera.readLine(), Integer.parseInt(tastiera.readLine()));
			System.out.println("inserisci il nome della sala");
			String ns=tastiera.readLine();
			Iterator i=listas.iterator();
			Sala sala;
			while (i.hasNext()) {
				sala=(Sala)i.next();
				if (sala.getnome().equals(ns)) {
					if (sala.postiresidui()>0) { 
						sala.insprenotazione(p);
						break();
					}
					else throw MyException("non ci sono posti");
				}
			} 
		} catch(IOException e){
			//System.out.println("file non trovato, si procede all'inserimento dei valori da tastiera");
			//...tastiera.readLine()...
		}
	}	
	public synchronized void cancellaPrenotazione() { ... }
	public synchronized void cancellaSala() { ... }
	public synchronized void inserisciSala() { ... }
		
	
	public synchronized void totalePrenotazioni() {
		numprenotazioni=0;
		for (Sala s: listas) numprenotazioni+=s.postioccupati(); 
		}
	public synchronized void postiDisponibili() { 
		for (Sala s: listas) System.out.println("posti disponibili per la sala "+s.getnome()+" pari a "+s.postiresidui());
		}
	public synchronized void ricavocomplessivo() { 
		ricavocomplessivo=0;
		for (Sala s: listas) ricavocomplessivo+=s.postioccupati()*s.gettariffa();
		}
	public synchronized void stampaReport() {
		System.out.println(numprenotazioni);
		postiDisponibili();
		System.out.println(ricavocomplessivo);
		}
	}