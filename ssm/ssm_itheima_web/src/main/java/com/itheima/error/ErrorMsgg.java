package com.itheima.error;

public class ErrorMsgg extends Exception {
    private String  errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ErrorMsgg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
