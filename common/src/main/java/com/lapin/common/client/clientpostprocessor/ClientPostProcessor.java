package com.lapin.common.client.clientpostprocessor;

import com.lapin.common.client.Client;

public interface ClientPostProcessor {
    void process(Client client);
}
