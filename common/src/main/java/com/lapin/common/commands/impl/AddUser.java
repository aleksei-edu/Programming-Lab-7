package com.lapin.common.commands.impl;

import com.lapin.common.commands.AbstractCommand;
import com.lapin.common.data.User;
import com.lapin.common.controllers.DBHandler;
import com.lapin.common.controllers.CommandManagerImpl;
import com.lapin.common.utility.OutManager;
import com.lapin.di.annotation.ClassMeta;
import com.lapin.di.annotation.Inject;
import com.lapin.network.AccessType;
import com.lapin.network.StatusCodes;

import java.io.Serializable;
@ClassMeta(
        name = "add_user",
        description = "добавить пользователя в bd"
)
public class AddUser extends AbstractCommand {
    @Inject
    DBHandler dbHandler;
    {
        super.accessType = AccessType.ALL;
    }
    @Override
    public void execute(String arg, Serializable argObj) {
        if(dbHandler.addUser((User) argObj) == -1){
            OutManager.push(StatusCodes.ERROR,"Failed to create user");
        }
        else OutManager.push(StatusCodes.OK, "User successfully created");
    }
}
