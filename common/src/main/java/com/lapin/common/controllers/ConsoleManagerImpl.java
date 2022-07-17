package com.lapin.common.controllers;

import com.lapin.di.annotation.Inject;

import java.util.Scanner;

/**
 * Класс управляет пользовательским вводом.
 */
public class ConsoleManagerImpl implements ConsoleManager{
    /**
     * Пользовательский ввод с консоли (по умолчанию).
     */
    @Inject
    private CommandManager commandManager;
    private static Scanner userScanner = new Scanner(System.in);


    /**
     * Считывает из пользовательского ввода введенную команду.
     */
    public void interactiveMode() {
        String[] userCommand = (userScanner.nextLine().toLowerCase().trim() + " ").split(" ", 2);
        commandManager.handle(userCommand[0], userCommand[1].trim(), null);
    }


    /**
     * Возвращает пользовательский ввод очищенный от мусора.
     *
     * @return String - из пользовательского ввода
     */
    public String getUserPrint() {
        String userPrint = null;
        if (!userScanner.hasNext()) {
            setUserScanner(new Scanner(System.in));
            userPrint = userScanner.nextLine().trim().toLowerCase();
        } else {
            userPrint = userScanner.nextLine().trim().toLowerCase();
        }
        return userPrint;
    }

    /**
     * Изменить канал получение пользовательского ввода
     *
     * @param userScanner Scanner с пользовательским вводом
     */
    public void setUserScanner(Scanner userScanner) {
        ConsoleManagerImpl.userScanner = userScanner;
    }

}
