package trabalho;
import java.util.Date;
import java.util.HashSet;

public class PublicacaoPeriodico extends Publicacao {
	private int volume;

	public PublicacaoPeriodico() {
		super();
	}

	public PublicacaoPeriodico(int numero, int ano, int volume, int pgInicial, int pgFinal, String titulo,
			Veiculo veiculo, HashSet<Docente> autores) {
		super(numero, ano, pgInicial, pgFinal, titulo, veiculo, autores);
		this.volume = volume;
	}
	
	public int getVolume() {
		return volume;
	}
}
