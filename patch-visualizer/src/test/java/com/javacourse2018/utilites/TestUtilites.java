package com.javacourse2018.utilites;

import java.nio.file.Path;
import java.nio.file.Paths;

public class TestUtilites {

    public String getPathToTestFile(String path) {
        Path currentRelativePath = Paths.get("");
        String projectPath = currentRelativePath.toAbsolutePath().toString();
        String filePath = projectPath + "\\src\\test\\java\\resources\\" + path;
        return filePath;
    }

}
