import java.util.concurrent.Future;

public class ThreadVerde extends Thread {
	private Buffer buffer;
	private CoordenadorThread coordenador;
	private Future future;

	public ThreadVerde(CoordenadorThread coordenador, String string,
			Buffer buffer) {
		super(string);
		this.buffer = buffer;
		this.coordenador = coordenador;
	}

	public void run() {
		while (true) {
			try {
				int n = buffer.ler();
				System.out.println(this.getName() + " leu " + n);
				coordenador.removerLeitor(this);
				coordenador.acordarEscritor();
				System.out.println("==> " + this.getName() + " terminou");
				break;
			} catch (Exception e) {
				try {
					System.out.println(this.getName() + " foi dormir");
					sleep(60000);
				} catch (InterruptedException f) {
					System.out.println(this.getName() + " foi interrompido");
				}
			}
		}
	}

	public Future getFuture() {
		return future;
	}

	public void setFuture(Future future) {
		this.future = future;
	}
}
