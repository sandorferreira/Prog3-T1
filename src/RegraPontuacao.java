import java.util.Date;

public class RegraPontuacao {
	private Date dataInicio, dataFinal;
	private String[] categoriasQualis;
        private int[] pontuacaoQualis;
	private double multiplicador;
	private int qtdAnos, pontuacaoMinima;
	
	public RegraPontuacao(Date dataInicio, Date dataFinal, String[] categoriasQualis, int[] pontuacaoQualis, double multiplicador, int qtdAnos,
			int pontuacaoMinima) {
		this.dataInicio = dataInicio;
		this.dataFinal = dataFinal;
                this.categoriasQualis = categoriasQualis;
                this.pontuacaoQualis = pontuacaoQualis;
		this.multiplicador = multiplicador;
		this.qtdAnos = qtdAnos;
		this.pontuacaoMinima = pontuacaoMinima;
	}
	
	public String[] getCategoriasQualis() {
		return categoriasQualis;
	}
        
	public int[] getPontuacaoQualis() {
		return pontuacaoQualis;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public double getMultiplicador() {
		return multiplicador;
	}

	public int getQtdAnos() {
		return qtdAnos;
	}

	public int getPontuacaoMinima() {
		return pontuacaoMinima;
	}
	
	
	
}