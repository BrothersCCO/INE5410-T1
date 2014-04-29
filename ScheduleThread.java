import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ScheduleThread implements Runnable {
	private List<ThreadAzul> threadsAzuis;
	private ScheduledExecutorService executor;
	private Buffer buffer;

	public ScheduleThread(Buffer buffer) {
		this.buffer = buffer;
		threadsAzuis = new ArrayList<ThreadAzul>();
		for (int i = 0; i < 100; ++i) {
			ThreadAzul azul = new ThreadAzul("Escritor" + i, buffer);
			threadsAzuis.add(azul);
		}
		executor = Executors.newScheduledThreadPool(1);
		System.out.println("ScheduleThread iniciado");
	}

	public void run() {
		executor.scheduleAtFixedRate(new ScheduleThread(buffer), 0, 100, TimeUnit.MILLISECONDS);
		System.out.println("ScheduleThread correndo");
	}
	
	public List<ThreadAzul> getThreads() {
		return threadsAzuis;
	}
}
