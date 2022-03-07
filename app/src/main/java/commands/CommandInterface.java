package commands;

public interface CommandInterface {
    public String getName();
    public String getDescription();
    public void execute(String argument);
}
