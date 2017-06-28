package trabalho.Exceptions;

public class CodigoDocenteNotFoundException extends Exception {

    public CodigoDocenteNotFoundException() {
        super("Exception Sigla Veiculo");
    }

    public CodigoDocenteNotFoundException(String pTitulo, String codigo) {
        super("Sigla de veículo não definida usada na publicação \""
                + pTitulo + "\": " + codigo);
    }
}
