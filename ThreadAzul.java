public class ThreadAzul extends Thread {
	private Buffer buffer;
	private boolean terminou = false;

	public ThreadAzul(String string, Buffer buffer) {
		super(string);
		this.buffer = buffer;
		System.out.println(this.getName() + " iniciado");
	}

	public void run() {
		while (!terminou) {
			int n = (int) (Math.random() * 100) + 1;
			if (!buffer.cheio()) {
				buffer.escrever(n);
				System.out.println(this.getName() + " escreveu " + n);
				terminou = true;
			} else {
				try {
					System.out.println(this.getName() + " foi dormir");
					sleep(6000);
				} catch (InterruptedException e) {
					System.out.println(this.getName() + " foi interrompido");
					e.printStackTrace();
				}
			}
		}
	}

	public boolean terminou() {
		return terminou;
	}
}
