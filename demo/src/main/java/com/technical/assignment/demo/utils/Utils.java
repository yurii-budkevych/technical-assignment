package com.technical.assignment.demo.utils;

import org.springframework.stereotype.Service;

@Service
public class Utils {
    public Double meterToFeet(Double meters) {
        return 3.28084 * meters;
    }
}
