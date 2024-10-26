public class Principale{
	public static void main(String args[]) {
		ListaSale l=new ListaSale();
		
		ThreadFG tfg=new ThreadFG(l);
		ThreadBG tbg=new ThreadBG(l);
		
		tfg.start();
		tbg.start();
		}
	
	}