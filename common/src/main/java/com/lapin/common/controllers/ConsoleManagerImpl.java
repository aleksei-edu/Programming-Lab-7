package com.lapin.common.controllers;

import java.util.Scanner;

/**
 * Класс управляет пользовательским вводом.
 */
public class ConsoleManagerImpl implements ConsoleManager{
    /**
     * Пользовательский ввод с консоли (по умолчанию).
     */
    private CommandManagerImpl commandManagerImpl;
    private static Scanner userScanner = new Scanner(System.in);

    public ConsoleManagerImpl(CommandManagerImpl commandManagerImpl){
        this.commandManagerImpl = commandManagerImpl;
    }

    /**
     * Считывает из пользовательского ввода введенную команду.
     */
    public void interactiveMode() {
        String[] userCommand = (userScanner.nextLine().toLowerCase().trim() + " ").split(" ", 2);
        commandManagerImpl.execute(userCommand[0], userCommand[1].trim(), null);
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
