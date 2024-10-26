/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author giuseppe
 */
public class Veicolo implements Comparable {
    private String targa;
    private String marca;
    private String modello;
    private int cilindrata;
    private int anno;
    private float prezzo;
    
    public Veicolo(String targa, String marca, String modello, int cilindrata, int anno, float prezzo){
        this.targa = targa;
        this.marca = marca;
        this.modello = modello;
        this.cilindrata = cilindrata;
        this.anno = anno;
        this.prezzo = prezzo;
    }
    
    public void setTarga(String targa){
        this.targa = targa;
    }
    
    public String getTarga(){
        return targa;
    }
    
    public void setMarca(String marca){
        this.marca = marca;
    }
    
    public String getMarca(){
        return marca;
    }
    
    public void setModello(String modello){
        this.modello = modello;
    }
    
    public String getModello(){
        return modello;
    }
    
    public void setCilindrata(int  cilindrata){
        this.cilindrata = cilindrata;
    }
    
    public int getCilindrata(){
        return cilindrata;
    }
    
    public void setAnno(int anno){
        this.anno = anno;
    }
    
    public int getAnno(){
        return anno;
    }
    
    public void setPrezzo(float prezzo){
        this.prezzo = prezzo;
    }
    
    public float getPrezzo(){
        return prezzo;
    }
    
    //Stampa nel formato: "marca, modello, cilindrata, anno di immatricolazione:  prezzo"
    public String toString(){
        return (targa + ", " + marca + ", " + modello + ", " + cilindrata + ", " + anno + ": " + prezzo);
    }
    
    public int compareTo(Object o){
        return (int)(prezzo - ((Veicolo)o).prezzo);
    }
} 


