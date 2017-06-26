package trabalho.Relatorio;
import trabalho.*;
import java.io.File;
import java.util.HashSet;


public class RelatorioPublicacoes extends File {
	private HashSet<Publicacao> publicacoes;
	private HashSet<Publicacao> publicacoesOrdenadas = new HashSet<Publicacao>();
	private String[] categoriasQualis = {"A1","A2","B1","B2","B3","B4","B5","C"};
	
	public RelatorioPublicacoes(String pathname, HashSet<Publicacao> publicacoes) {
		super(pathname);
		this.publicacoes = publicacoes;
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
		if(maiorAno - ano > 10) {
			for(Publicacao auxPub: pubPorQualis) {
				if(auxPub.getAno() == ano) {
					auxPubs.add(auxPub);
				}
			}
			this.ordenarPorAno(maiorAno, ano - 1, auxPubs, pubPorQualis);
		}
	}
	
	private void ordenar() {
		for(String categoriaQualis : categoriasQualis) {
			// ordenando por qualis
			HashSet<Publicacao> pubPorQualis = this.ordenarPorQualis(categoriaQualis);
			// ordenando por ano
			int maiorAno = this.getMaiorAno(pubPorQualis);
			HashSet<Publicacao> auxPubs = new HashSet<Publicacao>();
			this.ordenarPorAno(maiorAno, maiorAno, auxPubs, pubPorQualis);
			//ordenando por sigla
			
			
			//ordenando por titulo
			
		}
	}
	

}
