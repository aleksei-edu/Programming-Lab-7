package com.lapin.server;

import com.lapin.common.impl.Help;
import com.lapin.common.network.objimp.RequestCommand;
import com.lapin.common.network.objimp.ResponseCommand;
import com.lapin.common.utility.OutManager;
import com.lapin.common.utility.Pair;
import com.lapin.di.context.ApplicationContext;
import com.lapin.di.factory.BeanFactory;
import com.lapin.network.obj.ResponseBodyKeys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServerRequestHandlerTest {
    @BeforeEach
    void setUp() {
        BeanFactory beanFactory = new BeanFactory(ApplicationContext.getInstance());
        ApplicationContext.getInstance().setBeanFactory(beanFactory);
    }

    @Test
    void handleHelp() {
        ServerRequestHandler srh = new ServerRequestHandler();
        RequestCommand requestCommand = new RequestCommand("help","",null);
        ResponseCommand response = (ResponseCommand) srh.handle(requestCommand);
        Pair res = new Pair(response.getBody().get(ResponseBodyKeys.STATUS_CODE),response.getBody().get(ResponseBodyKeys.ANSWER));
        Help help = new Help();
        help.execute("", null);
        assertEquals(OutManager.pop(), res);
    }
}