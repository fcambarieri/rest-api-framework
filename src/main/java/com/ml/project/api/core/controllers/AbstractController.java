/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.project.api.core.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import jdk.nashorn.internal.parser.JSONParser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;

/**
 *
 * @author fcambarieri
 */
public class AbstractController {
    
    protected Map<String, Object> params;
    protected Response response;
    protected Request request;
    protected EntityManager entityManager;
    
    public void response(int status, Map body) {
        JSONObject json = new JSONObject();
        json.putAll(body);
        writeResponse(status, json.toString());
    }
    
    public void response(int status, List body) {
        JSONArray json = new JSONArray();
        json.addAll(body);
        writeResponse(status, json.toString());
    }
    
    public void response(int status, String body) {
        writeResponse(status, body);
    }
    
    public void writeResponse(int status, String body) {
        try {
            response.setStatus(status);
            response.setContentType("application/json");
            response.setContentLength(body.length());
            response.getWriter().write(body);
        } catch (IOException ex) {
            Logger.getLogger(AbstractController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void withTransaction() {
        
    }
    
    
}
