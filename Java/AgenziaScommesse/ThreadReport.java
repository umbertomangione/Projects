package Esercizi_Esame.AgenziaScommesse;

public class ThreadReport extends Thread{
    private ListaEventiSportivi eventi;
    
    public ThreadReport(ListaEventiSportivi es){
        this.eventi = es;
    }
    
    public void run(){
        while(true){
            try{
                Thread.sleep(5000);
                eventi.TotaleScommettitori();
            }catch(InterruptedException e){
                return;
            }
        }
    }
}
