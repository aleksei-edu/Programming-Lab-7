package com.lapin.server.BeforeRequestExecute;

import com.lapin.common.data.User;
import com.lapin.common.network.objimp.RequestCommand;
import com.lapin.common.utility.OutManager;
import com.lapin.network.StatusCodes;
import com.lapin.server.App;

public class CheckUser implements BeforeRequestExecute{
    @Override
    public boolean process(RequestCommand rc) {
        if(rc.getCmd().equals("addUser")){
            return true;
        }
        User user = rc.getUser();
        if(App.getDbHandler().checkUser(user) == -1){
            OutManager.push(StatusCodes.ERROR,"Invalid username or password");
            return false;
        }
        return true;
    }
}
