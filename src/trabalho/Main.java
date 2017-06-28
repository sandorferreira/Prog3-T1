package trabalho;

import java.io.*;
import java.text.*;
import java.util.*;
import trabalho.Arquivo.*;
import trabalho.Exceptions.*;
import trabalho.Relatorio.*;

public class Main {

    /*
	 * FUNÇÃO MAIN
	 * Falta: impementar argumentos para nome de arquivos de entrada
     */
    private static LinkedList<Docente> docentes = new LinkedList<Docente>();
    private static LinkedList<Publicacao> publicacoes = new LinkedList<Publicacao>();
    private static LinkedList<Veiculo> veiculos = new LinkedList<Veiculo>();
    private static RegraPontuacao regra;

    public static void main(String[] args) {

        /*
    	 * Criando arquivos:
    	 * Docente, Veículo, Publicação, Qualificação e Regra
         */
        ArquivoDocente arqDocente;
        ArquivoVeiculo arqVeiculo;
        ArquivoPublicacoes arqPublicacoes;
        ArquivoQualificacoes arqQualificacoes;
        ArquivoRegras arqRegras;

        /*
    	 * Criando os arquivos:
    	 * Tratando as Exceções de I/O caso o arquivo não exista
    	 * Substituir posteriormente pelos argumentos
         */
        try {
            arqDocente = new ArquivoDocente("docentes.csv");
            docentes = arqDocente.getDocentes();
        } catch (ExceptionFile e) {
            printAndStop(e.getMessage());
        }

        try {
            arqVeiculo = new ArquivoVeiculo("veiculos.csv");
            veiculos = arqVeiculo.getVeiculos();
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
            arqQualificacoes.setRegra(regra);
            //setListaQualis(arqQualificacoes.getQualis());
        } catch (ExceptionFile e) {
            printAndStop(e.getMessage());
        }

        /* 
		 * Todos os arquivos lidos
		 * Gerando os Relatórios...
         */
        RelatorioPublicacoes rPublicacoes = new RelatorioPublicacoes("2-publicacoes.csv", publicacoes);
        rPublicacoes.write();

        //Utilizando publicações ordenadas do relatório 2 para o relatório 3
        LinkedList<Publicacao> publicacoesOrdenadas = rPublicacoes.ordenar();

        RelatorioEstatisticas rEstatisticas = new RelatorioEstatisticas("3-estatisticas.csv", publicacoesOrdenadas);
        rEstatisticas.write();

        RelatorioRecredenciamento rRecredenciamento = new RelatorioRecredenciamento("1-recredenciamento.csv", docentes, regra);
        rRecredenciamento.write();
    }

    // Função auxiliar para printar na tela e parar a Main Thread
    public static void printAndStop(String s) {
        System.out.println(s);
        //(1);
    }
}
