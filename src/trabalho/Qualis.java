package trabalho;

public class Qualis {
    private int ano, pontuacao;
	private String qualis;
	private static String[] categoriasQualis = {"A1","A2","B1","B2","B3","B4","B5","C"};
	
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
        
    public boolean isValidQualis() {
    	boolean isContained = false;
    	for(String auxQualis : categoriasQualis) {
    		if (auxQualis.equals(qualis)) {
    			isContained = true;
    		}
    	}
    	return isContained;
    }
        
//	@Override
//	public boolean equals(Object o) {
//		Qualis qualis = (Qualis) o;
//		return sigla.equals(qualis.getQualis());
//	}
}
