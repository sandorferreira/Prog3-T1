package trabalho.Relatorio;
import trabalho.*;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.LinkedList;
import java.util.Vector;
import java.io.FileWriter;
import java.io.IOException;


public class RelatorioPublicacoes {
	private LinkedList<Publicacao> publicacoes;// = new LinkedList<Publicacao>();
	private String pathname;
	//private LinkedList<Publicacao> publicacoesOrdenadas = new LinkedList<Publicacao>();
	private String[] categoriasQualis = {"A1","A2","B1","B2","B3","B4","B5","C"};
	
	// Writer
	private static final String COMMA_DELIMITER = ";";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String FILE_HEADER = "Ano;Sigla Veículo;Veículo;Qualis;Fator de Impacto;Título;Docentes";

	
	public RelatorioPublicacoes(String pathname, LinkedList<Publicacao> publicacoes) {
		this.publicacoes = publicacoes;
		this.pathname = pathname;
	}
	
	private LinkedList<Publicacao> ordenarPorQualis(String qualis) {
		LinkedList<Publicacao> auxPub = new LinkedList<Publicacao>();
		
		for(Publicacao auxPublicacao: publicacoes) {
			if(auxPublicacao.getQualis().equals(qualis)) {
				auxPub.add(auxPublicacao);
			}
		}
		return auxPub;
	}
	
	private int getMaiorAno(LinkedList<Publicacao> auxPubs) {
		int maiorAno = 0; //auxPubs.iterator().next().getAno();
		for(Publicacao auxPub: auxPubs) {
			maiorAno = auxPub.getAno();
			break;
		}
		System.out.println("Continuou?");
		for(Publicacao auxPublicacao: auxPubs) {
			if(auxPublicacao.getAno() >= maiorAno) {
				maiorAno = auxPublicacao.getAno();
			}
		}
		return maiorAno;
	}
	
	private LinkedList<Publicacao> ordenarPorAno(int maiorAno, int ano, LinkedList<Publicacao> pubPorQualis) {
		LinkedList<Publicacao> auxSiglas = new LinkedList<Publicacao>();
		
		//if(maiorAno - ano > 10) {
			for(Publicacao auxPub: pubPorQualis) {
				if(auxPub.getAno() == ano) {
					auxSiglas.add(auxPub);
				}
			}
			//ordenando por sigla
			auxSiglas = this.ordenarPorSigla(auxSiglas);
			return auxSiglas;
	}
	
	
	private LinkedList<Publicacao> ordenarPorSigla(LinkedList<Publicacao> auxPubs) {
		//LinkedList<Publicacao> auxSiglas = new LinkedList<Publicacao>();
		LinkedList<Publicacao> returnedSiglaAndTitulo = new LinkedList<Publicacao>();
		Vector<String> auxSiglasString = new Vector<String>();
		for(Publicacao auxPub : auxPubs) {
			auxSiglasString.add(auxPub.getVeiculo().getSigla());
		}
		auxSiglasString = this.getSortedStringArray(auxSiglasString);
		
		for(String sigla : auxSiglasString) {
			LinkedList<Publicacao> auxSiglaHS = new LinkedList<Publicacao>();
			for(Publicacao auxPub : auxPubs) {
				if(auxPub.getVeiculo().getSigla().equals(sigla)) {
					auxSiglaHS.add(auxPub);
				}
			}
			
			//LinkedList<Publicacao> vetorSiglas = this.ordenaPorSigla(auxPubs, sigla);
			LinkedList<Publicacao> vetorTituloOrdenado = new LinkedList<Publicacao>();
			Vector<String> titulos = new Vector<String>();
			for(Publicacao auxPublicacaoTitulo : auxSiglaHS) {
				titulos.add(auxPublicacaoTitulo.getTitulo());
			}
			titulos = this.getSortedStringArray(titulos);
			for(String titulo : titulos) {
				for(Publicacao auxPub : auxSiglaHS) {
					if(titulo.equals(auxPub.getTitulo())) {
						vetorTituloOrdenado.add(auxPub);
					}
				}
			}
			for(Publicacao okok : vetorTituloOrdenado) {
				returnedSiglaAndTitulo.add(okok);
			}
			//returnedSiglaAndTitulo.addAll(vetorTituloOrdenado);
		}
		return returnedSiglaAndTitulo;
//		
//		
//		int i = 0;
//		for(Publicacao auxPub: auxPubs) {
//			if(auxPub.getVeiculo().getSigla().equals(auxSiglasString.elementAt(i))) {
//					auxSiglas.add(auxPub);
//					i++;
//			}
//		}
//		return auxSiglas;
	}
	
