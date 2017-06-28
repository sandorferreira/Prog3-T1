package trabalho;

import java.util.HashMap;
import java.util.Map;

public class Argumentos {
	private String[] args;
	private String[] indicators = new String[6];
	private static String writeOnly= "--write-only";
	private static String readOnly = "--read-only";
	
	Map<String, String> argumentos = new HashMap<String, String>();

	public Argumentos(String[] args) {
		//String[] argumentos = args.split(" -");
		this.args = args;
		if (!this.isWriteOnly()) {
			this.setIndicators();
			this.setArguments();
		}
	}
	
	private void setIndicators() {
		int j = 0; int k = 1;
		for(int i = 0; i <indicators.length;i++) {
			if(!this.isReadOnly()) {
				indicators[i] = args[j];
				j = j + 2;
			} else {
				indicators[i] = args[k];
				k = k + 2;
			}
		}
	}
	
	private void setArguments() {
		int j = 1; int k = 2;
		for(int i = 0; i<(indicators.length-1);i++) {
			String indicator = indicators[i];
			if(this.isReadOnly()) {
				argumentos.put(indicator, args[k]);
				k = k + 2;
			} else {
				argumentos.put(indicator, args[j]);
				j = j + 2;
			}
		}
	}
	
	public boolean isWriteOnly() {
		if (args[0].equals(writeOnly)) {
			return true;
		}
		return false;
	}
	
	public boolean isReadOnly() {
		if(args[0].equals(readOnly)) {
			return true;
		}
		return false;
	}
	
	public String getPathnameArquivoDocente() {
		return argumentos.get("-d");
	}
	
	public String getPathnameArquivoVeiculo() {
		return argumentos.get("-v");
	}
	
	public String getPathnameArquivoPublicacoes() {
		return argumentos.get("-p");
	}
	
	public String getPathnameArquivoRegras() {
		return argumentos.get("-r");
	}
	
	public String getPathnameArquivoQualis() {
		return argumentos.get("-q");
	}
	
	public String getNomeArquivo(String c) {
		return argumentos.get(c);
	}
	
}
