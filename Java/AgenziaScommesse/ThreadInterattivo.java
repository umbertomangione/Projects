package Esercizi_Esame.AgenziaScommesse;
import java.io.*;

public class ThreadInterattivo extends Thread {
    private ListaEventiSportivi eventi;
    private BufferedReader tastiera;
    
    public ThreadInterattivo(ListaEventiSportivi es){
        this.eventi = es;
        tastiera = new BufferedReader(new InputStreamReader(System.in));
    }
    
    public int menu(){
        int scelta = 0;
        try{
            System.out.print("------MENU'------\n" + 
                               "1. Inserire un nuovo evento sportivo\n" + 
                               "2. Inserire una nuova scommessa\n" + 
                               "3. Calcolare l’importo da pagare a tutti gli scommettitori di un determinato evento\n" + 
                               "4. Salvare tutti gli eventi sportivi su cui una perona ha effettuato delle scommesse\n" + 
                               "5. StampaReport\n" + 
                               "6. Stampa\n" + 
                               "7. Uscita\n" +
                               "Inserisci la tua scelta-------> ");
            scelta = Integer.parseInt(tastiera.readLine());
        }catch(IOException e){
            System.out.println("Errore di digitazione");
            System.exit(-1);
        }
        return scelta;
    }
    
    public void run(){
        String FileEventi;
        String FileScommesse;
        
        try{
            System.out.print("Inserire il nome del file contenente gli Eventi : ");
            FileEventi = tastiera.readLine();
            System.out.print("Inserire il nome del file contenente le Scommesse : ");
            FileScommesse = tastiera.readLine();
            eventi.caricaEventi(FileEventi);
            eventi.caricaScommesse(FileScommesse);
            
            while(true){
                switch(menu()){
                    case 1:
                            try{
                                eventi.addEvento();
                            }catch(GiaPresenteException e){
                                System.out.println("L'evento inserito è già presente.....");
                                System.out.println("---RIPROVA---");
                            }
                            break;
                    case 2:
                            try{
                                eventi.addScommessa();
                            }catch(ImportoException e){
                                System.out.println("L'importo della scommessa è minore della Puntata Minima.......");
                                System.out.println("---RIPROVA---");
                            }
                            break;
                    case 3:
                            eventi.ImportoDaPagare();
                            break;
                    case 4:
                            eventi.SalvareSuFile();
                            break;
                    case 5:
                            eventi.StampaReport();
                            break;
                    case 6:
                            eventi.Stampa();
                            break;
                    case 7:
                            System.out.println("Uscita Dal Programma");
                            System.exit(0);
                    default:
                            System.out.println("Digitazione Errata");
                            System.out.println("---RIPROVA---");
                }
            }
        }catch(IOException e){
            System.out.println("Errore nella lettura dei file");
            System.exit(-1);
        }
    }
}
