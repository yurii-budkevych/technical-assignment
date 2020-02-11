package com.technical.assignment.demo.storage;

import com.technical.assignment.demo.dto.TreeData;
import org.springframework.stereotype.Component;

@Component
public class TreeDataStorageImpl implements TreeDataStorage {

    private TreeData[] treeDataArray;

    public void saveData(TreeData[] treeDataArray) {
        this.treeDataArray = treeDataArray;
    }

    public TreeData[] getData() {
        return treeDataArray;
    }
}