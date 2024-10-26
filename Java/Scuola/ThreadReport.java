package Esercizi_Esame.Scuola;

public class ThreadReport extends Thread{
    ListaSezioni alunni;
    
    public ThreadReport(ListaSezioni a){
        this.alunni = a;
    }
    
    public void run(){
        while(true){
            try{
                Thread.sleep(5000);
                alunni.SezioneMaggiore();
            }catch(InterruptedException e){
                return;
            }
        }
    }
}
