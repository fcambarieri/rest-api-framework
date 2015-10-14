/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.project.api.core;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author fcambarieri
 */
public class Holder {
    
    private static final Holder instance = new Holder();
    
    Map<String, Object> config = new HashMap<>();
    
    private Holder () {
        
    }
    
    public static Map getConfig() {
        return instance.config;
    }
    
    public static void setConfig(Map map) {
        instance.config.putAll(map);
    }
    
    public static Object get(String key) {
        return instance.config.get(key);
    }
    
            
            
}
