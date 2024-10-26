package Esercizi_Esame.Palestra;
import java.util.*;
import java.io.*;

public class ListaCorsi {
    private LinkedList<Corso> ListaCorsi;
    private LinkedList<Prenotazione> ListaPrenotazioni;
    private BufferedReader tastiera;
    private int NumeroPrenotati;
    private float RicavoComplessivo;
    
    public ListaCorsi(){
        ListaCorsi = new LinkedList<Corso>();
        tastiera = new BufferedReader(new InputStreamReader(System.in));
    }
    
    public void caricaCorsi(String NomeFile){
        String NomeCorso;
        String NomeIstruttore;
        int CapienzaMax;
        float TariffaM;
        
        try{
            BufferedReader fp = new BufferedReader(new FileReader(NomeFile));
            NomeCorso = fp.readLine();
            while(NomeCorso != null){
                NomeIstruttore = fp.readLine();
                CapienzaMax = Integer.parseInt(fp.readLine());
                TariffaM = Float.parseFloat(fp.readLine());
                ListaCorsi.add(new Corso(NomeCorso, NomeIstruttore, CapienzaMax, TariffaM));
                NomeCorso = fp.readLine();
            }
        }catch(IOException e){
            System.out.println("Erroe Durante la lettura dal File dei Corsi");
            System.exit(-1);
        }
    }
    
    public void caricaPrenotazioni(String NomeFile){
        String NomeCognome;
        String NomeCorso;
        String DataPrenotazione;
        
        try{
            BufferedReader fp = new BufferedReader(new FileReader(NomeFile));
            NomeCognome = fp.readLine();
            while(NomeCognome != null){
                NomeCorso = fp.readLine();
                DataPrenotazione = fp.readLine();
                for(Corso c: ListaCorsi)
                    if(c.getNomeCorso().equals(NomeCorso))
                        c.addPrenotazione(new Prenotazione(NomeCognome, NomeCorso, DataPrenotazione));
                NomeCognome = fp.readLine();
            }
        }catch(IOException e){
            System.out.println("Errore Durante la lettura dal File delle Prenotazioni");
            System.exit(-1);
        }
    }
    
    public void addCorso(){
        String NomeCorso;
        String NomeIstruttore;
        int CapienzaMax;
        float TariffaM;
        
        try{
            System.out.println("---Inserire i dati relativi al Corso---");
            System.out.print("Inserire Nome del Corso : ");
            NomeCorso = tastiera.readLine();
            System.out.print("Inserire Nome dell'Istruttore : ");
            NomeIstruttore = tastiera.readLine();
            System.out.print("Inserire Capienza : ");
            CapienzaMax = Integer.parseInt(tastiera.readLine());
            System.out.print("Inserire Tariffa Mensile : ");
            TariffaM = Float.parseFloat(tastiera.readLine());
            ListaCorsi.add(new Corso(NomeCorso, NomeIstruttore, CapienzaMax, TariffaM));
        }catch(IOException e){
            System.out.println("Errore di Digitazione");
            System.exit(-1);
        }
    }
    
    public void addPrenotazione() throws FullCorsoException{
        String NomeCognome;
        String NomeCorso;
        String DataPrenotazione;
        
        try{
            System.out.println("---Inserire i dati relativi alla Prenotazione---");
            System.out.print("Inserire Nome e Cognome : ");
            NomeCognome = tastiera.readLine();
            System.out.print("Inserire Nome del Corso : ");
            NomeCorso = tastiera.readLine();
            System.out.print("Inserire Data di Prenotazione : ");
            DataPrenotazione = tastiera.readLine();
            
            for(Corso c: ListaCorsi)
                if(c.getNomeCorso().equals(NomeCorso))
                    if(c.isMax())
                        throw new FullCorsoException();
                    else{
                        c.addPrenotazione(new Prenotazione(NomeCognome, NomeCorso, DataPrenotazione));
                        break;
                    }
        }catch(IOException e){
            System.out.println("Errore di Digitazione");
            System.exit(-1);
        }
    }
    
    public void cancellaCorso(){
        String NomeCorso;
        
        try{
            System.out.print("Inserire il Nome del Corso da dover cancellare : ");
            NomeCorso = tastiera.readLine();
            
            for(Corso c: ListaCorsi)
                if(c.getNomeCorso().equals(NomeCorso))
                    ListaCorsi.remove(new Corso(NomeCorso, "", 0, 0.0F));
            System.out.println("Cancellazzione avvenuta con successo");
        }catch(IOException e){
            System.out.println("Errore di digitazione");
            System.exit(-1);
        }
    }
    
    public void cancellaPrenotazione(){
        String NomeCognome;
        
        try{
            System.out.println("Inserire il Nome e il Cognome inserito durante la prenotazione per la cancellazione : ");
            NomeCognome = tastiera.readLine();
            for(Corso c: ListaCorsi){
                ListaPrenotazioni = c.getListaPrenotazioni();
                for(Prenotazione p: ListaPrenotazioni)
                    if(p.getNomeCognome().equals(NomeCognome))
                        ListaPrenotazioni.remove(new Prenotazione(NomeCognome, "", ""));
            }
        }catch(IOException e){
            System.out.println("Errore di digitazione");
            System.exit(-1);
        }
    }
    
    public void Stampa(){
        for(Corso c: ListaCorsi)
            System.out.println(c);
    }
    
    public void NumeroPrenotazioni(){
        NumeroPrenotati = 0;
        for(Corso c: ListaCorsi)
            NumeroPrenotati += c.PostiPrenotati();
    }
    
    public void PostiDisponibili(){
        for(Corso c: ListaCorsi)
            if(!c.isMax())
                System.out.println("Per il Corso : " + c.getNomeCorso() + " il Numero di Posti Liberi è : " + c.PostiLiberi() + "\n");
    }
    
    public void RicavoComplessivo(){
        RicavoComplessivo = 0;
        for(Corso c: ListaCorsi)
            RicavoComplessivo += (c.getTariffa() * c.PostiPrenotati());
    }
    
    public void StampaReport(){
        System.out.println("NUMERO DEI PRENOTATI");
        System.out.println(NumeroPrenotati);
        PostiDisponibili();
        System.out.println("RICAVO COMPLESSIVO DELLA PALESTRA");
        System.out.println(RicavoComplessivo);
        
    }
}
