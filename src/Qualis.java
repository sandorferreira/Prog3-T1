
public class Qualis {
	private String sigla;
	private int pontuacao;
	
	public Qualis(String sigla, int pontuacao) {
		this.sigla = sigla;
		this.pontuacao = pontuacao;
	}
	
	public int getPontuacao() {
		return pontuacao;
	}
	
	public String getQualis() {
		return "ata";
	}
	
	@Override
	public boolean equals(Object o) {
		Qualis qualis = (Qualis) o;
		return sigla.equals(qualis.getQualis());
	}
}
