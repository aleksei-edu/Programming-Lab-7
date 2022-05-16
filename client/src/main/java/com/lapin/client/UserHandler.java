package com.lapin.client;

import com.lapin.common.interaction.Request;

import java.util.Scanner;

public class UserHandler {
    private static Scanner userScanner = new Scanner(System.in);
    public static Request handle(){
        String[] userCommand = (userScanner.nextLine().toLowerCase().trim() + " ").split(" ", 2);
        return new Request(userCommand[0], userCommand[1].trim());
    }
}
