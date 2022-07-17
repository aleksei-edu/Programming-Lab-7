package com.lapin.common.controllers;

import java.util.Scanner;

public interface ConsoleManager {
    public void interactiveMode();
    public String getUserPrint();
    public void setUserScanner(Scanner userScanner);
}
