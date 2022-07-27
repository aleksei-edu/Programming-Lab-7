package com.lapin.common.commands;

import com.lapin.common.network.objimp.RequestCommand;
import com.lapin.network.AccessType;

import java.io.Serializable;

/**
 * Интерфейс, который описывает основные методы всех команд
 */

public interface Command {
    /**
     * Запускает выполнение команды
     */
    public void execute(RequestCommand rc);

    public String toString();
    public boolean getNeedObj();
    public boolean getExecutingLocal();

    public String getName();

    public AccessType getAccessType();
}
