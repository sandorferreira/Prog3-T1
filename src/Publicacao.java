import java.util.Date;
import java.util.LinkedList;

public abstract class Publicacao {
	private int numero;
	private Date ano;
	private int pgInicial, pgFinal;
	private String titulo;
	private String siglaveiculo;
	private LinkedList<Docente> autores;
	
	public Publicacao() {
		System.out.println("Publicacao construida sem parametros");
	}
	
	
	public Publicacao(int numero, Date ano, int pgInicial, int pgFinal, String titulo, String siglaveiculo, LinkedList<Docente> autores) {
		super();
		this.numero = numero;
		this.ano = ano;
		this.pgInicial = pgInicial;
		this.pgFinal = pgFinal;
		this.titulo = titulo;
		this.siglaveiculo = siglaveiculo;
		this.autores = autores;
	}


	public int getNumero() {
		return numero;
	}


	public Date getAno() {
		return ano;
	}


	public int getPgInicial() {
		return pgInicial;
	}


	public int getPgFinal() {
		return pgFinal;
	}


	public String getTitulo() {
		return titulo;
	}


	public String getSiglaveiculo() {
		return siglaveiculo;
	}


	public LinkedList<Docente> getAutores() {
		return autores;
	}
	
	
	
	
}
