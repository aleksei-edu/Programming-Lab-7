package com.lapin.server.BeforeRequestExecute;

import com.lapin.common.network.objimp.RequestCommand;

public interface BeforeRequestExecute {
    boolean process(RequestCommand rc);
}
