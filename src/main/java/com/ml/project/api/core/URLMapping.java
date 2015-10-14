/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.project.api.core;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

/**
 *
 * @author fcambarieri
 */
public class URLMapping {
    
    private final LinkedHashMap<String, URLEntry> mapping = new LinkedHashMap<>();
    private final LinkedHashMap<Pattern, URLEntry> compiledMapping = new LinkedHashMap<>();

    public URLMapping() {
        
    }
    
    public URLMapping(LinkedHashMap<String, URLEntry> mapping) {
        init(mapping);
    }
    
    public void init(LinkedHashMap<String, URLEntry> mapping) {
        this.mapping.putAll(mapping);
        buildRegEx();
    }
    
    private void buildRegEx() {
        for (String sRegEx : mapping.keySet()) {
            Pattern pattern = Pattern.compile(sRegEx);
            URLEntry r =  mapping.get(sRegEx);
            compiledMapping.put(pattern, r);
        }
    }
    
    public URLEntry getUriHandler(String expression){
        for (Pattern pattern : compiledMapping.keySet()) {
                if(pattern.matcher(expression).matches()){
                        return compiledMapping.get(pattern);
                }
        }
        return null;
    }
    
   
    
}
