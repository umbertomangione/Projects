package Esercizi_Esame.Viaggi;
import java.util.*;
import java.io.*;

public class ListaPacchetti {
    private LinkedList<PacchettoViaggio> ListaViaggi;
    private LinkedList<PacchettoViaggio> ListaViaggi2;
    private LinkedList<Prenotazione> ListaPrenotazioni;
    private BufferedReader tastiera;
    private int TotalePrenotati;
    
    public ListaPacchetti(){
        ListaViaggi = new LinkedList<PacchettoViaggio>();
        ListaViaggi2 = new LinkedList<PacchettoViaggio>();
        tastiera = new BufferedReader(new InputStreamReader(System.in));
    }
    
    public void caricaPacchetti(String s){
        String Identificativo;
        String Descrizione;    
        float CostoP;
        int MaxPersone;
        
        try{
            BufferedReader fp = new BufferedReader(new FileReader(s));
            Identificativo = fp.readLine();
            while(Identificativo != null){
                Descrizione = fp.readLine();
                CostoP = Float.parseFloat(fp.readLine());
                MaxPersone = Integer.parseInt(fp.readLine());
                ListaViaggi.add(new PacchettoViaggio(Identificativo, Descrizione, CostoP, MaxPersone));
                Identificativo = fp.readLine();
            }
        }catch(IOException e){
            System.out.println("Errore durante la lettura del file dei Pacchetti");
            System.exit(-1);
        }
    }
    
    public void caricaPrenotazioni(String s){
        String IdentificativoV;
        String NomeCognome;
        String CodiceFiscale;
        
        try{
            BufferedReader fp = new BufferedReader(new FileReader(s));
            CodiceFiscale = fp.readLine();
            while(CodiceFiscale != null){
                NomeCognome = fp.readLine();
                IdentificativoV = fp.readLine();
                for(PacchettoViaggio pv: ListaViaggi)
                    if(pv.getIdentificativo().equals(IdentificativoV))
                        pv.addPrenotazione(new Prenotazione(CodiceFiscale, NomeCognome, IdentificativoV));
                CodiceFiscale = fp.readLine();
            }
        }catch(IOException e){
            System.out.println("Errore durante la lettura del file delle Prenotazioni");
            System.exit(-1);
        }
    }
    
    public void addPacchetto(){
        String Identificativo;
        String Descrizione;    
        float CostoP;
        int MaxPersone;
        
        try{
            System.out.println("INSERIRE I DATI RELATIVI AL PACCHETTO VIAGGIO");
            System.out.print("Inserire l'identificativo : ");
            Identificativo = tastiera.readLine();
            System.out.print("Inserire la descrizione : ");
            Descrizione = tastiera.readLine();
            System.out.print("Inserire il costo a persona : ");
            CostoP = Float.parseFloat(tastiera.readLine());
            System.out.print("Inserire il numero massimo di persone : ");
            MaxPersone = Integer.parseInt(tastiera.readLine());
            ListaViaggi.add(new PacchettoViaggio(Identificativo, Descrizione, CostoP, MaxPersone));    
        }catch(IOException e){
            System.out.println("Errore durante la lettura da tastiera");
            System.exit(-1);
        }
    }
    
    public void addPrenotazione() throws PersonaGiaPresenteException,SuperatoMaxException{
        String IdentificativoV;
        String NomeCognome;
        String CodiceFiscale;
        int i=0;
        try{
            System.out.println("INSERIRE I DATI RELATIVI ALLA PRENOTAZIONE");
            System.out.print("Inserire il codice fiscale : ");
            CodiceFiscale = tastiera.readLine();
            System.out.print("Inserire il Nome e Cognome : ");
            NomeCognome = tastiera.readLine();
            System.out.print("Inserire l'identificativo del viaggio : ");
            IdentificativoV = tastiera.readLine();
            for(PacchettoViaggio pv: ListaViaggi){
                ListaPrenotazioni = pv.getListaPrenotazioni();
                if(pv.getIdentificativo().equals(IdentificativoV)){
                    if(pv.isMax())
                        throw new SuperatoMaxException();
                    else
                        for(Prenotazione p: ListaPrenotazioni)
                            if(p.getCodiceFiscale().equals(CodiceFiscale))
                                throw new PersonaGiaPresenteException();
                    pv.addPrenotazione(new Prenotazione(CodiceFiscale, NomeCognome, IdentificativoV));
                }
            }
        }catch(IOException e){
            System.out.println("Errore durante la lettura da tastiera");
            System.exit(-1);
        }
    }
    
    public void cancellaPrenotazione(){
        String CodiceFiscale;
        
        try{
            System.out.print("Inserire il Codice Fiscale della persona : ");
            CodiceFiscale = tastiera.readLine();
            for(PacchettoViaggio pv: ListaViaggi){
                ListaPrenotazioni = pv.getListaPrenotazioni();
                for(Prenotazione p: ListaPrenotazioni)
                    if(p.getCodiceFiscale().equals(CodiceFiscale))
                        ListaPrenotazioni.remove(new Prenotazione(CodiceFiscale, "", ""));
            }
        }catch(IOException e){
            System.out.println("Errore durante la lettura da tastiera");
            System.exit(-1);
        }
    }
    
    public void DataPersona(){
        String CodiceFiscale;
        int i=0;
        
        try{
            System.out.print("Inserire il Codice Fiscale : ");
            CodiceFiscale = tastiera.readLine();
            for(PacchettoViaggio pv: ListaViaggi){
                ListaPrenotazioni = pv.getListaPrenotazioni();
                for(Prenotazione p: ListaPrenotazioni)
                    if(p.getCodiceFiscale().equals(CodiceFiscale))
                        i=1;
                if(i==1)
                    ListaViaggi2.add(pv);
                i=0;
            }
        }catch(IOException e){
            System.out.println("Errore di digitazione");
            System.exit(-1);
        }
    }
    
    public void Stampa(){
        for(PacchettoViaggio pv: ListaViaggi)
            System.out.println(pv);
    }
    
    public void StampaSecondaLista(){
        for(PacchettoViaggio pv: ListaViaggi2)
            System.out.println(pv.SecondaLista());
    }
    
    public void RicavoTotale(){
        System.out.println("\n----RICAVO TOTALE PER CIASCUN VIAGGIO----");
        for(PacchettoViaggio pv: ListaViaggi)
            System.out.println("Identificativo Viaggio : " + pv.getIdentificativo() + "Ricavo Totale : " + pv.CostoTotalePrenotazioni());
    }
    
    public void PostiDisponibili(){
        System.out.println("\n----LISTA DEI VIAGGI CON ANCORA POSTI DISPONIBILI----");
        for(PacchettoViaggio pv: ListaViaggi)
            if(!pv.isMax())
                System.out.println("Identificativo viaggio : " + pv.getIdentificativo() + " con " + pv.PostiDisponibili() + " prenotazioni disponibili");
    }
    
    public void TotalePrenotati(){
        TotalePrenotati = 0;
        for(PacchettoViaggio pv: ListaViaggi)
            TotalePrenotati += pv.NumeroPrenotati();
    }
    
    public void StampaReport(){
        RicavoTotale();
        System.out.println("\n");
        PostiDisponibili();
        System.out.println("\nNumero di tutti i prenotati in tutti i viaggi");
        System.out.println(TotalePrenotati);
    }
}
