package com.technical.assignment.demo.service;

import com.technical.assignment.demo.dto.Tree;
import com.technical.assignment.demo.storage.TreeDataStorage;
import com.technical.assignment.demo.storage.TreeDataStorageImpl;
import org.junit.Assert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
class TreeCounterTest {

    TreeCounter treeCounter;

    TreeDataStorage treeDataStorage;
    Tree[] test_suit_one;
    Tree[] test_suit_two;

    @BeforeEach
    void setUp() {
        treeDataStorage = new TreeDataStorageImpl();
        treeCounter = new TreeCounter(treeDataStorage);

        test_suit_one = new Tree[]{new Tree("Norway maple",100d,100d),
                new Tree("northern red oak",50d,50d),
                new Tree("Japanese zelkova",10d,10d)};

        test_suit_two = new Tree[]{new Tree("Norway maple",100d,100d),
                new Tree("Norway maple",25d,25d),
                new Tree("Norway maple",75d,75d)};
    }

    @Test
    void countNearbyTreesByTypesShouldFindCorrectNumberOfTrees() {
        treeDataStorage.saveData(test_suit_one);
        Map<String, Integer> nearbyTrees = treeCounter.countNearbyTreesByTypes(100d, 100d, 10d);
        Assert.assertEquals(1, nearbyTrees.size());
        nearbyTrees = treeCounter.countNearbyTreesByTypes(100d, 100d, 25d);
        Assert.assertEquals(2, nearbyTrees.size());
        nearbyTrees = treeCounter.countNearbyTreesByTypes(100d, 100d, 50d);
        Assert.assertEquals(3, nearbyTrees.size());
    }

    @Test
    void countNearbyTreesByTypesShouldFindCorrectNameOfTrees() {
        treeDataStorage.saveData(test_suit_one);
        Map<String, Integer> nearbyTrees = treeCounter.countNearbyTreesByTypes(100d, 100d, 10d);
        Assert.assertTrue(nearbyTrees.containsKey("Norway maple"));
        nearbyTrees = treeCounter.countNearbyTreesByTypes(100d, 100d, 25d);
        Assert.assertTrue(nearbyTrees.containsKey("northern red oak"));
        nearbyTrees = treeCounter.countNearbyTreesByTypes(100d, 100d, 50d);
        Assert.assertTrue(nearbyTrees.containsKey("Japanese zelkova"));
    }

    @Test
    void countNearbyTreesByTypesShouldFindCorrectCountOfTrees() {
        treeDataStorage.saveData(test_suit_two);
        Map<String, Integer> nearbyTrees = treeCounter.countNearbyTreesByTypes(100d, 100d, 100d);
        Assert.assertEquals(3, (int) nearbyTrees.get("Norway maple"));
    }
}