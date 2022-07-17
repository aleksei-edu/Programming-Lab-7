package com.lapin.common.client.clientpostprocessor;

import com.lapin.common.client.Client;
import com.lapin.common.controllers.CommandManagerImpl;
import com.lapin.common.controllers.ConsoleManager;
import com.lapin.di.context.ApplicationContext;
import com.lapin.network.log.NetworkLogger;

public class Authorization implements ClientPostProcessor{
    private String login = "";
    private String password = "";
    private ConsoleManager consoleManager = ApplicationContext.getInstance().getBean(ConsoleManager.class);
    @Override
    public void process(Client client) {
        NetworkLogger logger = ApplicationContext.getInstance().getBean(NetworkLogger.class);
        while (true) {
            System.out.print(
                    "#------------------------------------------------------------------------------\n" +
                    "# AUTHORIZATION\n" +
                    "#------------------------------------------------------------------------------\n" +
                    "1.\tLog in\n" +
                    "2.\tSign up\n");
            Integer userPrint = Integer.parseInt(consoleManager.getUserPrint());
            if (userPrint == 1 || userPrint == 2) {
                while (true) {
                    if (userPrint == 1) {
                        System.out.print(
                                "#------------------------------------------------------------------------------\n" +
                                "# LOG IN\n" +
                                "#------------------------------------------------------------------------------\n");
                        loginAndPassword();
                    } else {
                        System.out.print(
                                "#------------------------------------------------------------------------------\n" +
                                "# SING UP\n" +
                                "#------------------------------------------------------------------------------\n");
                        loginAndPassword();
                        if (checkPassword()) {
                            CommandManagerImpl.getInstance().execute("add_user","",null);
                        } else continue;
                    }
                }
            }
            logger.error("Unknown input. Try again");
        }
    }
    private void loginAndPassword(){
        System.out.print("Login: ");
        String login = consoleManager.getUserPrint();
        System.out.print("Password: ");
        String password = consoleManager.getUserPrint();
    }
    private boolean checkPassword(){
        System.out.print("Check password: ");
        String password2 = consoleManager.getUserPrint();
        if(!password2.equals(password)){
            System.err.print("Passwords don't match. Try again\n");
            return false;
        }
        else return true;
    }
}