package exception;

public class CommandNotAcceptArgumentsException extends RuntimeException{
    private static final String message = "Эта команда не принимает аргументов.";
    public CommandNotAcceptArgumentsException() {
        super(message);
    }
}
