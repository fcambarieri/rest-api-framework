/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.project.api.core;

import com.ml.project.api.core.controllers.AbstractController;
import com.ml.project.api.core.controllers.ControllerFactory;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.grizzly.http.Method;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;
/**
 *
 * @author fcambarieri
 */
public class DispacherHandlers extends HttpHandler {
    
    private URLMapping mapping;
    private ControllerFactory factory;

    public DispacherHandlers(URLMapping mapping,ControllerFactory factory) {
        this.mapping = mapping;
        this.factory = factory;
    }
    
    @Override
    public void service(final Request request, final Response response)
            throws Exception {
        //System.out.println(String.format("[%s] - %s ",request.getMethod().toString(), request.getRequestURI()));
        //response.setContentType("text/plain");
        //response.getWriter().write("Hello world!");
     
        String uri = request.getRequestURI();
        URLEntry urlEntry = mapping.getUriHandler(uri);
        if (urlEntry == null) {
            throw new RuntimeException("Not handler found for " + uri);
        }
        
        Method method = request.getMethod();
        Map params = request.getParameterMap();
        
        if (!urlEntry.getActions().containsKey(method)) {
            throw new RuntimeException(String.format("Not handler found method:%s and uri:%s", method, uri));
        }
        
        String controller = urlEntry.getController();
        String action = urlEntry.getActions().get(method);
        
        AbstractController ac = factory.createController(controller, params, response, request);
        
        invoke(ac, action);
        
  
    }
    
    private void invoke(AbstractController controller, String action) {
        
        try {
            java.lang.reflect.Method method = controller.getClass().getMethod(action,  new Class<?>[0]);
            method.invoke(controller);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(DispacherHandlers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
