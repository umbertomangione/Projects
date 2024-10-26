package Esercizi_Esame.AgenziaScommesse;

public class Scommessa {
    private String IdentificativoE;
    private String NomeCognome;
    private String CodiceFiscale;
    private float ImportoScommesso;
    
    public Scommessa(String nc, String ie, String cf, float is){
        this.NomeCognome = nc;
        this.IdentificativoE = ie;
        this.CodiceFiscale = cf;
        this.ImportoScommesso = is;
    }
    
    public String toString(){
        return "Nome e Cognome : " + NomeCognome +"\n"+
               "Identificativo Evento : " + IdentificativoE +"\n"+
               "Nome e Cognome : " + CodiceFiscale +"\n"+
               "Importo Scommesso : " + ImportoScommesso + "\n";
    }
    
    public float getImportoScommesso(){
        return ImportoScommesso;
    }
    
    public String getCodiceFiscale(){
        return CodiceFiscale;
    }
}