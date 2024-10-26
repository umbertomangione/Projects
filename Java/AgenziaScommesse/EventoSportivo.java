package Esercizi_Esame.AgenziaScommesse;
import java.util.*;

public class EventoSportivo {
    private String IdentificativoE;
    private String Descrizione;
    private String Data;
    private float PuntataMinima;
    private float FattoreQ;
    private LinkedList<Scommessa> ListaScommesse;
    
    public EventoSportivo(String ie, String desc, String data, float pm, float fq){
        this.IdentificativoE = ie;
        this.Descrizione = desc;
        this.Data = data;
        this.PuntataMinima = pm;
        this.FattoreQ = fq;
        ListaScommesse = new LinkedList<Scommessa>();
    }
    
    public void addScommessa(Scommessa s){
        ListaScommesse.add(s);
    }
    
    public String getIdentificativoE(){
        return IdentificativoE;
    }
    public String getDescrizione(){
        return Descrizione;
    }
    public String getData(){
        return Data;
    }
    
    public float getPuntataMinima(){
        return PuntataMinima;
    }
    
    public float getFattoreQ(){
        return FattoreQ;
    }
    
    public LinkedList<Scommessa> getListaScommesse(){
        return ListaScommesse;
    }
    
    public String toString(){
        String result;
        result = "Identificativo Evento : " + IdentificativoE + "\n" +
                 "Descrizione : "+ Descrizione +"\n" +
                 "Data : " +Data + "\n" +
                 "Puntata Minima : "+ PuntataMinima +"\n" +
                 "Fattore di Quotazione : "+ FattoreQ +"\n";
        result += "---Lista degli Scommettitori---\n";
        for(Scommessa s: ListaScommesse)
            result += s;
        return result;
    }
    
    public int NumeroScommettitori(){
        return ListaScommesse.size();
    }
    
    public boolean PuntataMinimaSuperiore(){
        return (PuntataMinima > 5);
    }
}