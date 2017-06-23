
public class Qualis {
        private int ano, pontuacao;
	private String qualis;
	
	public Qualis(int ano, String qualis) {
                this.ano = ano;
		this.qualis = qualis;
	}
        
        public void setPontuacao (int pontuacao){
            this.pontuacao = pontuacao;
        }
	
	public int getAno() {
		return ano;
	}
	
	public String getQualis() {
		return qualis;
	}
	
        public int getPontuacao(){
                return pontuacao;
        }
        
//	@Override
//	public boolean equals(Object o) {
//		Qualis qualis = (Qualis) o;
//		return sigla.equals(qualis.getQualis());
//	}
}
