package trabalho.Exceptions;
import trabalho.Docente;

public class CodigoDocenteException extends Exception {
	
	public CodigoDocenteException(){
		super("Código Docente já adicionado");
	}
	
	public CodigoDocenteException(Docente docente) {
		super("Código repetido para " + docente.toString() + ": " + docente.getCodigo());
	}
	
	
//	public String toString() {
//		return "Erro de I/O";
//	}

}
