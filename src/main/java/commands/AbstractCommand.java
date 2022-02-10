package commands;

public abstract class AbstractCommand implements CommandInterface {
    private String name;
    private String description;
    public AbstractCommand(String name, String description){
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription(){
        return description;
    }

    @Override
    public String toString(){
        return name + " – " + description;
    }

}
