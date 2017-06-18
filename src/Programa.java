
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable;

public class Programa {

    static LinkedList<Docente> docentes = new LinkedList<Docente>();
    static LinkedList<Publicacao> publicacoes = new LinkedList<Publicacao>();

    public static void main(String[] args) {
        abrirArquivosDeEntrada();

    }

    public static void abrirArquivosDeEntrada() {
        File fileDocente = new File("docentes.csv");
        obterDocentes(fileDocente);

        File filePublicacoes = new File("publicacoes.csv");
        obterPublicacoes(filePublicacoes);
        
        File fileQualis = new File("qualis.csv");

        File fileRegras = new File("regras.csv");

        File fileVeiculos = new File("veiculos.csv");
    }

    public static void obterDocentes(File fileDocente) {
        try {
            Scanner sc = new Scanner(fileDocente);
            String linha = sc.nextLine();
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
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
                        if (docente.getCodigo() == Long.parseLong(autores[i-1])){
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
}
