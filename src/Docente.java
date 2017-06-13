import java.util.Date;

public class Docente {
	private int codigo;
	private String nome;
	private Date dataNascimento;
	private Date dataIngresso;
	private boolean coordenador;
	
	public Docente(int codigo, String nome, Date dataNascimento, Date dataIngresso,
					boolean coordenador) {
		this.codigo = codigo;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.dataIngresso = dataIngresso;
		this.coordenador = coordenador;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public Date getDataIngresso() {
		return dataIngresso;
	}

	public boolean isCoordenador() {
		return coordenador;
	}
	
	
}
