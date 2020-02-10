package com.technical.assignment.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TreeData {
    String spc_common;
    Double x_sp;
    Double y_sp;
}
