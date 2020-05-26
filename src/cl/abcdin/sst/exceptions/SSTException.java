package cl.abcdin.sst.exceptions;

public class SSTException extends Exception {

	private static final long serialVersionUID = 1L;
	
    public SSTException() {
    	super();
    }

    public SSTException(String message) {
    	super(message);
    }

    public SSTException(String message, Throwable cause) {
        super(message, cause);
    }

    public SSTException(Throwable cause) {
        super(cause);
    }

}
