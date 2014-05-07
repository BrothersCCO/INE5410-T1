public class Buffer {
	private int[] valores = {0, 0};
	private int posicao = 0;
	
	public boolean cheio() {
		return posicao > 1;
	}
	
	public boolean vazio() {
		return posicao < 1;
	}
	
	public synchronized boolean escrever(int n) {
		if (cheio()) return false;
		valores[posicao] = n;
		++posicao;
		return true;
	}
	
	public synchronized int ler() {
		if (vazio()) return 0;
		--posicao;
		return valores[posicao];
	}
}
