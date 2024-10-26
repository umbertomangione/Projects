package Esercizi_Esame.AgenziaScommesse;
import java.util.*;
import java.io.*;

public class ListaEventiSportivi {
    private LinkedList<EventoSportivo> ListaEventi;
    private LinkedList<Scommessa> ListaScommesse;
    private BufferedReader tastiera;
    private int TotaleScommettitori;
    private int RicavoComplessivo;
    
    public ListaEventiSportivi(){
        ListaEventi = new LinkedList<EventoSportivo>();
        tastiera = new BufferedReader(new InputStreamReader(System.in));
    }
    
    public void caricaEventi(String es){
        String IdentificativoE;
        String Descrizione;
        String Data;
        float PuntataMinima;
        float FattoreQ;
        try{
            BufferedReader fp = new BufferedReader(new FileReader(es));
            IdentificativoE = fp.readLine();
            while(IdentificativoE != null){
                Descrizione = fp.readLine();
                Data = fp.readLine();
                PuntataMinima = Float.parseFloat(fp.readLine());
                FattoreQ = Float.parseFloat(fp.readLine());
                ListaEventi.add(new EventoSportivo(IdentificativoE, Descrizione, Data, PuntataMinima, FattoreQ));
                IdentificativoE = fp.readLine();
            }
        }catch(IOException e){
            System.out.println("ERRORE DI I/O");
            System.exit(-1);
        }
    }
    
    public void caricaScommesse(String s){
        String IdentificativoE;
        String NomeCognome;
        String CodiceFiscale;
        float ImportoScommesso;
        
        try{
            BufferedReader fp = new BufferedReader(new FileReader(s));
            NomeCognome = fp.readLine();
            while(NomeCognome != null){
                IdentificativoE = fp.readLine();
                CodiceFiscale = fp.readLine();
                ImportoScommesso = Float.parseFloat(fp.readLine());
                for(EventoSportivo es: ListaEventi)
                    if(es.getIdentificativoE().equals(IdentificativoE))
                        es.addScommessa(new Scommessa(NomeCognome, IdentificativoE, CodiceFiscale, ImportoScommesso));
                NomeCognome = fp.readLine();
            }
        }catch(IOException e){
            System.out.println("ERRORE DI I/O");
            System.exit(-1);
        }
    }
    public void addEvento() throws GiaPresenteException{
        String IdentificativoE;
        String Descrizione;
        String Data;
        float PuntataMinima;
        float FattoreQ;
        
        try{
            System.out.println("---Inserire i dati relativi all'Evento---");
            System.out.print("Inserire Nome dell'Evento : ");
            IdentificativoE = tastiera.readLine();
            for(EventoSportivo es: ListaEventi)
                if(es.getIdentificativoE().equals(IdentificativoE))
                    throw new GiaPresenteException();
            System.out.print("Inserire la Descrizione dell'Evento : ");
            Descrizione = tastiera.readLine();
            System.out.print("Inserire la Data dell'Evento : ");
            Data = tastiera.readLine();
            System.out.print("Inserire la Puntata Minima dell'Evento : ");
            PuntataMinima = Float.parseFloat(tastiera.readLine());
            System.out.print("Inserire il Fattore di Quotazione dell'Evento : ");
            FattoreQ = Float.parseFloat(tastiera.readLine());
            ListaEventi.add(new EventoSportivo(IdentificativoE, Descrizione, Data, PuntataMinima, FattoreQ));
        }catch(IOException e){
            System.out.println("ERRORE DI INPUT");
            System.exit(-1);
        }
    }
    
    public void addScommessa() throws ImportoException{
        String IdentificativoE;
        String NomeCognome;
        String CodiceFiscale;
        float ImportoScommesso;
        
        try{
            System.out.println("---Inserire i dati relativi alla Scommessa---");
            System.out.print("Inserire Nome e Cognome : ");
            NomeCognome = tastiera.readLine();
            System.out.print("Inserire l'Identificativo dell'Evento : ");
            IdentificativoE = tastiera.readLine();
            System.out.print("Inserire il Codice Fiscale : ");
            CodiceFiscale = tastiera.readLine();
            System.out.print("Inserire l'Importo della Scommessa : ");
            ImportoScommesso = Float.parseFloat(tastiera.readLine());
            for(EventoSportivo es: ListaEventi)
                if(es.getIdentificativoE().equals(IdentificativoE))
                    if(ImportoScommesso < es.getPuntataMinima())
                        throw new ImportoException();
                    else
                        es.addScommessa(new Scommessa(NomeCognome, IdentificativoE, CodiceFiscale, ImportoScommesso));
        }catch(IOException e){
            System.out.println("ERRORE DI INPUT");
            System.exit(-1);
        }
    }
    
