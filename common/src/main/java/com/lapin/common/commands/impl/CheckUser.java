package com.lapin.common.commands.impl;

import com.lapin.common.commands.AbstractCommand;
import com.lapin.common.controllers.Controllers;
import com.lapin.common.controllers.DBHandler;
import com.lapin.common.data.User;
import com.lapin.common.utility.OutManager;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.network.AccessType;
import com.lapin.network.StatusCodes;

import java.io.Serializable;
@ClassMeta(
        name = "checkUser",
        description = "проверить пользователя в bd"
)
public class CheckUser extends AbstractCommand {
    DBHandler dbHandler = Controllers.getDbHandler();
    {
        super.hide = true;
        super.accessType = AccessType.ALL;
    }
    @Override
    public void execute(String arg, Serializable argObj) {
        long res = dbHandler.checkUser((User) argObj);
        if(res == -1){
            OutManager.push(StatusCodes.ERROR,"Failed to login. Wait or contact the support service");
        }
        else if(res == 0){
            OutManager.push(StatusCodes.ERROR,"Invalid login or password. Try again.");
        }
        else OutManager.push(StatusCodes.OK, "Successful login.");
    }
}
