package trabalho.Arquivo;
import trabalho.Exceptions.*;
import trabalho.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public class ArquivoPublicacoes extends File{
	private HashSet<Docente> docentes;
	private HashSet<Veiculo> veiculos;
	
	private HashSet<Publicacao> publicacoes;

	public ArquivoPublicacoes(String pathname) throws ExceptionFile {
		super(pathname);
		if (!this.exists()) throw new ExceptionFile();

	}
	
	public ArquivoPublicacoes(String pathname, HashSet<Docente> docentes, HashSet<Veiculo> veiculos) throws ExceptionFile {
		super(pathname);
		if(!this.exists()) {
			throw new ExceptionFile();
		}
		else {
			this.docentes = docentes;
			this.veiculos = veiculos;
		}
	}
	
	public void loadDataToLocalMemory() {
		Scanner sc;
		try {
			sc = new Scanner(this);
			String linha = sc.nextLine(); // retirando primeira linha CSV
			while(sc.hasNextLine()) {
				linha = sc.nextLine();
                String[] dados = linha.split(";");      //Separa linha
                int ano = Integer.parseInt(dados[0]);       //Primeiro dado: ano
                String siglaVeiculo = dados[1];
                String titulo = dados[2];
                int numero = Integer.parseInt(dados[4]);            //Quinto: número
                int pagInicial = Integer.parseInt(dados[7]);        //Sexto: Página inicial
                int pagFinal = Integer.parseInt(dados[8]);
                
                try {
                	Veiculo v = this.containsVeiculoDeSigla(titulo, siglaVeiculo);
                	// Depois de checado, continua a criação de Publicação
                	
                	String[] codigosAutores = dados[3].split(",");
                	try {
                		HashSet<Docente> autores = this.loadListaAutores(codigosAutores, titulo);
                		
                		// Para conferência
                		if(!("".equals(dados[6]))) {
                			String local = dados[6];
                			this.publicacoes.add(new PublicacaoConferencia(numero, ano, pagInicial, pagFinal, titulo, v, local, autores));
                		}
                		
                		if(!("".equals(dados[5]))) {
                			int volume = Integer.parseInt(dados[5]);
                			this.publicacoes.add(new PublicacaoPeriodico(numero, ano, volume, pagInicial, pagFinal, titulo, v, autores));
                		}
                		
                	} catch(CodigoDocenteNotFoundException e) {
                		e.printStackTrace();
                		System.exit(1); // nao deve gerar arquivos de saída e quitar
                	}
                	
                } catch (SiglaVeiculoNotFoundException e) {
                	e.printStackTrace();
                	System.exit(1); // nao deve gerar arquivos de saída e quitar
                }
              
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Veiculo containsVeiculoDeSigla(String pTitulo, String sigla) throws SiglaVeiculoNotFoundException {
		boolean isVeiculoContained = false;
		Veiculo veiculo = null;
		for(Veiculo v: veiculos) {
			if(v.getISSN().equals(sigla)) {
				veiculo = v;
				isVeiculoContained = true;
			}
		}
		if(!isVeiculoContained) {
			throw new SiglaVeiculoNotFoundException(pTitulo, sigla);
		}
		return veiculo;
	}
	
	private HashSet<Docente> loadListaAutores(String[] autores, String pTitulo) throws CodigoDocenteNotFoundException {
		HashSet<Docente> hashAutores = new HashSet<Docente>();
		for(String codigoAutor: autores) {
			Docente d = this.isCodigoDocenteValid(codigoAutor);
			if(d == null) {
				throw new CodigoDocenteNotFoundException(pTitulo, codigoAutor);
			} else {
				hashAutores.add(d);
			}	
		}
		
		return hashAutores;
	}
	
	private Docente isCodigoDocenteValid(String codigo) {
		Docente returnedDocente = null;
		for(Docente d: docentes) {
			if(d.getCodigo() == Long.parseLong(codigo.trim())) {
				returnedDocente = d;
			}
		}
		return returnedDocente;
	}
	
	public HashSet<Publicacao> getPublicacoes() {
		return publicacoes;
	}
	
}