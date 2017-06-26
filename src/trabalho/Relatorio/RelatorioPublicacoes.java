package trabalho.Relatorio;
import trabalho.*;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Vector;
import java.io.FileWriter;
import java.io.IOException;


public class RelatorioPublicacoes {
	private HashSet<Publicacao> publicacoes = new HashSet<Publicacao>();
	private String pathname;
	//private HashSet<Publicacao> publicacoesOrdenadas = new HashSet<Publicacao>();
	private String[] categoriasQualis = {"A1","A2","B1","B2","B3","B4","B5","C"};
	
	// Writer
	private static final String COMMA_DELIMITER = ";";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String FILE_HEADER = "Ano;Sigla Veículo;Veículo;Qualis;Fator de Impacto;Título;Docentes";

	
	public RelatorioPublicacoes(String pathname, HashSet<Publicacao> publicacoes) {
		this.publicacoes = publicacoes;
		this.pathname = pathname;
	}
	
	private HashSet<Publicacao> ordenarPorQualis(String qualis) {
		HashSet<Publicacao> auxPub = new HashSet<Publicacao>();
		for(Publicacao auxPublicacao: publicacoes) {
			HashSet<Qualis> qualisPossiveis = auxPublicacao.getVeiculo().getListaQualis();
			Qualis qualisPublicacao = null;
			for(Qualis auxQualis : qualisPossiveis) {
				if(auxQualis.getAno() == auxPublicacao.getAno()) {
					qualisPublicacao = auxQualis;
					auxPublicacao.setQualis(qualisPublicacao);
				}
			}
			if(qualisPublicacao.getQualis() == qualis) {
				auxPub.add(auxPublicacao);
			}
		}
		return auxPub;
	}
	
	private int getMaiorAno(HashSet<Publicacao> auxPubs) {
		int maiorAno = auxPubs.iterator().next().getAno();
		for(Publicacao auxPublicacao: auxPubs) {
			if(auxPublicacao.getAno() >= maiorAno) {
				maiorAno = auxPublicacao.getAno();
			}
		}
		return maiorAno;
	}
	
	private void ordenarPorAno(int maiorAno, int ano, HashSet<Publicacao> auxPubs, HashSet<Publicacao> pubPorQualis) {
		HashSet<Publicacao> auxSiglas = new HashSet<Publicacao>();
		
		if(maiorAno - ano > 10) {
			for(Publicacao auxPub: pubPorQualis) {
				if(auxPub.getAno() == ano) {
					auxSiglas.add(auxPub);
				}
			}
			//ordenando por sigla
			auxSiglas = this.ordenarPorSigla(auxSiglas);
			auxPubs.addAll(auxSiglas);
			this.ordenarPorAno(maiorAno, ano - 1, auxPubs, pubPorQualis);
		}
	}
	
	
	private HashSet<Publicacao> ordenarPorSigla(HashSet<Publicacao> auxPubs) {
		//HashSet<Publicacao> auxSiglas = new HashSet<Publicacao>();
		HashSet<Publicacao> returnedSiglaAndTitulo = new HashSet<Publicacao>();
		Vector<String> auxSiglasString = new Vector<String>();
		for(Publicacao auxPub : auxPubs) {
			auxSiglasString.add(auxPub.getVeiculo().getSigla());
		}
		auxSiglasString = this.getSortedStringArray(auxSiglasString);
		
		for(String sigla : auxSiglasString) {
			HashSet<Publicacao> vetorSiglas = this.ordenaPorSigla(auxPubs, sigla);
			HashSet<Publicacao> vetorTituloOrdenado = new HashSet<Publicacao>();
			Vector<String> titulos = new Vector<String>();
			for(Publicacao auxPublicacaoTitulo : vetorSiglas) {
				titulos.add(auxPublicacaoTitulo.getTitulo());
			}
			titulos = this.getSortedStringArray(titulos);
			for(String titulo : titulos) {
				for(Publicacao auxPub : vetorSiglas) {
					if(titulo.equals(auxPub.getTitulo())) {
						vetorTituloOrdenado.add(auxPub);
					}
				}
			}
			returnedSiglaAndTitulo.addAll(vetorTituloOrdenado);
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
	
	private HashSet<Publicacao> ordenaPorSigla(HashSet<Publicacao> pubAno, String sigla) {
		HashSet<Publicacao> auxPubPorSigla = new HashSet<Publicacao>();
		for(Publicacao auxPub: pubAno) {
			if(auxPub.getVeiculo().getSigla().equals(sigla)) {
				auxPubPorSigla.add(auxPub);
			}
		}
		
		return auxPubPorSigla;
	}
	
//	private HashSet<Publicacao> ordenarPorTitulo(HashSet<Publicacao> auxPubs) {
//		Vector<String> auxTitulos = new Vector<String>();
//		HashSet<Publicacao> auxTitulosPub = new HashSet<Publicacao>();
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
	
	private HashSet<Publicacao> ordenar() {
		HashSet<Publicacao> hashOrdenada = new HashSet<Publicacao>();
		for(String categoriaQualis : categoriasQualis) {
			// ordenando por qualis
			
			HashSet<Publicacao> pubPorQualis = this.ordenarPorQualis(categoriaQualis);
			// ordenando por ano
			int maiorAno = this.getMaiorAno(pubPorQualis);
			HashSet<Publicacao> auxPubs = new HashSet<Publicacao>();
			this.ordenarPorAno(maiorAno, maiorAno, auxPubs, pubPorQualis);
			hashOrdenada.addAll(auxPubs);
		}
		
		return hashOrdenada;
	}
	
	public void write() {
		HashSet<Publicacao> hashOrdenada = this.ordenar();
		
		FileWriter fileWriter = null;
		
		try {
			fileWriter = new FileWriter(pathname);
			fileWriter.append(FILE_HEADER.toString());
			fileWriter.append(NEW_LINE_SEPARATOR.toString());
			
			for(Publicacao p : hashOrdenada) {
				fileWriter.append(String.valueOf(p.getAno()));
				fileWriter.append(COMMA_DELIMITER.toString());
				fileWriter.append(p.getVeiculo().getSigla());
				fileWriter.append(COMMA_DELIMITER.toString());
				fileWriter.append(p.getVeiculo().getNome());
				fileWriter.append(COMMA_DELIMITER.toString());
				fileWriter.append(p.getQualis().getQualis());
				fileWriter.append(COMMA_DELIMITER.toString());
				fileWriter.append(String.valueOf(p.getVeiculo().getFatorDeImpacto()).replace(".", ","));
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
		return array;
	}
	

}
