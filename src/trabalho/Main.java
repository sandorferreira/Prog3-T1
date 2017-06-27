package trabalho;
import java.io.*;
import java.text.*;
import java.util.*;
import trabalho.Arquivo.*;
import trabalho.Exceptions.*;
import trabalho.Relatorio.*;

public class Main {

    static LinkedList<Docente> docentes = new LinkedList<Docente>();
    static LinkedList<Publicacao> publicacoes = new LinkedList<Publicacao>();
    static LinkedList<Qualis> listaQualis = new LinkedList<Qualis>();
    static LinkedList<Veiculo> veiculos = new LinkedList<Veiculo>();
    static RegraPontuacao regra;

   // static DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
    	ArquivoDocente arqDocente;
    	ArquivoVeiculo arqVeiculo;
    	ArquivoPublicacoes arqPublicacoes;
    	ArquivoQualificacoes arqQualificacoes;
    	ArquivoRegras arqRegras;
    	
		try {
			arqDocente = new ArquivoDocente("docentes.csv");
			docentes = arqDocente.getDocentes();
		} catch (ExceptionFile e) {
			printAndStop(e.getMessage());
		}
		
		try {
			arqVeiculo = new ArquivoVeiculo("veiculos.csv");
			veiculos = arqVeiculo.getVeiculos();
			//System.out.println(veiculos);
		} catch (ExceptionFile e) {
			printAndStop(e.getMessage());
		}
		
		try {
			arqPublicacoes = new ArquivoPublicacoes("publicacoes.csv", docentes, veiculos);
			publicacoes = arqPublicacoes.getPublicacoes();
		} catch (ExceptionFile e) {
			printAndStop(e.getMessage());
		}
		
		try {
			arqRegras = new ArquivoRegras("regras.csv");
			regra = arqRegras.getRegra();
		} catch (ExceptionFile e) {
			printAndStop(e.getMessage());
		}
		
		try {
			arqQualificacoes = new ArquivoQualificacoes("qualis.csv", veiculos);
			//listaQualis = arqQualificacoes.getQualis();
			arqQualificacoes.setRegra(regra);
			listaQualis = arqQualificacoes.getQualis();
			//veiculos = arqQualificacoes.getVeiculosModificados();
		} catch (ExceptionFile e) {
			printAndStop(e.getMessage());
		}
		
		//System.out.println(publicacoes);
		
		// Todos os arquivos lidos, gerar relatório
		
		RelatorioPublicacoes rPublicacoes = new RelatorioPublicacoes("2-publicacoes.csv", publicacoes);
		rPublicacoes.write();
		
		//arqQualificacoes.setRegra(regra);
		
		
    	
    	
//        abrirArquivosDeEntrada();
//        colocarPontuacaoQualis();
        System.out.println("terminou");
    }

//    public static void abrirArquivosDeEntrada() {
//        File fileDocente = new File("docentes.csv");
//        obterDocentes(fileDocente);
//
//        File fileVeiculos = new File("veiculos.csv");
//        obterVeiculos(fileVeiculos);
//        
//        File filePublicacoes = new File("publicacoes.csv");
//        obterPublicacoes(filePublicacoes);
//
//        File fileQualis = new File("qualis.csv");
//        obterQualis(fileQualis);
//
//        File fileRegras = new File("regras.csv");
//        obterRegras(fileRegras);
//    }

