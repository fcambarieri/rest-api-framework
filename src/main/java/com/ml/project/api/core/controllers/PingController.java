/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.project.api.core.controllers;

import com.ml.project.api.core.services.MetricsService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author fcambarieri
 */
public class PingController extends AbstractController {
    
    @Inject MetricsService metricsService;
    
    public void ping() {
        try {
            response.setStatus(200);
            response.getWriter().write("pong");
        } catch (IOException ex) {
            Logger.getLogger(PingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
