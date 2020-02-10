package com.technical.assignment.demo.service.rest;

import com.technical.assignment.demo.controller.TreeDataController;
import com.technical.assignment.demo.service.TreeDataResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.core.Response;
import java.util.Map;

@Controller
public class EndpointImpl implements Endpoint {

    @Autowired TreeDataController treeDataController;
    @Autowired TreeDataResponseBuilder treeDataResponseBuilder;

    public Response nearbyTrees(Double x, Double y, Double radius) {
        Map<String, Integer> nearbyTrees = treeDataController.countNearbyTreesByTypes(x, y, radius);
        Response response = treeDataResponseBuilder.prepareResponse(nearbyTrees);

        return response;
    }
}
