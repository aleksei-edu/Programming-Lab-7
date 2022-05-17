package com.lapin.common.commands;

import com.lapin.common.network.objimp.RequestBodyKeys;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Интерфейс, который описывает основные методы всех команд
 */

public interface Command {
    /**
     * Запускает выполнение команды
     *
     * @param args аргументы команды (если есть, иначе null)
     */
    public void execute(HashMap<RequestBodyKeys, Serializable> args);

    public String toString();

    public String getName();

    public AccessType getAccessType();
}
