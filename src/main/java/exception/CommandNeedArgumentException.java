package exception;

public class CommandNeedArgumentException extends RuntimeException{
    public CommandNeedArgumentException() {
        super("Эта команде необходим аргумент.");
    }
    public CommandNeedArgumentException(String message) {
        super(message);
    }
}
