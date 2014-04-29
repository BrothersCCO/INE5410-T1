
public class CoordenadorThread extends Thread {
	private Buffer buffer;
	private LauExecutorService threadsVerdes;
	private ScheduleThread threadsAzuis;
	
	public CoordenadorThread() {
		this.buffer = new Buffer();
		this.threadsVerdes = new LauExecutorService(this.buffer);
		this.threadsAzuis = new ScheduleThread(this.buffer);
		threadsVerdes.run();
		threadsAzuis.run();
		System.out.println("Coordenador iniciado");
	}
	
	public void run() {	
		while(true) {
			if (!buffer.vazio()) {
				for (ThreadVerde verde : threadsVerdes.getThreads()) {
					if (!verde.terminou()) {
						verde.interrupt();
					}
				}
			}
			if (!buffer.cheio()) {
				for (ThreadAzul azul : threadsAzuis.getThreads()) {
					if (!azul.terminou()) {
						azul.interrupt();
					}
				}
			}
			try {
				sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
