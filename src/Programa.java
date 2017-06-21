import java.io.*;
import java.text.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Programa {

    static LinkedList<Docente> docentes = new LinkedList<Docente>();
    static LinkedList<Publicacao> publicacoes = new LinkedList<Publicacao>();
    static LinkedList<Qualis> listaQualis = new LinkedList<Qualis>();
    static LinkedList<Veiculo> veiculos = new LinkedList<Veiculo>();
    
    static DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    static Date inicioVigencia, fimVigencia;
    
    static String[] categoriasQualis;
    static double[] pontuacaoQualis;
    static double multiplicador, pontuacaoMin;
    static int anos;

    public static void main(String[] args) {
        abrirArquivosDeEntrada();
        System.out.println("teste");
    }

    public static void abrirArquivosDeEntrada() {
        File fileDocente = new File("docentes.csv");
        obterDocentes(fileDocente);

        File filePublicacoes = new File("publicacoes.csv");
        obterPublicacoes(filePublicacoes);
        
        File fileQualis = new File("qualis.csv");
        obterQualis(fileQualis);
        
        File fileVeiculos = new File("veiculos.csv");
        obterVeiculos(fileVeiculos);
        
        File fileRegras = new File("regras.csv");
        obterRegras(fileRegras);
    }

    public static void obterDocentes(File fileDocente) {
        try {
            Scanner sc = new Scanner(fileDocente);
            String linha = sc.nextLine();
            while (sc.hasNextLine()) {
                linha = sc.nextLine();
                String[] dados = linha.split(";");
                boolean coordenador = false;
                long codigo = Long.parseLong(dados[0]);
                String nome = dados[1];
                if ((dados.length > 4)&&(Objects.equals(dados[4],"X"))){
                    coordenador = true;
                }
                try {
                    Date nascimento = format.parse(dados[2]);
                    Date ingresso = format.parse(dados[3]);
                    Docente novoDocente = new Docente (codigo, nome, nascimento, ingresso, coordenador);
                    docentes.add(novoDocente);
                } catch (ParseException e) {
                    //ALGUMA EXCECAO
                }
            }
        } catch (FileNotFoundException ex) {
            //ALGUMA EXCECAO
        }
    }

    public static void obterPublicacoes(File filePublicacoes) {
        try {
            Scanner sc = new Scanner(filePublicacoes);
            String linha = sc.nextLine();
            while (sc.hasNextLine()) {
                linha = sc.nextLine();
                String[] dados = linha.split(";");
                int ano = Integer.parseInt(dados[0]);
                String veiculo = dados[1];
                String titulo = dados[2];
                String[] autores = dados[3].split(",");
                LinkedList<Docente> autoresList = new LinkedList<Docente>();
                for (int i = autores.length;i>0;i--){
                    for (Docente docente : docentes){
                        if (docente.getCodigo() == Long.parseLong(autores[i-1].trim())){
                            autoresList.add(docente);
                        }
                    }
                }
                int numero  = Integer.parseInt(dados[4]);
                int pagInicial = Integer.parseInt(dados[7]);
                int pagFinal = Integer.parseInt(dados[8]);
                if (Objects.equals(dados[5], "")){
                    String local = dados[6];
                    PublicacaoConferencia novaPublicacao = new PublicacaoConferencia(numero, ano, pagInicial, pagFinal, titulo, veiculo, local, autoresList);
                    publicacoes.add(novaPublicacao);
                }
                else if (Objects.equals(dados[6],"")){
                    int volume = Integer.parseInt(dados[5]);
                    PublicacaoPeriodico novaPublicacao = new PublicacaoPeriodico
                                    (numero, ano, volume, pagInicial, pagFinal, titulo, veiculo, autoresList);
                    publicacoes.add(novaPublicacao);
                }
            }
        } catch (FileNotFoundException ex) {
            //ALGUMA EXCECAO
        }
    }
    
    public static void obterQualis(File fileQualis) {
        try {
            Scanner sc = new Scanner(fileQualis);
            String linha = sc.nextLine();
            while (sc.hasNextLine()) {
                linha = sc.nextLine();
                String[] dados = linha.split(";");
                int ano = Integer.parseInt(dados[0]);
                String veiculo = dados[1];
                String qualis = dados[2];
                Qualis novoQualis = new Qualis (ano,veiculo,qualis);
                listaQualis.add(novoQualis);
            }
        } catch (FileNotFoundException ex) {
            //ALGUMA EXCECAO
        }
    }
    
    public static void obterVeiculos(File fileVeiculos) {
        try {
            Scanner sc = new Scanner(fileVeiculos);
            String linha = sc.nextLine();
            while (sc.hasNextLine()) {
                linha = sc.nextLine();
                String[] dados = linha.split(";");
                String sigla = dados[0];
                String nome = dados[1];
                char tipo = dados[2].charAt(0);
                double impacto = Double.parseDouble(dados[3].replace(',', '.'));
                if (dados.length>4){
                    String issn = dados[4];
                }
                else{
                    String issn = null;
                }
                Veiculo novoVeiculo = new Veiculo (sigla, nome, tipo, impacto, nome);
                veiculos.add(novoVeiculo);
            }
        } catch (FileNotFoundException ex) {
            //ALGUMA EXCECAO
        }
    }
    
    public static void obterRegras(File fileRegras) {
        try {
            Scanner sc = new Scanner(fileRegras);
            String linha = sc.nextLine();
            while (sc.hasNextLine()) {
                linha = sc.nextLine();
                String[] dados = linha.split(";");
                try {
                    inicioVigencia = format.parse(dados[0]);
                    fimVigencia = format.parse(dados[1]);
                } catch (ParseException ex) {
                    //ALGUMA EXCECAO
                }
                categoriasQualis = new String[dados.length];    //Confirmar se precisa ou não inicializar assim
                categoriasQualis = dados[2].split(",");
                String[] auxString = dados[3].split(",");
                pontuacaoQualis = new double [auxString.length];    //Mesma situação...
                for (int i=0;i<auxString.length;i++){
                    pontuacaoQualis[i] = Double.parseDouble(auxString[i].trim());
                }
                dados[4]= dados[4].replace(",", ".");
                multiplicador = Double.parseDouble(dados[4].trim());
                anos = Integer.parseInt(dados[5].trim());
                dados[6]=dados[6].replace(",",".");
                pontuacaoMin = Double.parseDouble(dados[6].trim());
                
            }
        } catch (FileNotFoundException ex) {
            //ALGUMA EXCECAO
        }
    }
}
