public class ThreadReport extends Thread{
	Eventi gestoreEventi;

	public ThreadReport(Eventi e){
		gestoreEventi = e;	
	}

	public void run(){
		while (true){
			try{
				gestoreEventi.ricavoPerEvento();
				gestoreEventi.listaEventiConPostiDisponibili();
				gestoreEventi.numeroTotalePersonePrenotate();
				Thread.sleep(5000);
			}catch(InterruptedException e){
				return;
			}
		}	
	}
}
