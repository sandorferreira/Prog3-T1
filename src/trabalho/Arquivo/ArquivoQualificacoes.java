package trabalho.Arquivo;
import trabalho.Exceptions.*;

import java.io.File;

public class ArquivoQualificacoes extends File {
	
	public ArquivoQualificacoes(String pathname) throws ExceptionFile {
		super(pathname);
		if(!this.exists()) throw new ExceptionFile();
	}
	
	

}
