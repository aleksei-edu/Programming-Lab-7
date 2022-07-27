package com.lapin.common.commands.impl;

import com.lapin.common.commands.AbstractCommand;
import com.lapin.common.controllers.Controllers;
import com.lapin.common.data.User;
import com.lapin.common.controllers.DBHandler;
import com.lapin.common.network.objimp.RequestCommand;
import com.lapin.common.utility.OutResultStack;
import com.lapin.common.utility.Pair;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.network.AccessType;
import com.lapin.network.StatusCodes;

import java.io.Serializable;
@ClassMeta(
        name = "addUser",
        description = "добавить пользователя в bd"
)
public class AddUser extends AbstractCommand {
    DBHandler dbHandler = Controllers.getDbHandler();
    {
        super.hide = true;
        super.accessType = AccessType.ALL;
        super.NeedObj = true;
    }
    @Override
    public void execute(RequestCommand rc) {
        long res = dbHandler.addUser((User) rc.argObj());
        if(res == -1){
            OutResultStack.push(StatusCodes.ERROR,"Failed to create user");
        }
        else if(res == 0){
            OutResultStack.push(StatusCodes.ERROR,"This login already exists. Try again.");
        }
        else OutResultStack.push(StatusCodes.OK, new Pair("User successfully created", res));
    }
}
