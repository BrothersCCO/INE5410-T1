public class ThreadAzul extends Thread {
	private Buffer buffer;
	private boolean terminou = false;

	public ThreadAzul(String string, Buffer buffer) {
		super(string);
		this.buffer = buffer;
	}

	public void run() {
		terminou = buffer.cheio();
		while (!terminou) {
			int n = (int) (Math.random() * 100) + 1;
			if (!buffer.escrever(n)) {
				terminou = true;
			} else {
				System.out.println(this.getName() + " escreveu " + n);
			}

			try {
				sleep(60000);
				System.out.println(this.getName() + " foi dormir");
			} catch (InterruptedException e) {
				System.out.println(this.getName() + " foi interrompido");
			}
		}
	}

	public boolean terminou() {
		return terminou;
	}
}
