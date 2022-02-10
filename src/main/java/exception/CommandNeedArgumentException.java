package exception;

public class CommandNeedArgumentException extends RuntimeException{
    private static final String message = "Эта команде необходим аргумент.";
    public CommandNeedArgumentException() {
        super(message);
    }
}
