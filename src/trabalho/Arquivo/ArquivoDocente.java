package trabalho.Arquivo;
import trabalho.*;
import trabalho.Exceptions.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;

public class ArquivoDocente extends File {
	private HashSet<Docente> docentes;
	private String pathname;

	public ArquivoDocente(String pathname) throws ExceptionFile {
		super(pathname);
		this.pathname = pathname;
		if (!this.exists()) throw new ExceptionFile();

	}
	
	public void loadDataToMemory() {
		Scanner sc;
		try {
			sc = new Scanner(this);
			String linha = sc.nextLine();
			while (sc.hasNextLine()) {
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	            linha = sc.nextLine();
	            String[] dados = linha.split(";");  //Divide cada linha usando o ';' como divisor
	            boolean coordenador = false;
	            long codigo = Long.parseLong(dados[0]);     //Primeiro dado, código do docente
	            String nome = dados[1];                     //Segundo dado, nome do docente
	            
	            //Confirma se possui um 5º dado, e se é um 'X', ou seja, coordenador
	            if ((dados.length > 4) && ("X".equals(dados[4]))) {
	                coordenador = true;
	            }
	            try {
	            Date nascimento = format.parse(dados[2]);   //Terceiro dado, nascimento
	            Date ingresso = format.parse(dados[3]);     //Quarto dado, ingresso
	            Docente novoDocente = new Docente(codigo, nome, nascimento, ingresso, coordenador);
	            	try {
	            		this.addDocente(novoDocente);
	            	} catch (Exception e) {
	            		e.toString();
	            	}
	            	
	            } catch (ParseException e) {
	            	e.toString();
	            }
	        }
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public HashSet<Docente> getDocentes() {
		this.loadDataToMemory();
		return docentes;
	}
	
	public void addDocente(Docente docente) throws Exception {
		boolean isDocenteContained = false;
		for(Docente d: docentes) {
			if(d.getCodigo() == docente.getCodigo()) {
				isDocenteContained = true;
			}
		}
		if(!isDocenteContained) {
			docentes.add(docente);
		} else {
			throw new Exception();
		}
	}
	
}
