import java.util.Date;

public class PublicacaoConferencia extends Publicacao {
	private int numero;
	private Date ano;
	private int pgInicial, pgFinal;
	private String titulo;
	private String siglaveiculo;
	private Docente[] autores;
	
	// Particular de Publicacao Conferencia
	private String local;

	public PublicacaoConferencia() {
		super();
	}

	public PublicacaoConferencia(int numero, Date ano, int pgInicial, int pgFinal, String titulo, String siglaveiculo,
			String local, Docente[] autores) {
		super(numero, ano, pgInicial, pgFinal, titulo, siglaveiculo, autores);
		this.local = local;
	}
	
}
