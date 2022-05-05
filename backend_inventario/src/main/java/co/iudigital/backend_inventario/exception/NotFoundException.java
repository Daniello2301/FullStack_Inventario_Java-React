package co.iudigital.backend_inventario.exception;

public class NotFoundException extends RestException{//404
    private static final long serialVersionUID = 1L;

    public NotFoundException() {
        super();
    }

    public NotFoundException(ErrorDto errorDto) {
        super(errorDto);
    }
}
