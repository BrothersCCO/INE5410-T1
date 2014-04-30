public class Buffer {
	private int[] valores = { 0, 0 };
	private int posicao = 0;

	public boolean cheio() {
		return posicao > 1;
	}

	public boolean vazio() {
		return posicao < 1;
	}

	public void escrever(int n) throws Exception {
		if (!cheio()) {
			valores[posicao] = n;
			++posicao;
		} else {
			throw new Exception();
		}
	}

	public int ler() throws Exception {
		if (!vazio()) {
			--posicao;
			return valores[posicao];
		} else {
			throw new Exception();
		}
	}
}
