package com.technical.assignment.demo.service;

import com.technical.assignment.demo.storage.TreeDataStorage;
import com.technical.assignment.demo.dto.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TreeCounter {

    private final TreeDataStorage treeDataStorage;

    public TreeCounter(@Autowired TreeDataStorage treeDataStorage) {
        this.treeDataStorage = treeDataStorage;
    }

    public Map<String, Integer> countNearbyTreesByTypes(Double x, Double y, Double radiusInMeters) {
        Double radiusInFeet = metersToFeet(radiusInMeters);
        Tree[] treeArray = treeDataStorage.getData();
        Map<String, Integer> nearbyTrees = countNearbyTreesByTypes(treeArray, x, y, radiusInFeet);

        return nearbyTrees;
    }

    private Map<String, Integer> countNearbyTreesByTypes(Tree[] trees, Double x, Double y, Double radius) {
        Map<String, Integer> map = new HashMap<>();
        if (trees == null) return map;

        for (Tree tree : trees) {
            double distance = Math.sqrt((tree.getXPosition() - x) * (tree.getXPosition() - x) + (tree.getYPosition() - y) * (tree.getYPosition() - y));
            if (distance <= radius) {
                map.put(tree.getTreeType(), map.getOrDefault(tree.getTreeType(), 0) + 1);
            }
        }

        return map;
    }

    private Double metersToFeet(Double meters) {
        double meterToFeetRatio = 3.28084d;
        return meterToFeetRatio * meters;
    }
}
