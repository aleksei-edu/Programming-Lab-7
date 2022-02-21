package exception;

public class IdOverflowException extends Exception{
    private static final String message = "Свободного id не найдено";
    public IdOverflowException() {
        super(message);
    }
}
