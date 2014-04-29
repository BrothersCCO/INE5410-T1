public class Buffer {
	private int[] valores = {0, 0};
	private int posicao = 0;
	
	public boolean cheio() {
		return posicao > 1;
	}
	
	public boolean vazio() {
		return posicao < 1;
	}
	
	public void escrever(int n) {
		valores[posicao] = n;
		++posicao;
	}
	
	public int ler() {
		--posicao;
		return valores[posicao];
	}
}
