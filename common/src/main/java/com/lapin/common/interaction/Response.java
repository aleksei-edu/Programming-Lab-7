package com.lapin.common.interaction;

import java.io.Serializable;

public class Response implements Serializable {
    private StatusCodes statusCode;
    private String Body;
    public Response(StatusCodes statusCode, String responseBody){
        this.Body = responseBody;
        this.statusCode = statusCode;
    }

    public String getBody(){
        return Body;
    }
    public StatusCodes getStatusCode(){return statusCode;}
}
