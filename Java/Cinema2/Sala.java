package progetto;

import java.io.*;
import java.util.*;

public class Sala {
	private String nome;
	private int capienza;
	private float tariffa;
	private LinkedList<Prenotazione> prenotazioni;
	
	public Sala (String n, int c, float t) {
		nome=n;
		capienza=c;
		tariffa=t;
		prenotazione=new LinkedList<Prenotazione>();
		}
		
	//...get...set...
	public String getnome() {return nome;}
	public float gettariffa() {return tariffa;}
	
	public void insprenotazione(Prenotazione p) { prenotazioni.add(p); }
	public int postiresidui() { return capienza-prenotazioni.size(); }
	public int postioccupati() { return prenotazioni.size();}
	
	public String toString(){ return "sala "+ nome + ", con posti "+capienza+ "e tariffa "+tariffa; }
		
	}