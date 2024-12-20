package com.fast.qaManager.service.core.util;





import com.fast.qaManager.service.core.entity.vo.Response;
import com.fast.qaManager.service.core.enums.ErrorType;
import com.fast.qaManager.service.core.exceptions.AssertException;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author jianghuadong
 * @Date 2022/1/26 14:19
 */
public class ResponseUtil {
    public static void structure(Response response, ErrorType errorType, Map<String,Object> result){
        response.setCode(errorType.getCode());
        response.setMsg(errorType.getMsg());
        if(null == result){
            response.setData(new HashMap<String,Object>());
            return;
        }
        response.setData(result);
    }

    public static Response success(Map<String,Object> result){
        Response response = new Response();
        response.setCode(ErrorType.SUCCESS.getCode());
        response.setMsg(ErrorType.SUCCESS.getMsg());
        response.setData(result);
        return response;
    }

    /**
     * 格式：
     * success("a",obj,"b",obj2)
     * @param datas
     * @return
     */
    public static Response success(Object... datas){
        Response response = new Response();
        Map<String,Object> result = new HashMap<>();
        String tempKey = "";
        for(int index = 0 ; index < datas.length ; index++){
            if(index % 2 == 0){
                tempKey = datas[index].toString();
                result.put(tempKey,"");
            }else{
                result.put(tempKey,datas[index]);
            }
        }
        response.setCode(ErrorType.SUCCESS.getCode());
        response.setMsg(ErrorType.SUCCESS.getMsg());
        response.setData(result);

        return response;
    }


    public static Response systemError(){
        Response response = new Response();
        response.setCode(ErrorType.SERVER_ERROR.getCode());
        response.setMsg(ErrorType.SERVER_ERROR.getMsg());
        return response;
    }

    public static Response assertError(AssertException assertException){
        Response response = new Response();
        response.setCode(assertException.getErrorType().getCode());
        response.setMsg(assertException.getErrorType().getMsg());
        return response;
    }

    public static Response assertErrorByMsg(AssertException assertException, String msg){
        Response response = new Response();
        response.setCode(assertException.getErrorType().getCode());
        response.setMsg(msg);
        return response;
    }
    public static Response errorByMsg(ErrorType errorType, String msg){
        Response response = new Response();
        response.setCode(errorType.getCode());
        response.setMsg(msg);
        return response;
    }

    public static Response error(ErrorType errorType){
        Response response = new Response();
        response.setCode(errorType.getCode());
        response.setMsg(errorType.getMsg());
        return response;
    }

}