	private LinkedList<Publicacao> ordenaPorSigla(LinkedList<Publicacao> pubAno, String sigla) {
		LinkedList<Publicacao> auxPubPorSigla = new LinkedList<Publicacao>();
		for(Publicacao auxPub: pubAno) {
			if(auxPub.getVeiculo().getSigla().equals(sigla)) {
				auxPubPorSigla.add(auxPub);
			}
		}
		
		return auxPubPorSigla;
	}
	
//	private LinkedList<Publicacao> ordenarPorTitulo(LinkedList<Publicacao> auxPubs) {
//		Vector<String> auxTitulos = new Vector<String>();
//		LinkedList<Publicacao> auxTitulosPub = new LinkedList<Publicacao>();
//		for(Publicacao auxPub: auxPubs) {
//			auxTitulos.add(auxPub.getTitulo());
//		}
//		auxTitulos = this.getSortedStringArray(auxTitulos);
//		int i = 0;
//		for(Publicacao auxPub: auxPubs) {
//			if(auxPub.getTitulo().equals(auxTitulos.elementAt(i))) {
//				//if(!auxSiglas.contains(auxPub)) {
//					auxTitulosPub.add(auxPub);
//					i++;
//				//}	
//			}
//		}
//		return auxTitulosPub;
//	}
	
	
	
	private LinkedList<Publicacao> ordenar() {
		LinkedList<Publicacao> hashOrdenada = new LinkedList<Publicacao>();
		
		for(Publicacao auxPublicacao: publicacoes) {
			LinkedList<Qualis> qualisPossiveis = auxPublicacao.getVeiculo().getListaQualis();
		
			//Qualis qualisPublicacao = new Qualis();
			for(Qualis auxQualis : qualisPossiveis) {
//				if(auxQualis.getAno() == auxPublicacao.getAno()) {
//					//qualisPublicacao = auxQualis;
					auxPublicacao.setQualis(auxQualis.getQualis());
//				}
			}
		}
		
		for(String categoriaQualis : categoriasQualis) {
			// ordenando por qualis
			LinkedList<Publicacao> auxPubs = new LinkedList<Publicacao>();
			LinkedList<Publicacao> pubPorQualis = this.ordenarPorQualis(categoriaQualis);
			
//			for(Publicacao tata: pubPorQualis) {
//				System.out.println(tata.getQualis());
//			}
			
			//System.out.println(pubPorQualis);
			// ordenando por ano
			//System.out.println(pubPorQualis);
			int maiorAno = this.getMaiorAno(pubPorQualis);
			System.out.println(maiorAno);
			for(int ano = maiorAno; ano>2007;ano--) {
				LinkedList<Publicacao> ordenadoPorAno = this.ordenarPorAno(maiorAno, ano, pubPorQualis);
				for(Publicacao okok : ordenadoPorAno) {
					auxPubs.add(okok);
					//System.out.println(okok.getQualis());
				}
//				
				//auxPubs.addAll(ordenadoPorAno);
			}
			
			//this.ordenarPorAno(maiorAno, maiorAno, auxPubs, pubPorQualis);
			//hashOrdenada.addAll(auxPubs);
			for(Publicacao okok : auxPubs) {
				hashOrdenada.add(okok);
				//System.out.println(okok.getQualis());
			}
		}
		for(Publicacao okok : hashOrdenada) {
			System.out.println(okok.getQualis());
		}
		return hashOrdenada;
	}
	
	public void write() {
		LinkedList<Publicacao> hashOrdered = this.ordenar();
//		for(Publicacao okok : hashOrdered) {
//			System.out.println(okok.getQualis());
//		}
		//System.out.println(hashOrdenada);
		
		FileWriter fileWriter = null;
		
		try {
			fileWriter = new FileWriter(pathname);
			fileWriter.append(FILE_HEADER.toString());
			fileWriter.append(NEW_LINE_SEPARATOR.toString());
			
			for(Publicacao p : hashOrdered) {
				//System.out.println(p);
				//System.out.println(p.getVeiculo().getListaQualis());
				fileWriter.append(String.valueOf(p.getAno()));
				fileWriter.append(COMMA_DELIMITER.toString());
				fileWriter.append(p.getVeiculo().getSigla());
				fileWriter.append(COMMA_DELIMITER.toString());
				fileWriter.append(p.getVeiculo().getNome());
				fileWriter.append(COMMA_DELIMITER.toString());
				fileWriter.append(p.getQualis());
				fileWriter.append(COMMA_DELIMITER.toString());
				fileWriter.append(String.valueOf(p.getVeiculo().getFatorDeImpacto()).replace(".", ","));
				fileWriter.append(COMMA_DELIMITER.toString());
				fileWriter.append(p.getTitulo());
				fileWriter.append(COMMA_DELIMITER.toString());
				int lenghAutores = p.getAutores().size();
				int i = 0;
				for(Docente d : p.getAutores()) {
					if(i == lenghAutores - 1) {
						fileWriter.append(d.getNome());
					} else {
						fileWriter.append(d.getNome() + ",");
					}
					i++;
				}
				fileWriter.append(NEW_LINE_SEPARATOR.toString());
			}
			
		} catch (Exception e) {
			System.out.println("Erro de I/O");
			System.exit(1);
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Erro de I/O");
				System.exit(1);
			}
		
			
		}
		
		
	}
	
	private Vector<String> getSortedStringArray(Vector<String> array) {
		Collections.sort(array);
		Vector<String> newArray = new Vector<String>();
		for(String auxArray : array ) {
			if (!newArray.contains(auxArray)) {
				newArray.add(auxArray);
			}
		}
		return newArray;
	}
	

}
