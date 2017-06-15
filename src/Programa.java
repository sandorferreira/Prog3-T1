import java.io.File;

public class Programa {
	public static void main(String[] args) {
	
	}
	
	public static void abrirArquivosDeEntrada() {
		File fileDocente = new File("docentes.csv");
		if (fileDocente.canRead()) {
			// criar objetos docente e ir ao proximo
		}
		
		File filePublicacoes = new File("publicacoes.csv");
		
		File fileQualis = new File("qualis.csv");
		
		File fileRegras = new File("regras.csv");
		
		File fileVeiculos = new File("veiculos.csv");
	}
	
}
