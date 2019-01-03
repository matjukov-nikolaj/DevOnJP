package com.javacourse2018.service;

import com.javacourse2018.model.CommitInfo;
import com.javacourse2018.model.CommitLineStatus;
import com.javacourse2018.model.DifferenceBlock;
import com.javacourse2018.utilites.TestUtilites;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class PatchFileParserImplTest {


    private static final String smallFile = "S_small.patch";
    private static final String largeFile = "S_large.patch";
    private static final String brokenFile = "S_incorrect_file.patch";

    private TestUtilites utils = new TestUtilites();

    @Test
    public void testUserCanParseUserInf() {
        PatchFileParser parser = null;

        parser = new PatchFileParserImpl(utils.getPathToTestFile(smallFile));
        parser.parse();
        CommitInfo info = parser.getCommitInfo();
        Assert.assertEquals(info.getDate(), "Date: Thu, 3 Jan 2019 11:59:56 +0300");
        Assert.assertEquals(info.getSubject(), "Subject: [PATCH] 5");
        Assert.assertEquals(info.getFileName(), " lolCheck.java | 1 +");
        Assert.assertEquals(info.getAuthor().getName(), "From: matjukov-nikolaj <blatnoi.kolyan@yandex.ru>");
    }

    @Test
    public void testParserCanParseDifferentBlocks() {
        PatchFileParser parser = null;

        parser = new PatchFileParserImpl(utils.getPathToTestFile(smallFile));
        parser.parse();
        List<DifferenceBlock> blocks = parser.getBlocks();
        Assert.assertEquals(blocks.size(), 1);
        DifferenceBlock block = blocks.get(0);
        Assert.assertEquals(block.getFunctionName(), null);
        Assert.assertEquals(block.getCommitLines().size(), 1);
        Assert.assertEquals(block.getCommitLines().get(0).getPayload(), "+123");
        Assert.assertEquals(block.getCommitLines().get(0).getStatus(), CommitLineStatus.INSERTED);
    }

    @Test
    public void testParserCanParseLargeFile() {
        PatchFileParser parser = null;

        parser = new PatchFileParserImpl(utils.getPathToTestFile(largeFile));
        parser.parse();

        Assert.assertEquals(parser.getBlocks().size(), 10);
        DifferenceBlock firstBlock = parser.getBlocks().get(0);
        DifferenceBlock lastBlock = parser.getBlocks().get(9);

        Assert.assertEquals(firstBlock.getCommitLines().size(), 23);
        Assert.assertEquals(firstBlock.getCommitLines().get(0).getPayload(), "");
        Assert.assertEquals(firstBlock.getCommitLines().get(1).getPayload(), "     private Operands operands = new Operands();");
        Assert.assertEquals(firstBlock.getCommitLines().get(2).getPayload(), "");

        Assert.assertEquals(lastBlock.getCommitLines().size(), 26);
        Assert.assertEquals(lastBlock.getCommitLines().get(0).getPayload(), "         Cell cell;");
        Assert.assertEquals(lastBlock.getCommitLines().get(1).getPayload(), "         cell = this.spreadsheet.getCell(position);");
        Assert.assertEquals(lastBlock.getCommitLines().get(2).getPayload(), "         String cellValue = cell.getValue();");
    }

    @Test
    public void testTryingParseBrokenFile() {
        PatchFileParser parser = null;

        parser = new PatchFileParserImpl(utils.getPathToTestFile(brokenFile));
        parser.parse();
        Assert.assertNull(parser.getCommitInfo().getAuthor());
    }
}