package trabalho.Arquivo;

import java.io.File;

import trabalho.Exceptions.ExceptionFile;

public class ArquivoRegras extends File {
	
	public ArquivoRegras(String pathname) throws ExceptionFile {
		super(pathname);
		if(!this.exists()) throw new ExceptionFile();
	}

}
