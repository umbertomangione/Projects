package Esercizi_Esame.Viaggi;

public class ThreadReport extends Thread {
    private ListaPacchetti pacchetti;
    
    public ThreadReport (ListaPacchetti lp){
        this.pacchetti = lp;
    }
    
    public void run(){
        
        while(true){
            try{
                Thread.sleep(5000);
                pacchetti.TotalePrenotati();
            }catch(InterruptedException e){
                return;
            }
        }
    }
}
