package com.javacourse2018.service;

import com.javacourse2018.utilites.TestUtilites;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainFileReaderTest extends TestUtilites {

    private static final String MAIN_FILE = "k_mainFile.txt";
    private static final String MAIN_FILE_NOT_EXIST = "k_mainFile1.txt";

    @Test
    public void construct_from_main_file_path() {
        MainFileReader mainFileReader = new MainFileReader(getPathToTestFile(MAIN_FILE));
        Assert.assertNotNull(mainFileReader.getMainFilePath());
        Assert.assertNotNull(mainFileReader.getLines());
    }

    @Test
    public void can_set_fields_from_out() {
        MainFileReader mainFileReader = new MainFileReader(getPathToTestFile(MAIN_FILE));
        mainFileReader.setMainFilePath(getPathToTestFile(MAIN_FILE));
        mainFileReader.setLines(new ArrayList<>());
        Assert.assertNotNull(mainFileReader.getMainFilePath());
        Assert.assertNotNull(mainFileReader.getLines());
    }

    @Test
    public void can_read_file() {
        MainFileReader mainFileReader = new MainFileReader(getPathToTestFile(MAIN_FILE));
        mainFileReader.read();
        String[] lines = {"", "1", "2", "3", "4"};
        List<String> expectedLines = new ArrayList<>(Arrays.asList(lines));
        Assert.assertEquals(expectedLines, mainFileReader.getLines());
    }

    @Test
    public void if_IO_error_throw_exception_and_lines_is_empty() {
        MainFileReader mainFileReader = new MainFileReader(MAIN_FILE_NOT_EXIST);
        mainFileReader.read();
        Assert.assertTrue(mainFileReader.getLines().isEmpty());
    }

}