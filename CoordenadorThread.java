import java.util.concurrent.ExecutorService;

public class CoordenadorThread extends Thread {
	private Buffer buffer;
	private LauExecutorService threadsVerdes;
	private ScheduleThread threadsAzuis;

	public CoordenadorThread() {
		this.buffer = new Buffer();

		this.threadsVerdes = new LauExecutorService(this.buffer);
		this.threadsAzuis = new ScheduleThread(this.buffer);

		threadsVerdes.start();
		threadsAzuis.start();
	}

	public void run() {
		while (true) {
			if (!buffer.cheio()) {
				threadsAzuis.acordarEscritor();
			}
			if (!buffer.vazio()) {
				threadsVerdes.acordarLeitor();
			}
			if (!threadsVerdes.tem()) {
				break;
			}
		}
	}
}
