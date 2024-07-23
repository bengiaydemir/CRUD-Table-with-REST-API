package com.staj.bengisu.exception;

public class MandatoryFieldMissingException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    String msg;

    public MandatoryFieldMissingException(String msg){
        this.msg=msg;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }


}
