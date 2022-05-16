package com.lapin.server.postprocess;


import com.lapin.di.postprocessor.BeanPostProcessor;
import com.lapin.common.commands.AbstractCommand;
import com.lapin.server.utility.HistoryStack;


public class CommandHistory implements BeanPostProcessor {
    @Override
    public void process(Object bean) {
        if (bean.getClass().getSuperclass().equals(AbstractCommand.class)) {
            HistoryStack.getInstance().push(bean);
        }
    }
}
