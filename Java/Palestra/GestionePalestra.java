package Esercizi_Esame.Palestra;

public class GestionePalestra {
    public static void main(String args[]){
        ListaCorsi corsi = new ListaCorsi();
        ThreadInterattivo ti = new ThreadInterattivo(corsi);
        ThreadReport tr = new ThreadReport(corsi);
        
        ti.start();
        tr.start();
    }
}