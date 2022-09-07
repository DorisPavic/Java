package hr.java.production.exception;

public class InvalidNameException extends RuntimeException{
    public InvalidNameException() {
        super("Dogodila se pogreška u radu programa!");
    }

    public InvalidNameException(String message) {
        super(message);
    }

    public InvalidNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidNameException(Throwable cause) {
        super(cause);
    }
}
