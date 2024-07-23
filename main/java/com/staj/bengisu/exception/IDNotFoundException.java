package com.staj.bengisu.exception;

public class IDNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    String msg;

    public IDNotFoundException(String msg) {
        this.msg = msg;

    }
    public String getMsg(){
        return msg;
    }
    public void setMsg(String msg){
        this.msg=msg;
    }
}
