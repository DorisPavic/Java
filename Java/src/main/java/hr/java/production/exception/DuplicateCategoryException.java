package hr.java.production.exception;

public class DuplicateCategoryException extends RuntimeException{
    public DuplicateCategoryException() {
        super("Dogodila se pogreška u radu programa!");
    }

    public DuplicateCategoryException(String message) {
        super(message);
    }

    public DuplicateCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateCategoryException(Throwable cause) {
        super(cause);
    }
}
