package com.luban.common.exception;


import com.luban.common.api.IErrorCode;
import lombok.Data;

@Data
public class GateWayException extends RuntimeException{

    private long code;

    private String message;

    public GateWayException(IErrorCode iErrorCode) {
        this.code = iErrorCode.getCode();
        this.message = iErrorCode.getMessage();
    }
}