//    public static void obterDocentes(File fileDocente) {
//        try {
//            Scanner sc = new Scanner(fileDocente);
//            String linha = sc.nextLine(); //Retirar a primeira linha com nome das colunas
//            
//            //O 'while' que faz a leitura das linhas do texto
//            while (sc.hasNextLine()) {
//                linha = sc.nextLine();
//                String[] dados = linha.split(";");  //Divide cada linha usando o ';' como divisor
//                boolean coordenador = false;
//                long codigo = Long.parseLong(dados[0]);     //Primeiro dado, código do docente
//                String nome = dados[1];                     //Segundo dado, nome do docente
//                
//                //Confirma se possui um 5º dado, e se é um 'X', ou seja, coordenador
//                if ((dados.length > 4) && (Objects.equals(dados[4], "X"))) {
//                    coordenador = true;
//                }
//                try {
//                    Date nascimento = format.parse(dados[2]);   //Terceiro dado, nascimento
//                    Date ingresso = format.parse(dados[3]);     //Quarto dado, ingresso
//                    Docente novoDocente = new Docente(codigo, nome, nascimento, ingresso, coordenador);
//                    docentes.add(novoDocente);
//                } catch (ParseException e) {
//                    //ALGUMA EXCECAO
//                }
//            }
//        } catch (FileNotFoundException ex) {
//            //ALGUMA EXCECAO
//        }
//    }
//
//    public static void obterPublicacoes(File filePublicacoes) {
//        try {
//            Scanner sc = new Scanner(filePublicacoes);
//            String linha = sc.nextLine();       //Novamente retira a linha com nome de colunas
//            
//            //Enquanto tiver linhas para serem lidas, faz o mesmo que a função anterior, porém para publicações
//            while (sc.hasNextLine()) {
//                linha = sc.nextLine();
//                String[] dados = linha.split(";");      //Separa linha
//                int ano = Integer.parseInt(dados[0]);       //Primeiro dado: ano
//                String siglaVeiculo = dados[1];             //Segundo: sigla do veículo
//                Veiculo veiculo = null;
//                
//                //Encontra um veículo na lista, com a mesma sigla
//                for (Veiculo auxVeiculo : veiculos){
//                    if (Objects.equals(auxVeiculo.getSigla(),siglaVeiculo)){
//                        veiculo = auxVeiculo;
//                    }
//                }
//                
//                String titulo = dados[2];               //Terceiro: título
//                String[] autores = dados[3].split(",");             //Quarto: docentes      Esses são separados pela ',' em um vetor de String
//                
//                //É então criado uma lista de docentes, e adiciona os docentes que estão no vetor de String
//                LinkedList<Docente> autoresList = new LinkedList<Docente>();
//                for (int i = autores.length; i > 0; i--) {
//                    for (Docente docente : docentes) {
//                        if (docente.getCodigo() == Long.parseLong(autores[i - 1].trim())) {
//                            autoresList.add(docente);
//                        }
//                    }
//                }
//                
//                int numero = Integer.parseInt(dados[4]);            //Quinto: número
//                int pagInicial = Integer.parseInt(dados[7]);        //Sexto: Página inicial
//                int pagFinal = Integer.parseInt(dados[8]);          //Sétimo: Página final
//                
//                //Caso encontre o sexto dado (local), ele cria a publicação como uma 'Conferência'
//                if (Objects.equals(dados[5], "")) {
//                    String local = dados[6];
//                    PublicacaoConferencia novaPublicacao = new PublicacaoConferencia(numero, ano, pagInicial, pagFinal, titulo, veiculo, local, autoresList);
//                    publicacoes.add(novaPublicacao);
//                    for (Docente auxDocente : autoresList) {
//                        auxDocente.adicionarPublicacao(novaPublicacao);
//                    }
//                    
//                //Caso encontre o sétimo dado (volume), ele cria como 'Periódico'
//                } else if (Objects.equals(dados[6], "")) {
//                    int volume = Integer.parseInt(dados[5]);
//                    PublicacaoPeriodico novaPublicacao = new PublicacaoPeriodico(numero, ano, volume, pagInicial, pagFinal, titulo, veiculo, autoresList);
//                    publicacoes.add(novaPublicacao);
//                    for (Docente auxDocente : autoresList) {
//                        auxDocente.adicionarPublicacao(novaPublicacao);
//                    }
//                }
//            }
//        } catch (FileNotFoundException ex) {
//            //ALGUMA EXCECAO
//        }
//    }
//
//    public static void obterQualis(File fileQualis) {
//        try {
//            Scanner sc = new Scanner(fileQualis);
//            String linha = sc.nextLine();
//            
//            //Novamente lê as linhas
//            while (sc.hasNextLine()) {
//                linha = sc.nextLine();
//                String[] dados = linha.split(";");
//                int ano = Integer.parseInt(dados[0]);           //Primeiro: ano
//                String siglaVeiculo = dados[1];                 //Segundo: Sigla do veículo
//                String qualis = dados[2];                       //Terceiro: Classificação Qualis
//                
//                //O 'Qualis' é criado e adicionado na lista geral, em seguida é encontrado o veículo com a mesma sigla e o qualis é atribuido a lista de qualis do veículo
//                Qualis novoQualis = new Qualis(ano, qualis);    
//                listaQualis.add(novoQualis);
//                for (Veiculo auxVeiculo : veiculos){
//                    if (Objects.equals(auxVeiculo.getSigla(), siglaVeiculo)){
//                        auxVeiculo.getListaQualis().add(novoQualis);
//                    }
//                }
//            }
//        } catch (FileNotFoundException ex) {
//            //ALGUMA EXCECAO
//        }
//    }
//
//    public static void obterVeiculos(File fileVeiculos) {
//        try {
//            Scanner sc = new Scanner(fileVeiculos);
//            String linha = sc.nextLine();
//            
//            while (sc.hasNextLine()) {
//                linha = sc.nextLine();
//                String[] dados = linha.split(";");
//                String sigla = dados[0];                                            //Primeiro: Sigla
//                String nome = dados[1];                                             //Segundo: Nome
//                char tipo = dados[2].charAt(0);                                     //Terceiro: Tipo de publicação (conferência ou periódico)
//                double impacto = Double.parseDouble(dados[3].replace(',', '.'));    //Quarto: Fator de impacto
//                String issn;            //Quinto: ISSN (caso tenha)
//                if (dados.length > 4) {
//                    issn = dados[4];
//                } else {
//                    issn = null;
//                }
//                Veiculo novoVeiculo = new Veiculo(sigla, nome, tipo, impacto, issn);
//                veiculos.add(novoVeiculo);
//            }
//        } catch (FileNotFoundException ex) {
//            //ALGUMA EXCECAO
//        }
//    }
//
//    public static void obterRegras(File fileRegras) {
//        try {
//            Scanner sc = new Scanner(fileRegras);
//            String linha = sc.nextLine();
//            String[] todosQualis = {"A1","A2","B1","B2","B3","B4","B5","C"};        //Lista usado como base para as classificações
//            linha = sc.nextLine();
//            String[] dados = linha.split(";");
//            Date inicioVigencia = new Date();
//            Date fimVigencia = new Date();
//            try {
//                inicioVigencia = format.parse(dados[0]);        //Primeiro: Data de início
//                fimVigencia = format.parse(dados[1]);           //Segundo: Data final
//            } catch (ParseException ex) {
//            	System.out.println("Erro de formatação");
//            	System.exit(1);
//            	//Termina sem produzir arquivos de saída
//                //ALGUMA EXCECAO
//            }
//            String[] categoriasQualis = new String[dados.length];    //PRECISA INICIALIZAR ASSIM???
//            categoriasQualis = dados[2].split(",");         //Terceiro: Classificações (Dividido em string usando a ',' como divisor
//            String[] auxString = dados[3].split(",");       //Quarto: Pontuação das classificações (Divide igual o anterior)
//            int[] pontuacaoQualis = new int[8];    //PRECISA INICIALIZAR ASSIM???
//            int auxInt;
//            
//            
//            //O 'for' é para adicionar a pontuação das classificações que não estão na lista, comparando com a lista base criado no começo da função
//            for (int i=0;i<categoriasQualis.length;){
//                for (int j=0;j<8;){
//                    if (Objects.equals(categoriasQualis[i], todosQualis[j])){
//                        pontuacaoQualis[j] = Integer.parseInt(auxString[i].trim());
//                        i++;
//                        j++;
//                    }
//                    else {
//                        pontuacaoQualis[j] = Integer.parseInt(auxString[i-1].trim());
//                        j++;
//                    }
//                }
//            }
//            
//            dados[4] = dados[4].replace(",", ".");                          //Muda ',' para '.' para ser reconhecido como um número
//            double multiplicador = Double.parseDouble(dados[4].trim());     //Quinto: Multiplicador
//            int anos = Integer.parseInt(dados[5].trim());                   //Sexto: Anos
//            int pontuacaoMin = Integer.parseInt(dados[6].trim());           //Sétimo: Pontuação mínima
//            regra = new RegraPontuacao(inicioVigencia, fimVigencia, pontuacaoQualis, multiplicador, anos, pontuacaoMin);
//
//        } catch (FileNotFoundException ex) {
//            //ALGUMA EXCECAO
//        }
//    }
//    
//    public static void colocarPontuacaoQualis(){
//        for (Qualis auxQualis : listaQualis){
//            auxQualis.setPontuacao(regra.valorQualis(auxQualis.getQualis()));
//        }
//    }
    
    public static void printAndStop(String s) {
    	System.out.println(s);
    	System.exit(1);
    }
    
}
