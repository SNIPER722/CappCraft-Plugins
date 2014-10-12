package org.cappmc.sniper722.cappLogin;

/**
 * Created by SNIPER722 on 11/10/2014.
 */
public class AuthResult {
    private boolean AllowJoin = false;
    private String Error = null;

    public boolean getAllowJoin(){
        return AllowJoin;
    }

    public String getError(){
        return Error;
    }

    @Override
    public String toString(){
        return "{\"AllowJoin\":"+AllowJoin+",\"Error\":\""+Error+"\"}";
    }
}
