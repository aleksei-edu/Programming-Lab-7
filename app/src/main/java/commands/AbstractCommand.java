package commands;

import annotation.ClassMeta;

/**
 * Абстрактный класс, от которого наследуются все команды.
 */

public abstract class AbstractCommand implements Command {
    @Override
    public String toString(){
        ClassMeta commandMeta = this.getClass().getAnnotation(ClassMeta.class);
        return commandMeta.name() + " – " + commandMeta.description();
    }

    @Override
    public String getName() {
        return this.getClass().getAnnotation(ClassMeta.class).name();
    }
}
