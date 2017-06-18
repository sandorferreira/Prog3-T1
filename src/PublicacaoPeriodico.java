import java.util.Date;
import java.util.LinkedList;

public class PublicacaoPeriodico extends Publicacao {
	private int numero;
	private int ano;
	private int pgInicial, pgFinal;
	private String titulo;
	private String siglaveiculo;
	private String local;
	private LinkedList<Docente> autores;
	
	// Particular de Publicacao Periodico
	private int volume;

	public PublicacaoPeriodico() {
		super();
	}

	public PublicacaoPeriodico(int numero, int ano, int volume, int pgInicial, int pgFinal, String titulo,
			String siglaveiculo, LinkedList<Docente> autores) {
		super(numero, ano, pgInicial, pgFinal, titulo, siglaveiculo, autores);
		this.volume = volume;
	}
	
	public int getVolume() {
		return volume;
	}
        
        public String toString(){
            return (this.titulo+" o volume é "+this.volume);
        }

}
