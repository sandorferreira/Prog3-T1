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
                /* 
                 * Verificando se há veículo com a sigla lida
                 */
                
                try {
                	Veiculo v = this.containsVeiculoDeSigla(titulo, siglaVeiculo);
                	
                } catch (SiglaVeiculoNotFoundException e) {
                	
                }
                
                //Encontra um veículo na lista, com a mesma sigla
                for (Veiculo auxVeiculo : veiculos){
                    if (Objects.equals(auxVeiculo.getSigla(),siglaVeiculo)){
                        veiculo = auxVeiculo;
                    }
                }
                
                           //Terceiro: título
                String[] autores = dados[3].split(",");             //Quarto: docentes      Esses são separados pela ',' em um vetor de String
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Veiculo containsVeiculoDeSigla(String pTitulo, String sigla) throws Exception {
		boolean isVeiculoContained = false;
		Veiculo veiculo = null;
		for(Veiculo v: veiculos) {
			if(v.getISSN().equals(sigla)) {
				veiculo = v;
				isVeiculoContained = true;
			}
		}
		if(!isVeiculoContained) {
			throw new SiglaVeiculoNotFoundException();
		}
		return veiculo;
	}
	
	private HashSet<Docente> criaListaAutores(String[] autores) throws CodigoDocenteNotFoundException {
		boolean isAutoresValid = true;
		for(String codigoAutor: autores) {
			for(Docente d: docentes) {
				//if(d.getCodigo())
					// parse codigo para long
					// pensar melhor
			}
		}
		
	}
	
}