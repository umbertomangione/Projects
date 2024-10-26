package Esercizi_Esame.Viaggi;
import java.util.*;

public class PacchettoViaggio {
    private String Identificativo;
    private String Descrizione;    
    private float CostoP;
    private int MaxPersone;
    private LinkedList<Prenotazione> ListaPrenotazioni;
    
    public PacchettoViaggio(String i, String d, float cp, int max){
        this.Identificativo = i;
        this.Descrizione = d;
        this.CostoP = cp;
        this.MaxPersone = max;
        ListaPrenotazioni = new LinkedList<Prenotazione>();
    }
    
    public void addPrenotazione(Prenotazione p){
        ListaPrenotazioni.add(p);
    }
    
    public String getIdentificativo(){
        return Identificativo;
    }
    
    public int NumeroPrenotati(){
        return ListaPrenotazioni.size();
    }
    
    public float CostoTotalePrenotazioni(){
        return (CostoP * ListaPrenotazioni.size());
    }
    
    public int PostiDisponibili(){
        return (MaxPersone - ListaPrenotazioni.size());
    }
    
    public LinkedList<Prenotazione> getListaPrenotazioni(){
        return ListaPrenotazioni;
    }
    
    public boolean isMax(){
        return (MaxPersone == ListaPrenotazioni.size());
    }
    
    public boolean equals(Object o){
        return (Identificativo.equals(((PacchettoViaggio)o).Identificativo));
    }
    
    public String toString(){
        String result;
        result = "Identificativo Viaggio : " + Identificativo + "\n" +
                 "Descrizione : " + Descrizione + "\n" +
                 "Costo per persona : " + CostoP + "\n" +
                 "Numero massimo di Prenotazioni : " + MaxPersone + "\n";
        result += "\n----LISTA DEI PRENOTATI----\n";
        for(Prenotazione p: ListaPrenotazioni)
            result += p;
        return result;
    }
    
    public String SecondaLista(){
        String result;
        result = "Identificativo Viaggio : " + Identificativo + "\n" +
                 "Descrizione : " + Descrizione + "\n" +
                 "Costo per persona : " + CostoP + "\n" +
                 "Numero massimo di Prenotazioni : " + MaxPersone + "\n";
        return result;
    }
}
