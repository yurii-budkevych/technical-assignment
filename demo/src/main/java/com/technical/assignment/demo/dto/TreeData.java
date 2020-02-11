package com.technical.assignment.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TreeData {
    @JsonProperty("spc_common")
    private String treeType;
    @JsonProperty("x_sp")
    private Double xPosition;
    @JsonProperty("y_sp")
    private Double yPosition;
}
