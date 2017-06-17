
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable;

public class Programa {

    static LinkedList<Docente> docentes = new LinkedList<Docente>();

    public static void main(String[] args) {
        abrirArquivosDeEntrada();
        for (Docente docente : docentes){
            System.out.println(docente.getCodigo()+" "+docente.getNome()+" "+docente.getDataNascimento()+" "+docente.getDataIngresso()+" "+docente.isCoordenador());
        }
    }

    public static void abrirArquivosDeEntrada() {
        File fileDocente = new File("docentes.csv");
        obterDocentes(fileDocente);

        File filePublicacoes = new File("publicacoes.csv");

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
                    System.out.println("chegou");
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

}
