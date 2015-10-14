/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.project.api.core.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Named;

/**
 *
 * @author fcambarieri
 */
@Named("metricsService")
public class TimeMetricService implements MetricsService{

    @Override
    public Map metrics() {
        Map map = new HashMap();
        map.put("time", new Date().getTime());
        return map;
    }
    
}
