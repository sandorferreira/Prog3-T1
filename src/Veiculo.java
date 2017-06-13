

public class Veiculo {
	private String sigla;
	private String nome;
	private char tipo;
	private double fatorDeImpacto;
	private String ISSN;
	
	public Veiculo(String sigla, String nome, char tipo, double fatorDeImpacto, String ISSN) {
		this.sigla = sigla;
		this.nome = nome;
		this.tipo = tipo;
		this.fatorDeImpacto = fatorDeImpacto;
		this.ISSN = ISSN;
	}
	
	public String getSigla() {
		return sigla;
	}
	public String getNome() {
		return nome;
	}
	public char getTipo() {
		return tipo;
	}
	public double getFatorDeImpacto() {
		return fatorDeImpacto;
	}
	public String getISSN() {
		return ISSN;
	}
	
	
}
