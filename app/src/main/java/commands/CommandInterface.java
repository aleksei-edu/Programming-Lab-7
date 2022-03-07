package commands;

import java.io.IOException;

public interface CommandInterface {
    public String getName();
    public String getDescription();
    public void execute(String argument) throws IOException;
}
