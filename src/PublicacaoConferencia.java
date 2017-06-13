import java.util.Date;
import java.util.LinkedList;

public class PublicacaoConferencia extends Publicacao {
	private int numero;
	private Date ano;
	private int pgInicial, pgFinal;
	private String titulo;
	private String siglaveiculo;
	private LinkedList<Docente> autores;
	
	// Particular de Publicacao Conferencia
	private String local;

	public PublicacaoConferencia() {
		super();
	}

	public PublicacaoConferencia(int numero, Date ano, int pgInicial, int pgFinal, String titulo, String siglaveiculo,
			String local, LinkedList<Docente> autores) {
		super(numero, ano, pgInicial, pgFinal, titulo, siglaveiculo, autores);
		this.local = local;
	}
	
}
