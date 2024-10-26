package progetto;

import java.io.*;

public class Prenotazione {
	private String cliente;
	private int posto;

	public Prenotazione (String c, int p) {
		cliente=c;
		posto=p;
		}
		
	//...get...set...
	
	public String toString(){ return "prenotazione di "+cliente+" nel posto "+posto;}	
	
	}