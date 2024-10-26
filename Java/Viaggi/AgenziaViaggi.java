package Esercizi_Esame.Viaggi;

public class AgenziaViaggi {
    public static void main(String args[]){
        ListaPacchetti pacchetti = new ListaPacchetti();
        ThreadInterattivo ti = new ThreadInterattivo(pacchetti);
        ThreadReport tr = new ThreadReport(pacchetti);
        
        ti.start();
        tr.start();
    }
}