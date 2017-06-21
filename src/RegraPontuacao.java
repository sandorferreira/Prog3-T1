import java.util.Date;

public class RegraPontuacao {
	private Date dataInicio, dataFinal;
	private String[] categoriasQualis;
        private double[] pontuacaoQualis;
	private double multiplicador, pontuacaoMinima;
	private int qtdAnos;
	
	public RegraPontuacao(Date dataInicio, Date dataFinal, String[] categoriasQualis, double[] pontuacaoQualis, double multiplicador, int qtdAnos,
			double pontuacaoMinima) {
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
        
	public double[] getPontuacaoQualis() {
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

	public double getPontuacaoMinima() {
		return pontuacaoMinima;
	}
	
	
	
}