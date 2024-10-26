package Esercizi_Esame.Palestra;

public class Prenotazione {
    private String NomeCognome;
    private String NomeCorso;
    private String DataPrenotazione;

    public Prenotazione(String ncog, String ncor, String data){
        this.NomeCognome = ncog;
        this.NomeCorso = ncor;
        this.DataPrenotazione = data;
    }
    
    public String toString(){
        return ("Nome e Cognome : " + NomeCognome +"\n" +
                "Nome Corso : " + NomeCorso +"\n" +
                "Data della Prenotazione : " + DataPrenotazione +"\n" );
    }
    
    public boolean equals(Object o){
        return (NomeCognome.equals(((Prenotazione)o).NomeCognome));
    }
    
    public String getNomeCognome(){
        return NomeCognome;
    }
}