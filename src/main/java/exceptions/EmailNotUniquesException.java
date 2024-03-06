package exceptions;

public class EmailNotUniquesException extends RuntimeException{

	public EmailNotUniquesException(String message) {
        super(message);
    }
}
