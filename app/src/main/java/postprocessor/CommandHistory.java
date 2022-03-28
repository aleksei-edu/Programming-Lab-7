package postprocessor;

import commands.AbstractCommand;
import utility.HistoryStack;

public class CommandHistory implements BeanPostProcessor {
    @Override
    public void process(Object bean) {
        if (bean.getClass().getSuperclass().equals(AbstractCommand.class)) {
            HistoryStack.getInstance().push(bean);
        }
    }
}
