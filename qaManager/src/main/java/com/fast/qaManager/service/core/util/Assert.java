package com.fast.qaManager.service.core.util;


import com.fast.qaManager.service.core.enums.ErrorType;
import com.fast.qaManager.service.core.exceptions.AssertException;

/**
 * @Description
 * @Author jianghuadong
 * @Date 2022/1/26 14:15
 */
public class Assert {
    public static void asserts(boolean item, ErrorType errorType) throws AssertException {
        if(!item){
            throw new AssertException(errorType.getMsg()).setErrorType(errorType);
        }
    }
}
