package trabalho.Arquivo;
import trabalho.Exceptions.*;
import trabalho.Veiculo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class ArquivoVeiculo extends File{
	LinkedList<Veiculo> veiculos = new LinkedList<Veiculo>();
	private String pathname;

	public ArquivoVeiculo(String pathname) throws ExceptionFile {
		super(pathname);
		this.pathname = pathname;
		if (!(new File(pathname).exists())) throw new ExceptionFile();
		//this.loadDataToMemory();
	}
	
	public void loadDataToMemory() {
		Scanner sc;
		try {
            sc = new Scanner(this);
            String linha = sc.nextLine();
            
            while (sc.hasNextLine()) {
                linha = sc.nextLine();
                //System.out.println(linha);
                String[] dados = linha.split(";");
                String sigla = dados[0].trim();
                //System.out.println(sigla);
                
                //Primeiro: Sigla
                String nome = dados[1].trim(); 
                char tipo;
                try {
                	tipo = isTipoValid(dados[2].charAt(0), sigla); // joga exceção
                	double impacto = Double.parseDouble(dados[3].replace(',', '.'));
                	//Quarto: Fator de impacto
                    String issn = null;            //Quinto: ISSN (caso tenha)
                    if (dados.length > 4) {
                        issn = dados[4];
                    }
                    
                    Veiculo novoVeiculo = new Veiculo(sigla, nome, tipo, impacto, issn);
                    //System.out.println(novoVeiculo);
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
		//this.veiculos = auxVeiculos;
		//System.out.println(auxVeiculos);
	}
	
	public LinkedList<Veiculo> getVeiculos() {
		this.loadDataToMemory();
		System.out.println(veiculos);
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
