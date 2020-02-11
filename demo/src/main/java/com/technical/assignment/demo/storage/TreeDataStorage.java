package com.technical.assignment.demo.storage;

import com.technical.assignment.demo.dto.Tree;

public interface TreeDataStorage {

    void saveData(Tree[] treeArray);
    Tree[] getData();
}
