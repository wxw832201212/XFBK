package com.fast.qaManager.service.core.buffer;

import com.fast.qaManager.service.core.entity.User;

import java.util.HashMap;
import java.util.Map;

public class LocalBuffer {
    public static Map<String, User> loginUserBuffer = new HashMap<String, User>();
}
