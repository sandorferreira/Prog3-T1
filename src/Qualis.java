
public class Qualis {
        private int ano;
	private String sigla;
	private String qualis;
	
	public Qualis(int ano,String sigla, String qualis) {
                this.ano = ano;
		this.sigla = sigla;
		this.qualis = qualis;
	}
	
	public int getAno() {
		return ano;
	}
	
	public String getQualis() {
		return qualis;
	}
	
        public String getSigla(){
                return sigla;
        }
        
	@Override
	public boolean equals(Object o) {
		Qualis qualis = (Qualis) o;
		return sigla.equals(qualis.getQualis());
	}
}
