public class ThreadReport extends Thread{
	private Immobili immobili;

	public ThreadReport(Immobili im){
		immobili = im;
	}

	public void run(){

		while(true){
			try{
				Thread.sleep(5000);
				immobili.numeroTotaleImmobili();
				immobili.giroAffariAgenzia();	
			}catch(InterruptedException e){
				return;
			}
		}
	}
}