    public void ImportoDaPagare(){
        String IdentificativoE;
        float FattoreQ;
        
        try{
            System.out.print("Inserire l'Identificativo dell'Evento : ");
            IdentificativoE = tastiera.readLine();
            for(EventoSportivo es: ListaEventi)
                if(es.getIdentificativoE().equals(IdentificativoE)){
                    ListaScommesse = es.getListaScommesse();
                    FattoreQ = es.getFattoreQ();
                    for(Scommessa s: ListaScommesse){
                        System.out.println(s);
                        System.out.println("Importo da Pagare : " + (FattoreQ * s.getImportoScommesso()));
                    }
                }
        }catch(IOException e){
            System.out.println("Errore di Input");
            System.exit(-1);
        }
    }
    
    public void Stampa(){
        for(EventoSportivo es: ListaEventi)
            System.out.println(es);
    }
    
    public void SalvareSuFile(){
        String CodiceFiscale;
        String NomeFile;
        String IdentificativoE;
        String Descrizione;
        String Data;
        String PuntataMinima;
        String FattoreQ;
        
        try{
            System.out.print("Inserire il Nome del File su cui memorizzare gli Eventi : ");
            NomeFile = tastiera.readLine();
            BufferedWriter fw = new BufferedWriter(new FileWriter(NomeFile + ".txt"));
            System.out.println("Inserire il Codice Fiscale : ");
            CodiceFiscale = tastiera.readLine();
            for(EventoSportivo es: ListaEventi){
                ListaScommesse = es.getListaScommesse();
                IdentificativoE = es.getIdentificativoE();
                Descrizione = es.getDescrizione();
                Data = es.getData();
                PuntataMinima = String.valueOf(es.getPuntataMinima());
                FattoreQ = String.valueOf(es.getFattoreQ());
                for(Scommessa s: ListaScommesse)
                    if(s.getCodiceFiscale().equals(CodiceFiscale)){
                        fw.write(IdentificativoE);
                        fw.write('\n');
                        fw.write(Descrizione);
                        fw.write('\n');
                        fw.write(Data);
                        fw.write('\n');
                        fw.write(PuntataMinima);
                        fw.write('\n');
                        fw.write(FattoreQ);
                        fw.write('\n');
                    }
            }
            fw.close();
        }catch(IOException e){
            System.out.println("Errore di Input");
            System.exit(-1);
        }
    }
    
    public void RicavoComplessivo(){
        RicavoComplessivo = 0;
        for(EventoSportivo es: ListaEventi){
            RicavoComplessivo = 0;
            ListaScommesse = es.getListaScommesse();
            for(Scommessa s: ListaScommesse)
                RicavoComplessivo += s.getImportoScommesso();
            System.out.println("Per l'Evento : " + es.getIdentificativoE() + ", il Ricavo Complessivo in caso di vincita sarebbe : " + RicavoComplessivo);
        }
    }
    
    public void ListaEventiPMS(){
        System.out.println("----I SEGUENTI EVENTI HANNO UNA PUNTATA MINIMA SUPERIORE A 5----");
        for(EventoSportivo es: ListaEventi)
            if(es.PuntataMinimaSuperiore())
                System.out.println("Evento : " + es.getIdentificativoE() + " Puntata Minima : " + es.getPuntataMinima());
    }
    
    public void TotaleScommettitori(){
        TotaleScommettitori = 0;
        for(EventoSportivo es: ListaEventi)
            TotaleScommettitori += es.NumeroScommettitori();
    }
    
    public void StampaReport(){
        RicavoComplessivo();
        ListaEventiPMS();
        System.out.println("Numero Degli Scomettitori : ");
        System.out.println(TotaleScommettitori);
    }
}