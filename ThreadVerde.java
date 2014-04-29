public class ThreadVerde extends Thread {
	private Buffer buffer;
	private boolean terminou = false;

	public ThreadVerde(String string, Buffer buffer) {
		super(string);
		this.buffer = buffer;
		System.out.println(this.getName() + " iniciado");
	}

	public void run() {
		while (!terminou) {
			if (!buffer.vazio()) {
				int n = buffer.ler();
				System.out.println(this.getName() + " leu " + n);
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
