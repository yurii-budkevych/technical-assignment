package com.technical.assignment.demo.service;

import com.technical.assignment.demo.storage.TreeDataStorage;
import com.technical.assignment.demo.dto.TreeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CountNearbyTreesUsecase {

    private final TreeDataStorage treeDataStorage;

    public CountNearbyTreesUsecase(@Autowired TreeDataStorage treeDataStorage) {
        this.treeDataStorage = treeDataStorage;
    }

    public Map<String, Integer> countNearbyTreesByTypes(Double x, Double y, Double radius) {
        Double radiusInFeets = meterToFeet(radius);
        TreeData[] treeDataArray = treeDataStorage.getData();
        Map<String, Integer> nearbyTrees = countNearbyTreesByTypes(treeDataArray, x, y, radiusInFeets);

        return nearbyTrees;
    }

    private Map<String, Integer> countNearbyTreesByTypes(TreeData[] treeData, Double x, Double y, Double radius) {
        Map<String, Integer> map = new HashMap<>();
        if (treeData == null) return map;

        for (TreeData tree : treeData) {
            double distance = Math.sqrt((tree.getXPosition() - x) * (tree.getXPosition() - x) + (tree.getYPosition() - y) * (tree.getYPosition() - y));
            if (distance <= radius) {
                map.put(tree.getTreeType(), map.getOrDefault(tree.getTreeType(), 0) + 1);
            }
        }

        return map;
    }

    public Double meterToFeet(Double meters) {
        double meterToFeetRatio = 3.28084d;
        return meterToFeetRatio * meters;
    }
}
