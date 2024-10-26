package Esercizi_Esame.Scuola;

public class GestioneScuola {
    public static void main(String args[]){
        ListaSezioni sezioni = new ListaSezioni();
        ThreadInterattivo ti = new ThreadInterattivo(sezioni);
        ThreadReport tr = new ThreadReport(sezioni);
        
        ti.start();
        tr.start();
    }
}