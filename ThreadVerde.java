public class ThreadVerde extends Thread {
	private Buffer buffer;
	private boolean terminou = false;

	public ThreadVerde(String string, Buffer buffer) {
		super(string);
		this.buffer = buffer;
	}

	public void run() {
		terminou = buffer.vazio();
		while (!terminou) {
			int n = buffer.ler();
			if (n == 0) {
				terminou = true;
			} else {
				System.out.println(this.getName() + " leu " + n);
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
