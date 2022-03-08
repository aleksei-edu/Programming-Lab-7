package commands;

import java.io.IOException;

public interface Command {
    /**
     * Имя команды, по которому пользователь, вызывает её
     *
     * @return String - имя команды
     */
    public String getName();
    public String getDescription();
    public void execute(String argument) throws IOException;
}
