package com.technical.assignment.demo.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
@Path("/service")
public class HelloWorld {

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrackInJSON() {

        JSONObject object = new JSONObject();
        object.put("Response", "get");
        object.put("Hello", "Holidu");
        Response response = Response.status(Response.Status.OK).entity(object.toString()).build();

        return response;
    }
}
