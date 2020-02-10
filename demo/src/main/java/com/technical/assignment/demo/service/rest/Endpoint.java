package com.technical.assignment.demo.service.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/service")
public interface Endpoint {

    @GET
    @Path("/nearby-trees")
    @Produces(MediaType.APPLICATION_JSON)
    Response nearbyTrees(@QueryParam("X") Double x,
                         @QueryParam("Y") Double y,
                         @QueryParam("radius") Double radius);
}
