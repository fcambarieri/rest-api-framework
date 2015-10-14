/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.project.api.core;

/**
 *
 * @author fcambarieri
 */
public class EnvironmentUtils {
 
    private static Environment currentEnviroment = null;
    
    static {
        String env = System.getProperty("env");
        if (env != null) {
            currentEnviroment = Environment.valueOf(env);
        }
        if (currentEnviroment == null) {
            currentEnviroment = Environment.DEVELOPMENT;
        }
    }
    
    public static Environment getCurrentEnviroment() {
        return currentEnviroment;
    }
    
}
