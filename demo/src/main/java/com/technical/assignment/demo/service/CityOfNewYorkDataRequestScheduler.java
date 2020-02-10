package com.technical.assignment.demo.service;

import com.technical.assignment.demo.config.Config;
import com.technical.assignment.demo.storage.DataStorageImpl;
import com.technical.assignment.demo.dto.TreeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
public class CityOfNewYorkDataRequestScheduler {

    @Autowired Config config;
    @Autowired Client client;
    @Autowired DataStorageImpl dataStorageImpl;

    @Scheduled(fixedRateString = "${cityofnewyork.data.trees.refresh-period}")
    private void requestData() {
        Invocation.Builder invocationBuilder =
                client.target(config.CITYOFNEWYORK_RESOURCE_PATH).path(config.CITYOFNEWYORK_DATA_TREES)
                        .request(MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.accept(MediaType.APPLICATION_JSON_TYPE).get();
        save(response);
    }

    private void save(Response response) {
        TreeData[] treeDataArray = response.readEntity(TreeData[].class);
        dataStorageImpl.saveData(treeDataArray);
    }
}
