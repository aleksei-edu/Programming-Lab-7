package com.lapin.common.data;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class User implements Serializable {
    @Getter
    @Setter
    private long id;
    @Getter
    @Setter
    private String login;
    @Getter
    @Setter
    private String password;

    public User(String login, String password){
        this.login = login;
        this.password = password;
    }
}
