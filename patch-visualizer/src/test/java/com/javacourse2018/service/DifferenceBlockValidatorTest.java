package com.javacourse2018.service;

import com.javacourse2018.utilites.TestUtilites;
import org.junit.Test;

import java.io.IOException;

public class DifferenceBlockValidatorTest {

    TestUtilites utils = new TestUtilites();

    private static final String BROKEN_MAIN_FILE = "broken_main_file.jav";
    private static final String PATCH_FOR_BROKEN_FILE = "broken_main_file.patch";

    @Test(expected = IOException.class)
    public void test_with_incorrect_main_file() throws IOException {
        PatchFileParser parser = new PatchFileParserImpl(utils.getPathToTestFile(PATCH_FOR_BROKEN_FILE));
        parser.parse();

        MainFileReader mainFileReader = new MainFileReader(utils.getPathToTestFile(BROKEN_MAIN_FILE));
        mainFileReader.read();

        DifferenceBlockValidator differenceBlockValidator = new DifferenceBlockValidator(parser.getBlocks(), mainFileReader.getLines());
        differenceBlockValidator.check();
    }

}