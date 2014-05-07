public class ThreadAzul extends Thread {
	private Buffer buffer;
	private boolean escreveu = false;

	public ThreadAzul(String string, Buffer buffer) {
		super(string);
		this.buffer = buffer;
	}

	public void run() {
		while (true) {
			int n = (int) (Math.random() * 100) + 1;
			if (buffer.escrever(n)) {
				System.out.println(this.getName() + " escreveu " + n);
				escreveu = true;
				break;
			}

			try {
				sleep(60000);
			} catch (InterruptedException e) {
			}
		}
	}

	public boolean isEscreveu() {
		return escreveu;
	}
}
