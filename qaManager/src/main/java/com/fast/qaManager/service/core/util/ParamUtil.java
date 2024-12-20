package com.fast.qaManager.service.core.util;

import java.util.HashMap;
import java.util.Map;

public class ParamUtil {
    public static Map<String,Object> format(String urlParam){
        String[] paramArr = urlParam.split("&");
        Map<String,Object> map = new HashMap<String,Object>();
        for (String param : paramArr) {
            String[] keyValue = param.split("=");
            map.put(keyValue[0], keyValue[1]);
        }
        return map;
    }
}
