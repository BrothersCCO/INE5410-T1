import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LauExecutorService {
	private List<ThreadVerde> threadsVerdes;
	private ExecutorService executor;

	public LauExecutorService(Buffer buffer) {
		threadsVerdes = new ArrayList<ThreadVerde>();
		for (int i = 0; i < 100; ++i) {
			ThreadVerde verde = new ThreadVerde("Leitor" + i, buffer);
			threadsVerdes.add(verde);
		}
		executor = Executors.newFixedThreadPool(4);
		System.out.println("ExecutorService iniciado");
	}

	public void run() {
		for (ThreadVerde verde : threadsVerdes) {
			executor.submit(verde);
			System.out.println(verde.getName() + " agendado para executar.");
		}
		System.out.println("ExecutorService correndo");
	}

	public List<ThreadVerde> getThreads() {
		return threadsVerdes;
	}
}
