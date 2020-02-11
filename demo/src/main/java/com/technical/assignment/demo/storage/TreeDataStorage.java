package com.technical.assignment.demo.storage;

import com.technical.assignment.demo.dto.TreeData;

public interface TreeDataStorage {
    void saveData(TreeData[] treeDataArray);
    TreeData[] getData();
}
