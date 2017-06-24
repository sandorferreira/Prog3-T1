package trabalho;
import java.util.Date;
import java.util.HashSet;

public class PublicacaoConferencia extends Publicacao {
	private String local;

	public PublicacaoConferencia() {
		super();
	}

	public PublicacaoConferencia(int numero, int ano, int pgInicial, int pgFinal, String titulo, Veiculo veiculo,
			String local, HashSet<Docente> autores) {
		super(numero, ano, pgInicial, pgFinal, titulo, veiculo, autores);
		this.local = local;
	}
        
        public String getLocal(){
            return local;
        }
}
