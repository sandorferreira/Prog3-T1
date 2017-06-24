package trabalho.Arquivo;
import trabalho.Exceptions.*;
import trabalho.Veiculo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class ArquivoVeiculo extends File{
	HashSet<Veiculo> veiculos;

	public ArquivoVeiculo(String pathname) throws ExceptionFile {
		super(pathname);
		if (!this.exists()) throw new ExceptionFile();

	}
	
	public void loadDataToMemory() {
		Scanner sc;
		try {
            sc = new Scanner(this);
            String linha = sc.nextLine();
            
            while (sc.hasNextLine()) {
                linha = sc.nextLine();
                String[] dados = linha.split(";");
                String sigla = dados[0];                                            //Primeiro: Sigla
                String nome = dados[1];                                             //Segundo: Nome
                char tipo;
                try {
                	tipo = isTipoValid(dados[2].charAt(0), sigla); // joga exceção
                	double impacto = Double.parseDouble(dados[3].replace(',', '.'));    //Quarto: Fator de impacto
                    String issn = null;            //Quinto: ISSN (caso tenha)
                    if (dados.length > 4) {
                        issn = dados[4];
                    }
                    
                    Veiculo novoVeiculo = new Veiculo(sigla, nome, tipo, impacto, issn);
                    veiculos.add(novoVeiculo);
                } catch(TypeVeiculoNotDefinedException e) {
                	e.printStackTrace();
                	System.exit(1); // terminar sem gerar arquivo de saída
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
	}
	
	public HashSet<Veiculo> getVeiculos() {
		return veiculos;
	}
	
	private char isTipoValid(char tipo, String sigla) throws TypeVeiculoNotDefinedException {
		char auxTipo;
		if(tipo == 'P' || tipo == 'C') {
			auxTipo = tipo;
		} else {
			throw new TypeVeiculoNotDefinedException(sigla, tipo);
		}
		return auxTipo;
	}
	
}
