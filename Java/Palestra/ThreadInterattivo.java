package Esercizi_Esame.Palestra;
import java.io.*;

public class ThreadInterattivo extends Thread {
    private ListaCorsi listacorsi;
    private BufferedReader tastiera;
    
    public ThreadInterattivo(ListaCorsi lc){
        this.listacorsi = lc;
        tastiera = new BufferedReader(new InputStreamReader(System.in));
    }

    public int menu(){
        int scelta = 0;
        try{
            System.out.print("------MENU'------\n" +
                               "1. Aggiungere un nuovo corso\n" +
                               "2. Eliminare un corso\n" +
                               "3. Inserire una nuova prenotazione\n" +
                               "4. Cancellare una prenotazione\n" +
                               "5. Stampa Report\n" +
                               "6. Stampa\n" +
                               "7. Uscita\n" +
                               "--------> Inserisci la tua scelta :");
            scelta = Integer.parseInt(tastiera.readLine());
        }catch(IOException e){
            System.out.println("Scelta non corretta");
            System.exit(-1);
        }
        return scelta;
    }
    
    public void run(){
        String NomeCorsi;
        String NomePrenotazioni;
        
        try{
            System.out.print("Inserire il nome del File contenente i Corsi : ");
            NomeCorsi = tastiera.readLine();
            System.out.print("Inserire il nome del File contenente le Prenotazioni : ");
            NomePrenotazioni = tastiera.readLine();
            listacorsi.caricaCorsi(NomeCorsi);
            listacorsi.caricaPrenotazioni(NomePrenotazioni);
            while(true){
                switch(menu()){
                    case 1:
                            listacorsi.addCorso();
                            break;
                    case 2:
                            listacorsi.cancellaCorso();
                            break;
                    case 3:
                            try{
                                listacorsi.addPrenotazione();
                            }catch(FullCorsoException e){
                                System.out.println("Nessun Posto Disponibile....");
                                System.out.println("SALA PIENA");
                            }
                            break;
                    case 4:
                            listacorsi.cancellaPrenotazione();
                            break;
                    case 5:
                            listacorsi.StampaReport();
                            break;
                    case 6:
                            listacorsi.Stampa();
                            break;
                    case 7:
                            System.out.println("Uscita Dal Programma");
                            System.exit(0);
                    default:
                            System.out.println("---SCELTA ERRATA---");
                            System.out.println("RIPROVA");
                }
            }
        }catch(IOException e){
            System.out.println("ERRORE DI I/O");
            System.exit(-1);
        }
    }
}
