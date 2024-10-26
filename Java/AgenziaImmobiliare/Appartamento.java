public class Appartamento extends UnitaImmobiliare{
	private int piano;
	private int postoAuto;

	public Appartamento(String id, String desc, String ind, String cf, float metri, int nv, float pv, int p, int pa){
		super(id, desc, ind, cf, metri, nv,pv);
		piano = p;
		postoAuto = pa;
	}

	public String toString(){
		return (super.toString() + "\n" +
						"Numero piano: " 			+ piano 		+ "\n" +
						"Numero posto auto: " + postoAuto 	+ "\n" 
					); 
	}
}
