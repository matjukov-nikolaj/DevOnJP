package com.javacourse2018.service;

import com.javacourse2018.model.DisplayMode;
import com.javacourse2018.utilites.TestUtilites;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;


public class HtmlGeneratorTest {

    private TestUtilites utils = new TestUtilites();

    private static final String smallFilePatch = "S_small.patch";
    private static final String smallFile = "S_small.jav";
    private static final String outSplitFile = "splitOut.html";
    private static final String expectedOutSplitFile = "expectedSplitOut.html";
    private static final String outUnifiedFile = "unifiedOut.html";
    private static final String expectedOutUnifiedFile = "expectedUnifiedOut.html";


    private static final String largeFilePatch = "S_large.patch";
    private static final String largeFile = "S_large.jav";
    private static final String expectedOutUnifiedLargeFile = "expectedOutUnifiedLarge.html";
    private static final String outUnifiedLargeFile = "outUnifiedLarge.html";
    private static final String expectedOutSplitLargeFile = "expectedOutSplitLarge.html";
    private static final String outSplitLargeFile= "outSplitLarge.html";


    @Test
    public void htmlGeneratorCanGenerateSplitHtml() {

        PatchFileParser parser = new PatchFileParserImpl(utils.getPathToTestFile(smallFilePatch));
        parser.parse();

        MainFileReader mainFileReader = new MainFileReader(utils.getPathToTestFile(smallFile));
        mainFileReader.read();

        CommitLineGenerator generator = new CommitLineGenerator(mainFileReader.getLines(), parser.getBlocks());
        generator.generate();

        HtmlGenerator htmlGenerator = new HtmlGenerator(DisplayMode.SPLIT, generator.getLines(), utils.getPathToTestFile(outSplitFile), parser.getCommitInfo());
        htmlGenerator.generate();

        try{
            FileInputStream expected = new FileInputStream(utils.getPathToTestFile(expectedOutSplitFile));
            BufferedReader expectedBr = new BufferedReader(new InputStreamReader(expected));
            String expectedLine;

            FileInputStream actual = new FileInputStream(utils.getPathToTestFile(outSplitFile));
            BufferedReader actualBr = new BufferedReader(new InputStreamReader(actual));
            String actualLine;

            while ((expectedLine = expectedBr.readLine()) != null && (actualLine = actualBr.readLine()) != null) {
                Assert.assertEquals(expectedLine, actualLine);
            }
        } catch (IOException e) {
            System.out.println("Files reading error");
        }
    }

    @Test
    public void htmlGeneratorCanGenerateLargeUnifiedHtml() {

        PatchFileParser parser = new PatchFileParserImpl(utils.getPathToTestFile(largeFilePatch));
        parser.parse();

        MainFileReader mainFileReader = new MainFileReader(utils.getPathToTestFile(largeFile));
        mainFileReader.read();

        CommitLineGenerator generator = new CommitLineGenerator(mainFileReader.getLines(), parser.getBlocks());
        generator.generate();

        HtmlGenerator htmlGenerator = new HtmlGenerator(DisplayMode.UNIFIED, generator.getLines(), utils.getPathToTestFile(outUnifiedLargeFile), parser.getCommitInfo());
        htmlGenerator.generate();

        try{
            FileInputStream expected = new FileInputStream(utils.getPathToTestFile(expectedOutUnifiedLargeFile));
            BufferedReader expectedBr = new BufferedReader(new InputStreamReader(expected));
            String expectedLine;

            FileInputStream actual = new FileInputStream(utils.getPathToTestFile(outUnifiedLargeFile));
            BufferedReader actualBr = new BufferedReader(new InputStreamReader(actual));
            String actualLine;

            while ((expectedLine = expectedBr.readLine()) != null && (actualLine = actualBr.readLine()) != null) {
                Assert.assertEquals(expectedLine, actualLine);
            }
        } catch (IOException e) {
            System.out.println("Files reading error");
        }
    }

    @Test
    public void htmlGeneratorCanGenerateUnifiedHtml() {

        PatchFileParser parser = new PatchFileParserImpl(utils.getPathToTestFile(smallFilePatch));
        parser.parse();

        MainFileReader mainFileReader = new MainFileReader(utils.getPathToTestFile(smallFile));
        mainFileReader.read();

        CommitLineGenerator generator = new CommitLineGenerator(mainFileReader.getLines(), parser.getBlocks());
        generator.generate();

        HtmlGenerator htmlGenerator = new HtmlGenerator(DisplayMode.UNIFIED, generator.getLines(), utils.getPathToTestFile(outUnifiedFile), parser.getCommitInfo());
        htmlGenerator.generate();

        try{
            FileInputStream expected = new FileInputStream(utils.getPathToTestFile(expectedOutUnifiedFile));
            BufferedReader expectedBr = new BufferedReader(new InputStreamReader(expected));
            String expectedLine;

            FileInputStream actual = new FileInputStream(utils.getPathToTestFile(outUnifiedFile));
            BufferedReader actualBr = new BufferedReader(new InputStreamReader(actual));
            String actualLine;

            while ((expectedLine = expectedBr.readLine()) != null && (actualLine = actualBr.readLine()) != null) {
                Assert.assertEquals(expectedLine, actualLine);
            }
        } catch (IOException e) {
            System.out.println("Files reading error");
        }
    }

    @Test
    public void htmlGeneratorCanGenerateLargeSplitHtml() {

        PatchFileParser parser = new PatchFileParserImpl(utils.getPathToTestFile(largeFilePatch));
        parser.parse();

        MainFileReader mainFileReader = new MainFileReader(utils.getPathToTestFile(largeFile));
        mainFileReader.read();

        CommitLineGenerator generator = new CommitLineGenerator(mainFileReader.getLines(), parser.getBlocks());
        generator.generate();

        HtmlGenerator htmlGenerator = new HtmlGenerator(DisplayMode.SPLIT, generator.getLines(), utils.getPathToTestFile(outSplitLargeFile), parser.getCommitInfo());
        htmlGenerator.generate();

        try{
            FileInputStream expected = new FileInputStream(utils.getPathToTestFile(expectedOutSplitLargeFile));
            BufferedReader expectedBr = new BufferedReader(new InputStreamReader(expected));
            String expectedLine;

            FileInputStream actual = new FileInputStream(utils.getPathToTestFile(outSplitLargeFile));
            BufferedReader actualBr = new BufferedReader(new InputStreamReader(actual));
            String actualLine;

            while ((expectedLine = expectedBr.readLine()) != null && (actualLine = actualBr.readLine()) != null) {
                Assert.assertEquals(expectedLine, actualLine);
            }
        } catch (IOException e) {
            System.out.println("Files reading error");
        }
    }
}