package Esercizi_Esame.Palestra;
import java.util.*;

public class Corso {
    private String NomeCorso;
    private String NomeIstruttore;
    private int CapienzaMax;
    private float TariffaM;
    private LinkedList<Prenotazione> ListaPrenotazioni;
    
    public Corso(String nc, String ni, int cm, float tm){
        this.NomeCorso = nc;
        this.NomeIstruttore = ni;
        this.CapienzaMax = cm;
        this.TariffaM = tm;
        ListaPrenotazioni = new LinkedList<Prenotazione>();
    }
    
    public String getNomeCorso(){
        return NomeCorso;
    }
    
    public void addPrenotazione(Prenotazione p){
        ListaPrenotazioni.add(p);
    }
    
    public boolean isMax(){
        return (CapienzaMax == ListaPrenotazioni.size());
    }
    
    public String toString(){
        String result;
        result = "Nome del Corso : " + NomeCorso +"\n" +
                 "Nome dell'Istruttore : " + NomeIstruttore + "\n" +
                 "Capienza massima del corso : " + CapienzaMax + "\n" +
                 "Tariffa Mensile del corso : " + TariffaM + "\n";
        result += "\n--  Lista Prenotati al Corso  --\n";
        for(Prenotazione p: ListaPrenotazioni)
            result += p;
        return result;
    }
    
    public boolean equals(Object o){
        return (NomeCorso.equals(((Corso)o).NomeCorso));
    }
    
    public LinkedList<Prenotazione> getListaPrenotazioni(){
        return ListaPrenotazioni;
    }
    
    public int PostiPrenotati(){
        return ListaPrenotazioni.size();
    }
    
    public float getTariffa(){
        return TariffaM;
    }
    
    
    public int PostiLiberi(){
        return (CapienzaMax - ListaPrenotazioni.size());
    }
}
