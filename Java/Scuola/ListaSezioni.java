package Esercizi_Esame.Scuola;
import java.util.*;
import java.io.*;

public class ListaSezioni {
    private LinkedList<Alunno> ListaAlunni;
    private LinkedList<Sezione> ListaSezioni;
    private LinkedList<Sezione> ListaSezioni2;
    private BufferedReader tastiera;
    private int Insufficienze;
    private String NS;
    private int max = 0;
    
    public ListaSezioni(){
        ListaSezioni = new LinkedList<Sezione>();
        ListaSezioni2 = new LinkedList<Sezione>();
        ListaSezioni2.add(new Sezione("Sezione per alunni senza classe", 0, "4a"));
        tastiera = new BufferedReader(new InputStreamReader(System.in));
    }
    
    public void caricaAlunno(String s){
        String NomeCognome;
        String DataN;
        int NumeroS;
        String NomeSezione;
        
        try{
            BufferedReader fp = new BufferedReader(new FileReader(s));
            NomeCognome = fp.readLine();
            while(NomeCognome != null){
                NomeSezione = fp.readLine();
                DataN = fp.readLine();
                NumeroS = Integer.parseInt(fp.readLine());
                for(Sezione se: ListaSezioni){
                    if(se.getNomeSezione().equals(NomeSezione))
                        se.addAlunno(new Alunno(NomeCognome, NomeSezione,DataN, NumeroS));
                }
                NomeCognome = fp.readLine();
            }
        }catch(IOException e){
            System.out.println("Errore durante la lettura del file");
            System.exit(-1);
        }
    }
    
    public void caricaSezione(String s){
        String NomeSezione;
        int AnnoSezione;
        String PosizioneAula;
        
        try{
            BufferedReader fp = new BufferedReader(new FileReader(s));
            NomeSezione = fp.readLine();
            while(NomeSezione != null){
                AnnoSezione = Integer.parseInt(fp.readLine());
                PosizioneAula = fp.readLine();
                ListaSezioni.add(new Sezione(NomeSezione, AnnoSezione, PosizioneAula));
                NomeSezione = fp.readLine();
            }
        }catch(IOException e){
            System.out.println("Errore durante la lettura del file");
            System.exit(-1);
        }
    }
    
    public void addSezione(){
        String NomeSezione;
        int AnnoSezione;
        String PosizioneAula;
        
        try{
            System.out.println("Inserire i dati della Sezione");
            System.out.print("Nome della Sezione : ");
            NomeSezione = tastiera.readLine();
            System.out.print("Anno della Sezione : ");
            AnnoSezione = Integer.parseInt(tastiera.readLine());
            System.out.print("Posizione Aula : ");
            PosizioneAula = tastiera.readLine();
            ListaSezioni.add(new Sezione(NomeSezione, AnnoSezione, PosizioneAula));
        }catch(IOException e){
            System.out.println("Errore di INPUT");
            System.exit(-1);
        }
    }
    
    public void EliminaSezione(){
        String Nomesezione;
        
        try{
            System.out.print("Inserire il nome della sezione da eliminare : ");
            Nomesezione = tastiera.readLine();
            
            for(Sezione s: ListaSezioni){
                ListaAlunni = s.getListaAlunni();
                if(s.getNomeSezione().equals(Nomesezione)){
                    for(Alunno a: ListaAlunni)
                        for(Sezione s1: ListaSezioni2)
                            s1.addAlunno(a);
                    ListaSezioni.remove(new Sezione(Nomesezione, 0, ""));
                }
            }
        }catch(IOException e){
            System.out.println("Errore di digitazione");
            System.exit(-1);
        }
    }
    
    public void EliminaAlunnoS() throws NonPresenteException{
        String NomeCognome;
        String Sezione;
        int i=0;
        
        try{
            System.out.print("Inserire il nome e il cognome dell'alunno da eliminare : ");
            NomeCognome = tastiera.readLine();
            System.out.print("Inserire la sezione dell'alunno da eliminare : ");
            Sezione = tastiera.readLine();
            for(Sezione e: ListaSezioni){       
                ListaAlunni = e.getListaAlunni();
                if(e.getNomeSezione().equals(Sezione))
                    for(Alunno a: ListaAlunni)
                        if(a.getNomeCognome().equals(NomeCognome)){
                            ListaAlunni.remove(new Alunno(NomeCognome, "", "", 0));
                            i=1;
                        }
            }
            if(i==0)
                throw new NonPresenteException();
        }catch(IOException e){
            System.out.println("Errore Di Digitazione");
            System.exit(-1);
        }
    }
    
    public void EliminaAlunno(){
        String NomeCognome;
        
        try{
            System.out.print("Inserire il nome e il cognome dell'alunno da eliminare : ");
            NomeCognome = tastiera.readLine();
            for(Sezione e: ListaSezioni){
                ListaAlunni = e.getListaAlunni();
                for(Alunno a: ListaAlunni)
                    if(a.getNomeCognome().equals(NomeCognome))
                        ListaAlunni.remove(new Alunno(NomeCognome, "","", 0));
            }
        }catch(IOException e){
            System.out.println("Errore Di Digitazione");
            System.exit(-1);
        }
    }
    
    public void Stampa(){
        for(Sezione s: ListaSezioni)
            System.out.println(s);
    }
    
    public void AlunniSenzaClasse(){
        for(Sezione s: ListaSezioni2)
            System.out.println(s);
    }
    
    public void TotaleStudentiS(){
        for(Sezione s: ListaSezioni)
            System.out.println("Il numero totale di studenti per la sezione : " + s.getNomeSezione()+ " è : " + s.TotaleS());
    }
    
    public void SezioneMaggiore(){
        Insufficienze = 0;
        
        for(Sezione s: ListaSezioni){
            Insufficienze = 0;
            ListaAlunni = s.getListaAlunni();
            for(Alunno a: ListaAlunni)
                Insufficienze += a.NumeroS();
            if(Insufficienze > max){
                NS = s.getNomeSezione();
                max = Insufficienze;
            }
        }
    }
    
    public void ListaInsufficienze(){
        System.out.println("Lista Alunni con insufficienze : ");
        for(Sezione s: ListaSezioni){
            ListaAlunni = s.getListaAlunni();
            for(Alunno a: ListaAlunni)
                if(a.NumeroS() > 0)
                    System.out.println("Alunno : " + a.getNomeCognome());
        }
    }
    
    public void StampaReport(){
        TotaleStudentiS();
        System.out.println("La sezione con il maggior numero di alunni con insufficienze.....");
        System.out.println(NS + " " + max);
        ListaInsufficienze();
    }
}
