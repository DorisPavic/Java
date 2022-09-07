package hr.java.production.exception;

public class DuplicateItemException extends Exception{
    public DuplicateItemException() {
        super("Dogodila se pogreška u radu programa!");
    }

    public DuplicateItemException(String message) {
        super(message);
    }

    public DuplicateItemException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateItemException(Throwable cause) {
        super(cause);
    }

}
