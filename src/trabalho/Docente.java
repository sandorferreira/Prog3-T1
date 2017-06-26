package trabalho;
import java.util.Date;
import java.util.*;

public class Docente {
	private long codigo;
	private String nome;
	private Date dataNascimento;
	private Date dataIngresso;
	private boolean coordenador;
    private HashSet<Publicacao> publicacoes = new HashSet<Publicacao>();
	
	public Docente(long codigo, String nome, Date dataNascimento, Date dataIngresso,
					boolean coordenador) {
		this.codigo = codigo;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.dataIngresso = dataIngresso;
		this.coordenador = coordenador;
	}

	public long getCodigo() {
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
	
    public HashSet<Publicacao> getPublicacoes(){
        return publicacoes;
    }
        
    public void adicionarPublicacao (Publicacao publicacao){
        publicacoes.add(publicacao);
    }
	
}
