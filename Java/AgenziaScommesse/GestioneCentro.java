package Esercizi_Esame.AgenziaScommesse;

public class GestioneCentro {
    public static void main(String args[]){
        ListaEventiSportivi eventi = new ListaEventiSportivi();
        ThreadInterattivo ti = new ThreadInterattivo(eventi);
        ThreadReport tr = new ThreadReport(eventi);
        
        ti.start();
        tr.start();
    }
}
