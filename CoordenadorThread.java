import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CoordenadorThread extends Thread {
	private Buffer buffer;
	private LauExecutorService threadsVerdes;
	private ScheduleThread threadsAzuis;
	private ExecutorService executor;
	
	public CoordenadorThread() {
		this.buffer = new Buffer();
		
		this.threadsVerdes = new LauExecutorService(this.buffer);
		this.threadsAzuis = new ScheduleThread(this.buffer);
		
		executor = Executors.newFixedThreadPool(2);
		executor.execute(threadsVerdes);
		executor.execute(threadsAzuis);
		
		System.out.println("Coordenador iniciado");
	}
	
	public void run() {	
		while(true) {
			if (!buffer.vazio()) {
				threadsVerdes.acordarLeitor();
			}
			if (!buffer.cheio()) {
				threadsAzuis.acordarEscritor();
			}
		}
	}
}
