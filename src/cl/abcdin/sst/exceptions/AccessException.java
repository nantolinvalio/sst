package cl.abcdin.sst.exceptions;

public class AccessException extends Exception {

	private static final long serialVersionUID = 1L;
	
    public AccessException() {
    	super();
    }

    public AccessException(String message) {
    	super(message);
    }

    public AccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessException(Throwable cause) {
        super(cause);
    }

}
