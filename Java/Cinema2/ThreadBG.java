public class ThreadBG extends Thread {
	private Listasale l;
	public ThreadBG(ListaSale l) {this.l=l;}
	public void run() {
		
		while(true){
			try{
				Thread.sleep(5000);
				l.totalePrenotazioni();
				l.ricavocomplessivo();
			}catch(InterruptedException e){
				return;
			}
		}
	}