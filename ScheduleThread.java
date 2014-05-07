import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ScheduleThread extends Thread {
	private List<ThreadAzul> threadsAzuis;
	private ScheduledExecutorService executor;

	public ScheduleThread(Buffer buffer) {
		threadsAzuis = new ArrayList<ThreadAzul>();
		for (int i = 0; i < 100; ++i) {
			ThreadAzul azul = new ThreadAzul("Escritor" + i, buffer);
			threadsAzuis.add(azul);
		}
		executor = Executors.newScheduledThreadPool(100);
	}

	public void run() {
		while (true) {
			for (ThreadAzul azul : threadsAzuis) {
				executor.submit(azul);

				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void acordarEscritor() {
		for (ThreadAzul azul : threadsAzuis) {
			azul.interrupt();
			System.out.println(azul.getName() + " interrompido.");
		}
	}
}
