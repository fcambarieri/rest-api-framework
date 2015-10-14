/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.project.api;

import java.io.IOException;

/**
 *
 * @author fcambarieri
 */
public class Bootstrap {
    
     public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 8080;
        String sport = System.getProperty("port");
        if (sport != null){
            port = Integer.parseInt(sport);
        }
        host = System.getProperty("host");
        if (host == null){
            host = "localhost";
        }
        
        final APIHttpServer server = new APIHttpServer(host, port);
        server.start();
    
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                server.stop();
            }
        });
        
    }
}
