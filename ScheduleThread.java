import java.util.ArrayList;
import java.util.List;

public class ScheduleThread extends Thread {
	private List<ThreadAzul> threadsAzuis;

	public ScheduleThread(Buffer buffer) {
		threadsAzuis = new ArrayList<ThreadAzul>();
		for (int i = 0; i < 100; ++i) {
			ThreadAzul azul = new ThreadAzul("Escritor" + i, buffer);
			threadsAzuis.add(azul);
		}
	}

	public void run() {
		for (ThreadAzul azul : threadsAzuis) {
			azul.start();
			
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void acordarEscritor() {
		for (ThreadAzul azul : threadsAzuis) {
			if (azul.isAlive()) {
				azul.interrupt();
				return;
			}
		}
	}
}
