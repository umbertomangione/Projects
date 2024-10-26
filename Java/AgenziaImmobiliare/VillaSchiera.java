public class VillaSchiera extends UnitaImmobiliare{
	private int numeroLivelli;
	private float mqGiardino;

	public VillaSchiera(String id, String desc, String ind, String cf, float metri, int nv, float pv, int liv, float mqg){
		super(id, desc, ind, cf, metri, nv, pv);
		numeroLivelli = liv;
		mqGiardino = mqg;
	}

	public String toString(){
		return (super.toString() + "\n" +
						"Numero livelli: " + numeroLivelli 		+ "\n" +
						"Dimensione giardino: " + mqGiardino 	+ "\n"
					); 
	}
}
