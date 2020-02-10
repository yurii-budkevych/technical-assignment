package com.technical.assignment.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class Config {

    @Value("${cityofnewyork.resource.path}")
    public String CITYOFNEWYORK_RESOURCE_PATH ;

    @Value("${cityofnewyork.data.trees}")
    public String CITYOFNEWYORK_DATA_TREES;
}
