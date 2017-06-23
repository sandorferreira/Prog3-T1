import java.util.Date;
import java.util.Objects;

public class RegraPontuacao {
	private Date dataInicio, dataFinal;
	private String[] categoriasQualis = {"A1","A2","B1","B2","B3","B4","B5","C"};
        private int[] pontuacaoQualis;
	private double multiplicador;
	private int qtdAnos, pontuacaoMinima;
	
	public RegraPontuacao(Date dataInicio, Date dataFinal, int[] pontuacaoQualis, double multiplicador, int qtdAnos,
			int pontuacaoMinima) {
		this.dataInicio = dataInicio;
		this.dataFinal = dataFinal;
                this.pontuacaoQualis = pontuacaoQualis;
		this.multiplicador = multiplicador;
		this.qtdAnos = qtdAnos;
		this.pontuacaoMinima = pontuacaoMinima;
	}
        
        public String[] getCategoriasQualis (){
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
	
	public int valorQualis (String qualis){
            for (int i=0;i<this.categoriasQualis.length;i++){
                if (Objects.equals(categoriasQualis[i], qualis)){
                    return pontuacaoQualis[i];
                }
            }
            return 0;
        }
	
}