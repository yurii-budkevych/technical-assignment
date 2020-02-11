package com.technical.assignment.demo.storage;

import com.technical.assignment.demo.dto.Tree;
import org.springframework.stereotype.Component;

@Component
public class TreeDataStorageImpl implements TreeDataStorage {

    private Tree[] treeArray;

    public void saveData(Tree[] treeArray) {
        this.treeArray = treeArray;
    }
    public Tree[] getData() {
        return treeArray;
    }
}
