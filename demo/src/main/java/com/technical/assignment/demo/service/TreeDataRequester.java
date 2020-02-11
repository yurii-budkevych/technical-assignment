package com.technical.assignment.demo.service;

import com.technical.assignment.demo.storage.TreeDataStorage;
import com.technical.assignment.demo.dto.TreeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
public class TreeDataRequester {

    @Value("${cityofnewyork.resource.path}")
    public String CITYOFNEWYORK_RESOURCE_PATH ;

    @Value("${cityofnewyork.data.trees}")
    public String CITYOFNEWYORK_DATA_TREES;

    @Autowired
    private Client client;
    @Autowired
    private TreeDataStorage treeDataStorage;

    @Scheduled(fixedRateString = "${cityofnewyork.data.trees.refresh-period}")
    private void requestData() {
        Invocation.Builder invocationBuilder =
                client.target(CITYOFNEWYORK_RESOURCE_PATH).path(CITYOFNEWYORK_DATA_TREES)
                        .request(MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.accept(MediaType.APPLICATION_JSON_TYPE).get();
        save(response);
    }

    private void save(Response response) {
        TreeData[] treeDataArray = response.readEntity(TreeData[].class);
        treeDataStorage.saveData(treeDataArray);
    }
}
