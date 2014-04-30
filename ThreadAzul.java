import java.util.concurrent.Future;

public class ThreadAzul extends Thread {
	private Buffer buffer;
	private CoordenadorThread coordenador;
	private Future future;

	public ThreadAzul(CoordenadorThread coordenador, String string,
			Buffer buffer) {
		super(string);
		this.buffer = buffer;
		this.coordenador = coordenador;
	}

	public void run() {
		while (true) {
			try {
				int n = (int) (Math.random() * 100) + 1;
				buffer.escrever(n);
				System.out.println(this.getName() + " escreveu " + n);
				coordenador.removerEscritor(this);
				coordenador.acordarLeitor();
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
