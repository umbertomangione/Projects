package Esercizi_Esame.Scuola;
import java.io.*;

public class ThreadInterattivo extends Thread{
    private ListaSezioni alunni;
    private BufferedReader tastiera;
    
    public ThreadInterattivo(ListaSezioni a){
        this.alunni = a;
        tastiera = new BufferedReader(new InputStreamReader(System.in));
    }
    
    public int menu(){
        int scelta = 0;
        try{
            System.out.print("----MENU'----\n" +
                               "1. Aggiungere una nuova sezione\n" +
                               "2. Eliminare una sezione\n" +
                               "3. Eliminare un alunno, data la sezione, il nome cognome\n" +
                               "4. Cancellare un alunno dati nome e cognome\n" +
                               "5. Stampa report\n" +
                               "6. Stampa\n" +
                               "7. Stampa alunni senza classe\n" +
                               "8. Uscita dal programma\n" +
                               "Inserisci la tua scelta-------> ");
            scelta = Integer.parseInt(tastiera.readLine());
        }catch(IOException e){
            System.out.println("Errore di digitazione");
            System.exit(-1);
        }
        return scelta;
    }
    public void run(){
        String Sezioni;
        String Alunni;
        
        try{
            System.out.print("Inserire il nome del file contenente le Sezioni : ");
            Sezioni = tastiera.readLine();
            System.out.print("Inserire il nome del file contenente gli Alunni : ");
            Alunni = tastiera.readLine();
            alunni.caricaSezione(Sezioni);
            alunni.caricaAlunno(Alunni);
            while(true){
                switch(menu()){
                    case 1:
                           alunni.addSezione();
                           break;
                    case 2:
                           alunni.EliminaSezione();
                           break;
                    case 3:
                           try{
                               alunni.EliminaAlunnoS();
                           }catch(NonPresenteException e){
                               System.out.println("Alunno Non Presente");
                           }
                           break;
                    case 4:
                           alunni.EliminaAlunno();
                           break;
                    case 5:
                           alunni.StampaReport();
                           break;
                    case 6:
                           alunni.Stampa();
                           break;
                    case 7:
                           alunni.AlunniSenzaClasse();
                           break;
                    case 8:
                           System.out.println("Uscita dal programma");
                           System.exit(0);
                    default:
                           System.out.println("Errore di digitazione");
                           System.out.println("----RIPROVA----");
                }
            }
        }catch(IOException e){
            System.out.println("Errore nella lettura del file");
            System.exit(-1);
        }
    }
}