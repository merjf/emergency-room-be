package com.portaleps.dto;

public class ResponseDto {

    private boolean result; // true or false
    private int code; // 200, 403, 404
    private String message; // "Something went wrong"

    public ResponseDto(){}

    public ResponseDto(boolean result, int code, String message){
        this.result = result;
        this.code = code;
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
