package commands;

import java.io.IOException;

public interface Command {
    /**
     * Запускает выполнение команды
     *
     * @param argument аргумент команды (если есть, иначе null)
     */
    public void execute(String argument);
    public String toString();
    public String getName();
}
