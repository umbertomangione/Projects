package Esercizi_Esame.Viaggi;
import java.io.*;

public class ThreadInterattivo extends Thread{
    private ListaPacchetti viaggi;
    private BufferedReader tastiera;
    
    public ThreadInterattivo(ListaPacchetti lp){
        this.viaggi = lp;
        tastiera = new BufferedReader(new InputStreamReader(System.in));
    }
    
    public int menu(){
        int scelta = 0;
        try{
            System.out.print("-----MENU'-----\n" +
                               "1. Inserire una nuova prenotazione\n" +
                               "2. Cancellare una prenotazione\n" +
                               "3. Inserire un nuovo viaggio\n" +
                               "4. Data una persona, trasferire in una nuova lista tutti i viaggi\n" +
                               "5. StampaReport\n" +
                               "6. Stampa\n" +
                               "7. Stampa la seconda lista\n" +
                               "8. Uscita dal programma\n" +
                               "------>Inserisci la tua risposta : ");
        scelta = Integer.parseInt(tastiera.readLine());
        }catch(IOException e){
            System.out.println("Errore di digitazione");
            System.exit(-1);
        }
        return scelta;
    }
    
    public void run(){
        String Viaggi;
        String Prenotazioni;
        
        try{
            System.out.print("Inserire il nome del file contenente i viaggi : ");
            Viaggi = tastiera.readLine();
            System.out.print("Inserire il nome del file contenente le prenotazioni : ");
            Prenotazioni = tastiera.readLine();
            viaggi.caricaPacchetti(Viaggi);
            viaggi.caricaPrenotazioni(Prenotazioni);
            
            while(true){
                switch(menu()){
                    case 1:
                           try{
                               viaggi.addPrenotazione();
                           }catch(SuperatoMaxException e){
                               System.out.println("Superato il numero massimo di prenotazioni effettuabili");
                           }catch(PersonaGiaPresenteException e){
                               System.out.println("Persona già presente nell'elenco delle prenotazioni");
                           }
                           break;
                    case 2:
                           viaggi.cancellaPrenotazione();
                           break;
                    case 3:
                           viaggi.addPacchetto();
                           break;
                    case 4:
                           viaggi.DataPersona();
                           break;
                    case 5:
                           viaggi.StampaReport();
                           break;
                    case 6:
                           viaggi.Stampa();
                           break;
                    case 7:
                           viaggi.StampaSecondaLista();
                           break;
                    case 8:
                           System.out.println("Uscita dal programma");
                           System.exit(0);
                    default:
                           System.out.println("Errore di digitazione.....");
                           System.out.println("----RIPROVA----");
                }
            }
        }catch(IOException e){
            System.out.println("Errore di I/O");
            System.exit(-1);
        }
    }
}
