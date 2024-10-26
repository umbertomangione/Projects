package Esercizi_Esame.Scuola;

public class Alunno {
    private String NomeCognome;
    private String DataN;
    private int NumeroS;
    private String Sezione;
    
    public Alunno(String nc, String s,String dn, int ns){
        this.NomeCognome = nc;
        this.Sezione = s;
        this.DataN = dn;
        this.NumeroS = ns;
    }
    
    public String getNomeCognome(){
        return NomeCognome;
    }
    
    public int NumeroS(){
        return NumeroS;
    }
    
    public String toString(){
        return "Nome e Cognome : " + NomeCognome + "\n"+
               "Data di nascita : " + DataN + "\n"+
               "Numero di insegnamenti con voto medio insufficiente : " + NumeroS + "\n";
    }
    
    public boolean equals(Object o){
        return (NomeCognome.equals(((Alunno)o).NomeCognome));
    }
}