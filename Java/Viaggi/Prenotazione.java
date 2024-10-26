package Esercizi_Esame.Viaggi;

public class Prenotazione {
    private String IdentificativoP;
    private String NomeCognome;
    private String CodiceFiscale;
    
    public Prenotazione(String cf, String nc, String ip){
        this.CodiceFiscale = cf;
        this.NomeCognome = nc;
        this.IdentificativoP = ip;
    }
    
    public String getCodiceFiscale(){
        return CodiceFiscale;
    }
    
    public boolean equals(Object o){
        return (CodiceFiscale.equals(((Prenotazione)o).CodiceFiscale));
    }
    
    public String toString(){
        return ("Codice Fiscale : " + CodiceFiscale + "\n" +
               "Nome e Cognome : " + NomeCognome + "\n" +
               "Identificativo viaggio : " + IdentificativoP + "\n");
    }
}