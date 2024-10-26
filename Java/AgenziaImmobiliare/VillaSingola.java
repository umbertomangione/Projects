public class VillaSingola extends UnitaImmobiliare{
	private int numeroLivelli;
	private float mqGiardino;
	private boolean piscina;

	public VillaSingola(String id, String desc, String ind, String cf, float metri, int nv, float pv, int liv, float mqg, boolean p){
		super(id, desc, ind, cf, metri, nv, pv);
		numeroLivelli = liv;
		mqGiardino = mqg;
		piscina = p;
	}

	public String toString(){
		return (super.toString() + "\n" +
						"Numero livelli: " + numeroLivelli 		+ "\n" +
						"Dimensione giardino: " + mqGiardino 	+ "\n" +
						"Piscina presente (true/false)?: " + piscina + "\n"
					); 
	}
}
