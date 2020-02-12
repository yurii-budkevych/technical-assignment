package com.technical.assignment.demo.controller.rest;

import com.technical.assignment.demo.service.TreeCounter;
import com.technical.assignment.demo.service.TreeCounterResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.core.Response;
import java.util.Map;

@Controller
public class EndpointImpl implements Endpoint {

    @Autowired
    private TreeCounter treeCounter;
    @Autowired
    private TreeCounterResponseBuilder treeCounterResponseBuilder;

    public Response nearbyTrees(Double x, Double y, Double radius) {
        if (x == null || y == null || radius == null || radius < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Map<String, Integer> nearbyTrees = treeCounter.countNearbyTreesByTypes(x, y, radius);
        return treeCounterResponseBuilder.prepareResponse(nearbyTrees);
    }
}
