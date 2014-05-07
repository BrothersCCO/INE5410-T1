import java.util.ArrayList;
import java.util.List;

public class LauExecutorService extends Thread {
	private List<ThreadVerde> threadsVerdes;
	public int quantasJaCriou = 0;

	public LauExecutorService(Buffer buffer) {
		threadsVerdes = new ArrayList<ThreadVerde>(100);
		for (int i = 0; i < 100; ++i) {
			ThreadVerde verde = new ThreadVerde("Leitor" + i, buffer);
			threadsVerdes.add(verde);
		}
	}

	public void run() {
		for (ThreadVerde verde : threadsVerdes) {
			verde.start();
		}
	}

	public void acordarLeitor() {
		for (ThreadVerde verde : threadsVerdes) {
			if (verde.isAlive()) {
				verde.interrupt();
				return;
			} else {
				threadsVerdes.remove(verde);
				return;
			}
		}
	}

	public boolean tem() {
		return !(threadsVerdes.size() == 1 && threadsVerdes.get(0).getName() == "Leitor99");
	}
}
