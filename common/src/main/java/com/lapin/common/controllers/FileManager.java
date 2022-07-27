package com.lapin.common.controllers;

import com.lapin.common.exception.MaxRecursionExceededException;
import com.lapin.common.exception.NullEnvException;

import java.io.IOException;

public interface FileManager {
    public void setCollectionManager(CollectionManager collectionManager);
    public String saveCollection(CollectionManager collectionManager) throws IOException, NullEnvException;
   // public void readCollection();
    public void readScript(String fileName) throws MaxRecursionExceededException, IOException;
    public void setEnv(String env);
    public String getEnv();
}
