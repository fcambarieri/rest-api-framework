/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.project.api;

import com.ml.project.api.core.DispacherHandlers;
import com.ml.project.api.core.URLEntry;
import com.ml.project.api.core.URLMapping;
import com.ml.project.api.core.controllers.ControllerFactory;
import com.ml.project.api.core.controllers.PingController;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.grizzly.http.Method;
import org.glassfish.grizzly.http.server.HttpServer;

/**
 *
 * @author fcambarieri
 */
public class APIHttpServer {

    private final Logger logger = Logger.getLogger(APIHttpServer.class.getName());
    
    private HttpServer httpServer;
    private boolean stop = false;
    
    
    public APIHttpServer() {
        this("localhost", 8080);
    }

    public APIHttpServer(String host , int port) {
        httpServer = HttpServer.createSimpleServer(host, port);
        httpServer.getServerConfiguration().addHttpHandler(new DispacherHandlers(new URLMapping(buildMapping()), new ControllerFactory()), "/");
    }
    
    
    public void start() throws IOException {
        
        httpServer.start();
        
        while(!stop) {
            try {
              Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(APIHttpServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Create a FilterChain using FilterChainBuilder
//        FilterChainBuilder filterChainBuilder = FilterChainBuilder.stateless();
//
//        // Add TransportFilter, which is responsible
//        // for reading and writing data to the connection
//        filterChainBuilder.add(new TransportFilter());
//
//        // StringFilter is responsible for Buffer <-> String conversion
//        filterChainBuilder.add(new StringFilter(Charset.forName("UTF-8")));
//        
//        
//
//        // EchoFilter is responsible for echoing received messages
//        //filterChainBuilder.add(new EchoFilter());
//        // Create TCP transport
//        final TCPNIOTransport transport
//                = TCPNIOTransportBuilder.newInstance().build();
//
//        transport.setSelectorHandler(null);
//        
//        transport.setProcessor(filterChainBuilder.build());
//        try {
//            // binding transport to start listen on certain host and port
//            transport.bind(HOST, PORT);
//
//            
//            // start the transport
//            transport.start();
//            
//            
//
//            logger.info("Press any key to stop the server...");
//            System.in.read();
//        } finally {
//            logger.info("Stopping transport...");
//            // stop the transport
//            transport.shutdownNow();
//
//            logger.info("Stopped transport...");
//        }
    }

    public void stop() {
        logger.info("shutdown Now!");
        httpServer.shutdownNow();
        stop = true;
    }
    
    public LinkedHashMap<String, URLEntry> buildMapping() {
        
        LinkedHashMap<String, URLEntry> mapping = new LinkedHashMap<>();
        
        mapping.put("/ping", URLEntry.createEntry(PingController.class.getName(), Method.GET, "ping"));
        
        return mapping;
    }
}
