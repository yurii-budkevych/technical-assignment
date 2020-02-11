package com.technical.assignment.demo.controller.rest;

import com.technical.assignment.demo.service.CountNearbyTreesUsecase;
import com.technical.assignment.demo.service.CountNearbyTreesResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.core.Response;
import java.util.Map;

@Controller
public class EndpointImpl implements Endpoint {

    @Autowired
    private CountNearbyTreesUsecase countNearbyTreesUsecase;
    @Autowired
    private CountNearbyTreesResponseBuilder countNearbyTreesResponseBuilder;

    public Response nearbyTrees(Double x, Double y, Double radius) {
        Map<String, Integer> nearbyTrees = countNearbyTreesUsecase.countNearbyTreesByTypes(x, y, radius);
        Response response = countNearbyTreesResponseBuilder.prepareResponse(nearbyTrees);

        return response;
    }
}
