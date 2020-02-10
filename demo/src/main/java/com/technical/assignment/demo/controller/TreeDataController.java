package com.technical.assignment.demo.controller;

import com.technical.assignment.demo.storage.DataStorage;
import com.technical.assignment.demo.dto.TreeData;
import com.technical.assignment.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TreeDataController {

    private DataStorage dataStorage;
    private Utils utils;

    public TreeDataController(@Autowired DataStorage dataStorage, @Autowired Utils utils) {
        this.dataStorage = dataStorage;
        this.utils = utils;
    }

    public Map<String, Integer> countNearbyTreesByTypes(Double x, Double y, Double radius) {
        Double radiusInFeets = utils.meterToFeet(radius);
        TreeData[] treeDataArray = dataStorage.getData();
        Map<String, Integer> nearbyTrees = countNearbyTreesByTypes(treeDataArray, x, y, radiusInFeets);

        return nearbyTrees;
    }

    private Map<String, Integer> countNearbyTreesByTypes(TreeData[] treeData, Double x, Double y, Double radius) {
        Map<String, Integer> map = new HashMap<>();
        if (treeData == null) return map;

        for (TreeData tree : treeData) {
            double distance = Math.sqrt((tree.getX_sp() - x) * (tree.getX_sp() - x) + (tree.getY_sp() - y) * (tree.getY_sp() - y));
            if (distance <= radius) {
                map.put(tree.getSpc_common(), map.getOrDefault(tree.getSpc_common(), 0) + 1);
            }
        }

        return map;
    }
}
