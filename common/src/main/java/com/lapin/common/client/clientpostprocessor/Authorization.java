package com.lapin.common.client.clientpostprocessor;

import com.lapin.common.client.Client;
import com.lapin.common.controllers.CommandManager;
import com.lapin.common.controllers.CommandManagerImpl;
import com.lapin.common.controllers.ConsoleManager;
import com.lapin.common.data.User;
import com.lapin.common.utility.Pair;
import com.lapin.di.annotation.Inject;
import com.lapin.di.context.ApplicationContext;
import com.lapin.network.StatusCodes;
import com.lapin.network.log.NetworkLogger;

public class Authorization implements ClientPostProcessor{
    private User user;
    private ConsoleManager consoleManager = ApplicationContext.getInstance().getBean(ConsoleManager.class);
    private CommandManager commandManager = ApplicationContext.getInstance().getBean(CommandManager.class);
    private NetworkLogger logger = ApplicationContext.getInstance().getBean(NetworkLogger.class);
    @Override
    public void process(Client client) {
        while (true) {
            System.out.print(
                    "#------------------------------------------------------------------------------\n" +
                    "# AUTHORIZATION\n" +
                    "#------------------------------------------------------------------------------\n" +
                    "1.\tLog in\n" +
                    "2.\tSign up\n");
            try {
                Integer userPrint = Integer.parseInt(consoleManager.getUserPrint());
                if (userPrint == 1 || userPrint == 2) {
                    while (true) {
                        if (userPrint == 1) {
                            System.out.print(
                                    "#------------------------------------------------------------------------------\n" +
                                            "# LOG IN\n" +
                                            "#------------------------------------------------------------------------------\n");
                            loginAndPassword();
                            Pair res = commandManager.handle("checkUser", "", user);
                            if (((StatusCodes) res.getFirst())
                                    .equals(StatusCodes.OK)) {
                                if (res.getSecond() instanceof Pair) {
                                    user.setId((Long) ((Pair<?, ?>) res.getSecond()).getSecond());
                                    client.setUser(user);
                                    System.out.println(((Pair<?, ?>) res.getSecond()).getFirst());
                                    return;
                                }
                            }
                        } else {
                            System.out.print(
                                    "#------------------------------------------------------------------------------\n" +
                                            "# SING UP\n" +
                                            "#------------------------------------------------------------------------------\n");
                            loginAndPassword();
                            if (checkPassword()) {
                                Pair res = commandManager.handle("addUser", "", user);
                                if (((StatusCodes) res.getFirst())
                                        .equals(StatusCodes.OK)) {
                                    if (res.getSecond() instanceof Pair) {
                                        user.setId((Long) ((Pair<?, ?>) res.getSecond()).getSecond());
                                        System.out.println(((Pair<?, ?>) res.getSecond()).getFirst());
                                        client.setUser(user);
                                        return;
                                    }
                                }
                            } else continue;
                        }
                    }
                }
            }
            catch (NumberFormatException e){
                logger.error("Unknown input. Try again");
                continue;
            }
            logger.error("Unknown input. Try again");
        }
    }
    private void loginAndPassword(){
        System.out.print("Login: ");
        String login = consoleManager.getUserPrint();
        System.out.print("Password: ");
        String password = consoleManager.getUserPrint();
        user = new User(login,password);
    }
    private boolean checkPassword(){
        System.out.print("Check password: ");
        String password2 = consoleManager.getUserPrint();
        if(!password2.equals(user.getPassword())){
            System.err.print("Passwords don't match. Try again\n");
            return false;
        }
        else return true;
    }
}