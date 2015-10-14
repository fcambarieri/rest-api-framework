/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.project.api.core.controllers;

import com.ml.project.api.bean.AppBeanContext;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;

/**
 *
 * @author fcambarieri
 */
public class ControllerFactory {
    
    public <T extends AbstractController> T createController(String controller, Map params, Response response, Request request) {
        try {
            Class clazz = Class.forName(controller);
            return (T) createController(clazz, params, response, request);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControllerFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(String.format("Cound not create controller: %s", controller));
        }
    }
    
    public <T extends AbstractController> T createController(Class<T> controller, Map params, Response response, Request request) {
        try {
            //T instance = controller.newInstance();
            T instance = AppBeanContext.INSTANCE.getBean(controller);
            instance.params = params;
            instance.request = request;
            instance.response = response;
            return instance;
        } catch (Exception ex) {
            Logger.getLogger(ControllerFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Cound load controller: " + controller.getName(), ex);
        } 
    }
}
