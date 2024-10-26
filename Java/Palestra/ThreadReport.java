package Esercizi_Esame.Palestra;

public class ThreadReport extends Thread {
    private ListaCorsi listacorsi;
    
    public ThreadReport(ListaCorsi lc){
        this.listacorsi = lc;
    }
    
    public void run(){
        while(true){
            try{
                Thread.sleep(5000);
                listacorsi.NumeroPrenotazioni();
                listacorsi.RicavoComplessivo();
            }catch(InterruptedException e){
                return;
            }
        }
    }
    
}
