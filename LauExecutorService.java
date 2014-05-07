import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LauExecutorService extends Thread {
	private List<ThreadVerde> threadsVerdes;
	private ExecutorService executor;

	public LauExecutorService(Buffer buffer) {
		threadsVerdes = new ArrayList<ThreadVerde>();
		for (int i = 0; i < 100; ++i) {
			ThreadVerde verde = new ThreadVerde("Leitor" + i, buffer);
			threadsVerdes.add(verde);
		}
		executor = Executors.newFixedThreadPool(4);
	}

	public void run() {
		while (true) {
			for (ThreadVerde verde : threadsVerdes) {
				executor.submit(verde);
			}
		}
	}

	public void acordarLeitor() {
		for (ThreadVerde verde : threadsVerdes) {
			verde.interrupt();
		}
	}
}
