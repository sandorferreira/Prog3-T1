package trabalho.Arquivo;
import trabalho.Exceptions.*;
import trabalho.Qualis;
import trabalho.RegraPontuacao;
import trabalho.Veiculo;
import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ArquivoQualificacoes extends File {
	private HashSet<Qualis> qualificacoes = new HashSet<Qualis>();
	private HashSet<Veiculo> veiculos = new HashSet<Veiculo>();
	private HashSet<Veiculo> novoVeiculos = new HashSet<Veiculo>();
	private RegraPontuacao regra;
	
	public ArquivoQualificacoes(String pathname, HashSet<Veiculo> veiculos) throws ExceptionFile {
		super(pathname);
		this.veiculos = veiculos;
		if(!this.exists()) throw new ExceptionFile();
		this.loadToLocalMemory();
	}
	
	public void loadToLocalMemory() {
		Scanner sc;
		try {
            sc = new Scanner(this);
            String linha = sc.nextLine();
            
            //Novamente lê as linhas
            while (sc.hasNextLine()) {
                linha = sc.nextLine();
                String[] dados = linha.split(";");
                int ano = Integer.parseInt(dados[0]);           //Primeiro: ano
                String siglaVeiculo = dados[1].trim();                 //Segundo: Sigla do veículo
                String qualis = dados[2];  
                //Terceiro: Classificação Qualis
                
                Qualis novoQualis = new Qualis(ano, qualis);    
                qualificacoes.add(novoQualis);
                
                try {
                	Veiculo v = this.getVeiculoWithSigla(ano, siglaVeiculo);
                	try {
                		this.isValidQualis(novoQualis, siglaVeiculo);
                		v.getListaQualis().add(novoQualis);
                		novoVeiculos.add(v);
                		//System.out.println(v.getListaQualis());
                	} catch (QualisNotFoundException e) {
                		System.out.println(e.getMessage());
                        System.exit(1);
                	}
                } catch (SiglaVeiculoNotFoundException e) {
                	System.out.println(e.getMessage());
                	System.exit(1);
                }
                
                //O 'Qualis' é criado e adicionado na lista geral, em seguida é encontrado o veículo com a mesma sigla e o qualis é atribuido a lista de qualis do veículo
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
	}
	
	public Veiculo getVeiculoWithSigla(int ano, String sigla) throws SiglaVeiculoNotFoundException {
		Veiculo returnedVeiculo = null;
		//System.out.println(veiculos);
		for(Veiculo auxVeiculo: veiculos) {
			if(auxVeiculo.getSigla().equals(sigla)) {
				returnedVeiculo = auxVeiculo;
				//System.out.println(returnedVeiculo);
				//System.out.println(sigla);
			}
		}
		
		if(returnedVeiculo == null) {
			throw new SiglaVeiculoNotFoundException(ano, sigla);
		}
		
		return returnedVeiculo;
	}
	
	public boolean isValidQualis(Qualis qualis, String vSigla) throws QualisNotFoundException {
		boolean isValidQualis = true;
		if (!qualis.isValidQualis()) {
			isValidQualis = false;
			throw new QualisNotFoundException(vSigla, qualis.getAno(), qualis.getQualis());
		}
		return isValidQualis;
	}
	
	public HashSet<Qualis> getQualis() {
		//this.loadToLocalMemory();
		return qualificacoes;
	}
	
	public HashSet<Veiculo> getVeiculosModificados() {
		return novoVeiculos;
	}
	
	public void setRegra(RegraPontuacao regra) {
		this.regra = regra;
		this.colocarPontuacaoQualis();
	}
	
	private void colocarPontuacaoQualis(){
        for (Qualis auxQualis : qualificacoes){
            auxQualis.setPontuacao(regra.valorQualis(auxQualis.getQualis()));
        }
    }

}
