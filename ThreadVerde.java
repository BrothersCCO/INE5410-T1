public class ThreadVerde extends Thread {
	private Buffer buffer;

	public ThreadVerde(String string, Buffer buffer) {
		super(string);
		this.buffer = buffer;
	}

	public void run() {
		while (true) {
			int n = buffer.ler();
			if (n != 0) {
				System.out.println(this.getName() + " leu " + n);
				break;
			}
			
			try {
				sleep(60000);
			} catch (InterruptedException e) {
			}
		}
	}
}
