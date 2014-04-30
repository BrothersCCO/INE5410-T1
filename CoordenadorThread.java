import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CoordenadorThread {
	private Buffer buffer = new Buffer();
	private ExecutorService verdeExecutor = Executors.newFixedThreadPool(4);
	private ScheduledExecutorService azulScheduler = Executors
			.newSingleThreadScheduledExecutor();
	private List<ThreadVerde> leitores = new ArrayList<ThreadVerde>();
	private List<ThreadAzul> escritores = new ArrayList<ThreadAzul>();

	public CoordenadorThread() {
		ThreadAzul azul;
		ThreadVerde verde;
		for (int i = 0; i < 100; ++i) {
			azul = new ThreadAzul(this, "Escritor" + i, buffer);
			azul.setFuture(azulScheduler.schedule(azul, (i + 1) * 100,
					TimeUnit.MILLISECONDS));
			escritores.add(azul);

			verde = new ThreadVerde(this, "Leitor" + i, buffer);
			verde.setFuture(verdeExecutor.submit(verde));
			leitores.add(verde);
		}
		System.out.println("Coordenador correndo");
	}

	public void removerEscritor(ThreadAzul escritor) {
		this.escritores.remove(escritor);
	}

	public ThreadAzul pegarEscritor() {
		return escritores.get(0);
	}

	public void acordarEscritor() {
		this.pegarEscritor().getFuture().cancel(true);
	}

	public void removerLeitor(ThreadVerde leitor) {
		this.escritores.remove(leitor);
	}

	public ThreadVerde pegarLeitor() {
		return leitores.get(0);
	}

	public void acordarLeitor() {
		this.pegarLeitor().getFuture().cancel(true);
	}
}
