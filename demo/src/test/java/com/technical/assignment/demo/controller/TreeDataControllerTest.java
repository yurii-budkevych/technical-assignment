package com.technical.assignment.demo.controller;

import com.technical.assignment.demo.dto.TreeData;
import com.technical.assignment.demo.storage.DataStorage;
import com.technical.assignment.demo.storage.DataStorageImpl;
import com.technical.assignment.demo.utils.Utils;
import org.junit.Assert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
class TreeDataControllerTest {

    TreeDataController treeDataController;

    Utils utils;
    DataStorage dataStorage;
    TreeData[] test_suit_one;
    TreeData[] test_suit_two;

    @BeforeEach
    void setUp() {
        utils = new Utils();
        dataStorage = new DataStorageImpl();
        treeDataController = new TreeDataController(dataStorage, utils);

        test_suit_one = new TreeData[]{new TreeData("Norway maple",100d,100d),
                new TreeData("northern red oak",50d,50d),
                new TreeData("Japanese zelkova",10d,10d)};

        test_suit_two = new TreeData[]{new TreeData("Norway maple",100d,100d),
                new TreeData("Norway maple",25d,25d),
                new TreeData("Norway maple",75d,75d)};
    }

    @Test
    void countNearbyTreesByTypesShouldFindCorrectNumberOfTrees() {
        dataStorage.saveData(test_suit_one);
        Map<String, Integer> nearbyTrees = treeDataController.countNearbyTreesByTypes(100d, 100d, 10d);
        Assert.assertEquals(1, nearbyTrees.size());
        nearbyTrees = treeDataController.countNearbyTreesByTypes(100d, 100d, 25d);
        Assert.assertEquals(2, nearbyTrees.size());
        nearbyTrees = treeDataController.countNearbyTreesByTypes(100d, 100d, 50d);
        Assert.assertEquals(3, nearbyTrees.size());
    }

    @Test
    void countNearbyTreesByTypesShouldFindCorrectNameOfTrees() {
        dataStorage.saveData(test_suit_one);
        Map<String, Integer> nearbyTrees = treeDataController.countNearbyTreesByTypes(100d, 100d, 10d);
        Assert.assertTrue(nearbyTrees.containsKey("Norway maple"));
        nearbyTrees = treeDataController.countNearbyTreesByTypes(100d, 100d, 25d);
        Assert.assertTrue(nearbyTrees.containsKey("northern red oak"));
        nearbyTrees = treeDataController.countNearbyTreesByTypes(100d, 100d, 50d);
        Assert.assertTrue(nearbyTrees.containsKey("Japanese zelkova"));
    }

    @Test
    void countNearbyTreesByTypesShouldFindCorrectCountOfTrees() {
        dataStorage.saveData(test_suit_two);
        Map<String, Integer> nearbyTrees = treeDataController.countNearbyTreesByTypes(100d, 100d, 100d);
        Assert.assertEquals(3, (int) nearbyTrees.get("Norway maple"));
    }
}