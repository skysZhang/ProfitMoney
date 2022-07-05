package com.bjpn.money.utils;

import java.util.HashMap;
import java.util.Map;

public class Result {

    public static Map<String, Object> success() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("message", "");
        map.put("success", true);
        return map;
    }

    public static Map<String, Object> success(String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("message", message);
        map.put("success", true);
        return map;
    }

    public static Map<String, Object> error() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("message", "");
        map.put("success", false);
        return map;
    }

    public static Map<String, Object> error(String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("message", message);
        map.put("success", false);
        return map;
    }

}
