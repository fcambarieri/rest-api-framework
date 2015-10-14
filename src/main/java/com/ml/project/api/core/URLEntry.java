/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.project.api.core;

import java.util.LinkedHashMap;
import java.util.Map;
import org.glassfish.grizzly.http.Method;

/**
 *
 * @author fcambarieri
 */
public class URLEntry {
    
    private String controller;
    private Map<Method, String> actions;

    public URLEntry() {
        
    }
    
    public URLEntry(String controller, Map actions) {
        this.controller = controller;
        this.actions = actions;
    }

    /**
     * @return the controller
     */
    public String getController() {
        return controller;
    }

    /**
     * @param controller the controller to set
     */
    public void setController(String controller) {
        this.controller = controller;
    }

    public Map<Method, String> getActions() {
        return actions;
    }

    public void setActions(Map<Method, String> actions) {
        this.actions = actions;
    }
    
    public URLEntry addAction(Method method, String action) {
        if (this.actions == null) {
            this.actions = new LinkedHashMap<>();
        }
        this.actions.put(method, action);
        return this;
    }

    public static URLEntry createEntry(String controller, Method m, String action){
        URLEntry entry = new URLEntry();
        entry.controller = controller;
        
        Map<Method, String> actions = new LinkedHashMap<>();
        actions.put(m, action);
        entry.actions = actions;
        return entry;
    }
    
   
    
}
