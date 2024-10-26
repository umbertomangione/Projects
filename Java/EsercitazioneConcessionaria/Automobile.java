/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author giuseppe
 */
public class Automobile extends Veicolo{
    private int numeroPorte;
    private int numeroPosti;
    
    public Automobile(String targa, String marca, String modello, int cilindrata, int anno, float prezzo, int numeroPorte, int numeroPosti){
        super(targa, marca, modello, cilindrata, anno, prezzo);
        this.numeroPorte = numeroPorte;
        this.numeroPosti = numeroPosti;
    }
    
    public int getNumeroPorte(){
        return numeroPorte;
    }
    
    public void setNumeroPorte(int numeroPorte){
        this.numeroPorte = numeroPorte;
    }
    
    public int getNumeroPosti(){
        return numeroPosti;
    }
    
    public void setNumeroPosti(int numeroPosti){
        this.numeroPosti = numeroPosti;
    } 
    
    public String toString(){
        return super.toString() + ", nporte: " + numeroPorte + " , nposti: " + numeroPosti; 
    }
    
}
