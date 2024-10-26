/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author giuseppe
 */
public class Concessionaria {
    private String nome;
    private Veicolo []elencoVeicoli;
    private int numVeicoli;
    
    public Concessionaria(String nome, int N){
        this.nome = nome;
        elencoVeicoli = new Veicolo[N];
        numVeicoli = 0;
    }
    
    public boolean aggiungiVeicolo(Veicolo v){
        if (numVeicoli == elencoVeicoli.length){
            System.out.println("ERRORE: non è possibile aggiungere un altro veicolo. Concessionaria piena!!!");
            return false;
        }
        elencoVeicoli[numVeicoli++] = v; 
        return true;
    }
    
    public void trovaVeicoli(float costo){
        for (int i = 0; i < numVeicoli; i++)
            if (elencoVeicoli[i].getPrezzo() <= costo)
                System.out.println(elencoVeicoli[i]);
    }
    
    public float costo(){
        float totale = 0;
        
        for (int i=0; i < numVeicoli; i++)
            totale += elencoVeicoli[i].getPrezzo();       
        return totale;
    }
    
    public void rimuoviVeicolo(String t){
        
        for (int i = 0; i < numVeicoli; i++){
            if (elencoVeicoli[i].getTarga().equals(t)){
                elencoVeicoli[i] = elencoVeicoli[numVeicoli - 1];
                elencoVeicoli[numVeicoli - 1] = null;
                numVeicoli--;
                break;
            }
        }
    }

    public String toString(){
        String str = "Concessionaria: " + nome + "\n";
        for (int i = 0; i < numVeicoli; i++){
            str += elencoVeicoli[i] + "\n";
        }
        return str;
    }
     
   public String getNome(){
			return nome;
	 } 

	 public int getNumVeicoli(){
			return numVeicoli;		
	 }

	 public float getPrezzoVeicoloCostoso(){
		float max, vmax;
		int imax;

		if (numVeicoli == 0)
			return 0;
		
		max = elencoVeicoli[0].getPrezzo();
		for (int i = 1; i < numVeicoli; i++){
			vmax = elencoVeicoli[i].getPrezzo();
			if (vmax > max){
				max = vmax;
				imax = i;
			}
		}
		return max;
	 }	

}
