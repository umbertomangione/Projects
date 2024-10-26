/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author giuseppe
 */
public class Autocarro extends Veicolo {
    private int carico;
    
    public Autocarro(String targa, String marca, String modello, int cilindrata, int anno, float prezzo, int carico){
        super(targa, marca, modello, cilindrata, anno, prezzo);
        this.carico = carico;
    }
    
    public int getCarico(){
        return carico;
    }
    
    public void setCarico(int carico){
        this.carico = carico;
    }
    
    public String toString(){
        return super.toString() + " carico: " + carico;
    }
    
}
