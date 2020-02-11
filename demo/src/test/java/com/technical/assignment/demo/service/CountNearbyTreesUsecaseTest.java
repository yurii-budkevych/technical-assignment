package com.technical.assignment.demo.service;

import com.technical.assignment.demo.dto.TreeData;
import com.technical.assignment.demo.storage.TreeDataStorage;
import com.technical.assignment.demo.storage.TreeDataStorageImpl;
import org.junit.Assert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
class CountNearbyTreesUsecaseTest {

    CountNearbyTreesUsecase countNearbyTreesUsecase;

    TreeDataStorage treeDataStorage;
    TreeData[] test_suit_one;
    TreeData[] test_suit_two;

    @BeforeEach
    void setUp() {
        treeDataStorage = new TreeDataStorageImpl();
        countNearbyTreesUsecase = new CountNearbyTreesUsecase(treeDataStorage);

        test_suit_one = new TreeData[]{new TreeData("Norway maple",100d,100d),
                new TreeData("northern red oak",50d,50d),
                new TreeData("Japanese zelkova",10d,10d)};

        test_suit_two = new TreeData[]{new TreeData("Norway maple",100d,100d),
                new TreeData("Norway maple",25d,25d),
                new TreeData("Norway maple",75d,75d)};
    }

    @Test
    void countNearbyTreesByTypesShouldFindCorrectNumberOfTrees() {
        treeDataStorage.saveData(test_suit_one);
        Map<String, Integer> nearbyTrees = countNearbyTreesUsecase.countNearbyTreesByTypes(100d, 100d, 10d);
        Assert.assertEquals(1, nearbyTrees.size());
        nearbyTrees = countNearbyTreesUsecase.countNearbyTreesByTypes(100d, 100d, 25d);
        Assert.assertEquals(2, nearbyTrees.size());
        nearbyTrees = countNearbyTreesUsecase.countNearbyTreesByTypes(100d, 100d, 50d);
        Assert.assertEquals(3, nearbyTrees.size());
    }

    @Test
    void countNearbyTreesByTypesShouldFindCorrectNameOfTrees() {
        treeDataStorage.saveData(test_suit_one);
        Map<String, Integer> nearbyTrees = countNearbyTreesUsecase.countNearbyTreesByTypes(100d, 100d, 10d);
        Assert.assertTrue(nearbyTrees.containsKey("Norway maple"));
        nearbyTrees = countNearbyTreesUsecase.countNearbyTreesByTypes(100d, 100d, 25d);
        Assert.assertTrue(nearbyTrees.containsKey("northern red oak"));
        nearbyTrees = countNearbyTreesUsecase.countNearbyTreesByTypes(100d, 100d, 50d);
        Assert.assertTrue(nearbyTrees.containsKey("Japanese zelkova"));
    }

    @Test
    void countNearbyTreesByTypesShouldFindCorrectCountOfTrees() {
        treeDataStorage.saveData(test_suit_two);
        Map<String, Integer> nearbyTrees = countNearbyTreesUsecase.countNearbyTreesByTypes(100d, 100d, 100d);
        Assert.assertEquals(3, (int) nearbyTrees.get("Norway maple"));
    }
}