package com.technical.assignment.demo.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Map;

@Service
public class TreeCounterResponseBuilder {

    public static final String INFO = "info";
    public static final String NO_DATA = "no data";

    public Response prepareResponse(Map<String, Integer> treeData) {
        return Response.status(Response.Status.OK)
                .entity(toJSONObject(treeData).toString())
                .build();
    }

    private JSONObject toJSONObject(Map<String, Integer> treeData) {
        if (treeData.size() == 0) return new JSONObject().put(INFO, NO_DATA);

        JSONObject result = new JSONObject();

        for (Map.Entry<String, Integer> entry : treeData.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) continue;
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
