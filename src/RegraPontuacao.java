import java.util.Date;

public class RegraPontuacao {
	private Date dataInicio;
	private Date dataFinal;
	private Qualis[] qualis;
	private double multiplicador;
	private int qtdAnos;
	private double pontuacaoMinima;
	
	public RegraPontuacao(Date dataInicio, Date dataFinal, Qualis[] qualis, double multiplicador, int qtdAnos,
			double pontuacaoMinima) {
		super();
		this.dataInicio = dataInicio;
		this.dataFinal = dataFinal;
		this.qualis = qualis;
		this.multiplicador = multiplicador;
		this.qtdAnos = qtdAnos;
		this.pontuacaoMinima = pontuacaoMinima;
	}
	
	public Qualis[] getQualisVetor() {
		return qualis;
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