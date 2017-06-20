import java.util.Date;
import java.util.LinkedList;

public class PublicacaoConferencia extends Publicacao {
	private String local;

	public PublicacaoConferencia() {
		super();
	}

	public PublicacaoConferencia(int numero, int ano, int pgInicial, int pgFinal, String titulo, String siglaveiculo,
			String local, LinkedList<Docente> autores) {
		super(numero, ano, pgInicial, pgFinal, titulo, siglaveiculo, autores);
		this.local = local;
	}
        
        public String getLocal(){
            return local;
        }
}
