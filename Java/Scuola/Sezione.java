package Esercizi_Esame.Scuola;
import java.util.*;

public class Sezione {
    private String NomeSezione;
    private int AnnoSezione;
    private String PosizioneAula;
    private LinkedList<Alunno> ListaAlunni;
    
    public Sezione(String ns, int as, String pa){
        this.NomeSezione = ns;
        this.AnnoSezione = as;
        this.PosizioneAula = pa;
        ListaAlunni = new LinkedList<Alunno>();
    }
    
    public String getNomeSezione(){
        return NomeSezione;
    }
    
    public void addAlunno(Alunno a){
        ListaAlunni.add(a);
    }
    
    public LinkedList<Alunno> getListaAlunni(){
        return ListaAlunni;
    }
    
    public String toString(){
        String result;
        result = "Nome Sezione : " + NomeSezione + "\n" +
                 "Anno Sezione : " + AnnoSezione + "\n" +
                 "Posizione Aula : " + PosizioneAula + "\n";
        result += "----LISTA DEGLI ALUNNI----\n";
        for(Alunno a: ListaAlunni)
            result += a;
        return result;
    }
    
    public int TotaleS(){
        return ListaAlunni.size();
    }
    
    public boolean equals(Object o){
        return (NomeSezione.equals(((Sezione)o).NomeSezione));
    }
}