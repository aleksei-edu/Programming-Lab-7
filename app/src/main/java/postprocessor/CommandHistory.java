package postprocessor;

import commands.AbstractCommand;
import commands.Command;
import commands.impl.Help;
import utility.HistoryStack;

public class CommandHistory implements BeanPostProcessor{
    @Override
    public void process(Object bean) {
        if(bean.getClass().getSuperclass().equals(AbstractCommand.class)){
            HistoryStack.getInstance().push(bean);
        }
    }
}
