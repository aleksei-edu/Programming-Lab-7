package exception;

public class CommandNotFoundException extends RuntimeException{
    private static final String message = "Команда не найдена.";
    public CommandNotFoundException() {
        super(message);
    }
}
