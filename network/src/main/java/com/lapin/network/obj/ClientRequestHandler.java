package com.lapin.network.obj;

import com.lapin.network.config.NetworkConfigurator;

public interface ClientRequestHandler {
    public NetObj handle(String str, NetworkConfigurator config);
}
