package com.fast.qaManager.service.core.exceptions;

import com.fast.qaManager.service.core.enums.ErrorType;

/**
 * @Description
 * @Author jianghuadong
 * @Date 2022/1/26 14:12
 */
public class AssertException extends Exception{
    public AssertException(String errmsg){
        super(errmsg);
    }
    private ErrorType errorType;

    public AssertException setErrorType(ErrorType errorType){
        this.errorType = errorType;
        return this;
    }

    public ErrorType getErrorType(){
        return errorType;
    }
}
