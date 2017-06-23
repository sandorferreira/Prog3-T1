import java.util.Date;
import java.util.LinkedList;

public abstract class Publicacao {
	private int numero;
	private int ano;
	private int pgInicial, pgFinal;
	private String titulo;
        private Veiculo veiculo;
	private String siglaveiculo;
	private LinkedList<Docente> autores;
	
	public Publicacao() {
		System.out.println("Publicacao construida sem parametros");
	}
	
	
	public Publicacao(int numero, int ano, int pgInicial, int pgFinal, String titulo, Veiculo veiculo, LinkedList<Docente> autores) {
		this.numero = numero;
		this.ano = ano;
		this.pgInicial = pgInicial;
		this.pgFinal = pgFinal;
		this.titulo = titulo;
		this.veiculo = veiculo;
		this.autores = autores;
	}


	public int getNumero() {
		return numero;
	}


	public int getAno() {
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


	public Veiculo getVeiculo() {
		return veiculo;
	}


	public LinkedList<Docente> getAutores() {
		return autores;
	}
	
}
