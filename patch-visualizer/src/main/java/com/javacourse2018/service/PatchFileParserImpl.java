package com.javacourse2018.service;

import com.javacourse2018.model.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatchFileParserImpl implements PatchFileParser {
    private static final Log LOG = LogFactory.getLog(PatchFileParserImpl.class);
    private static final String PATTERN = "[" + "\\" + "@]{2,2}\\s[" + "\\" + "-]{1,1}[0-9]{1,}["
                                            + "\\" + ",]{0,1}[0-9]{0,}\\s[" + "\\"
                                            + "+]{1,1}[0-9]{1,}[" + "\\"
                                            + ",]{0,1}[0-9]{0,}\\s[" + "\\" + "@]{2,2}.{0,}";

    private static final String FROM = "From: ";
    private static final String DATE = "Date: ";
    private static final String SUBJECT = "Subject: ";
    private static final String FILE_NAME_SEPARATOR = "---";


    private List<DifferenceBlock> blocks;
    private CommitInfo commitInfo;
    private String patchFile;

    public PatchFileParserImpl(String patchFile) {
        this.patchFile = patchFile;
        this.blocks = new ArrayList<>();
        this.commitInfo = new CommitInfo();
    }

    public List<DifferenceBlock> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<DifferenceBlock> blocks) {
        this.blocks = blocks;
    }

    public CommitInfo getCommitInfo() {
        return commitInfo;
    }

    public void setCommitInfo(CommitInfo commitInfo) {
        this.commitInfo = commitInfo;
    }

    public void parse() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.patchFile))) {
            String line;
            Integer totalNumbersLine = 0;
            DifferenceBlock differenceBlock = null;
            List<CommitLine> commitLines = new ArrayList<>();
            Boolean newBlock = false;
            Boolean isFileName = false;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty() && line.toCharArray()[0] == '\\') {
                    continue;
                }
                totalNumbersLine++;
                if (totalNumbersLine > 1 && totalNumbersLine < 8 && !line.isEmpty()) {
                    if (line.substring(0, 3).contains(FILE_NAME_SEPARATOR)) {
                        isFileName = true;
                        continue;
                    }
                    this.handleCommitInformation(line, isFileName);
                    continue;
                }
                if (line.matches(PATTERN)) {
                    this.setCommitLinesToDifferenceBlock(differenceBlock, commitLines);
                    differenceBlock = new DifferenceBlock();
                    commitLines = new ArrayList<>();
                    this.parseFunctionNameInBlockSeparator(line, differenceBlock);
                    this.parseBlockSeparator(line, differenceBlock);
                    newBlock = true;
                    continue;
                }
                if (newBlock) {
                    CommitLine commitLine = this.getCommitLineFromLine(line);
                    commitLines.add(commitLine);
                }
            }
            this.setCommitLinesToDifferenceBlock(differenceBlock, commitLines);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    private void handleCommitInformation(String line, Boolean isFileName) throws IOException {
        if (line.isEmpty()) {
            return;
        }
        if (line.contains(FROM)) {
            Person person = new Person();
            person.setName(line);
            commitInfo.setAuthor(person);
        } else if (line.contains(DATE)) {
            commitInfo.setDate(line);
        } else if (line.contains(SUBJECT)) {
            commitInfo.setSubject(line);
        } else if (isFileName) {
            commitInfo.setFileName(line);
        } else {
            throw new IOException();
        }
    }

    private CommitLine getCommitLineFromLine(String line) {
        CommitLine commitLine = new CommitLineImpl();
        commitLine.setPayload(line);
        commitLine.setStatus(this.getCommitLineStatus(line));
        return commitLine;
    }

    private CommitLineStatus getCommitLineStatus(String line) {
        if (line.isEmpty()) {
            return CommitLineStatus.DEFAULT;
        }
        Character ch = line.charAt(0);
        if (ch.equals('-')) {
            return CommitLineStatus.REMOVED;
        }
        if (ch.equals('+')) {
            return CommitLineStatus.INSERTED;
        }
        return CommitLineStatus.DEFAULT;
    }

    private void setCommitLinesToDifferenceBlock(DifferenceBlock differenceBlock, List<CommitLine> commitLines) {
        if (differenceBlock != null && !commitLines.isEmpty()) {
            differenceBlock.setCommitLines(commitLines);
            this.blocks.add(differenceBlock);
        }
    }

    private void parseBlockSeparator(String line, DifferenceBlock differenceBlock) throws IOException {
        List<String> parsedChunkLines = Arrays.asList(line.split(" "));
        if (parsedChunkLines.size() < 4) {
            throw new IOException();
        }
        if (!parsedChunkLines.get(0).equals("@@") || !parsedChunkLines.get(3).equals("@@")) {
            throw new IOException();
        }
        String minusNumber = parsedChunkLines.get(1);
        String plusNumber = parsedChunkLines.get(2);
        if (minusNumber.contains("-")) {
            differenceBlock.setPrevCommit(this.getCommitChunkPosition(minusNumber));
        } else {
            throw new IOException();
        }
        if (plusNumber.contains("+")) {
            differenceBlock.setCurrCommit(this.getCommitChunkPosition(plusNumber));
        } else {
            throw new IOException();
        }
    }

    private CommitChunkPosition getCommitChunkPosition(String decimalNumberStr) throws IOException {
        decimalNumberStr = decimalNumberStr.substring(1, decimalNumberStr.length());
        List<String> numbers = Arrays.asList(decimalNumberStr.split(","));
        CommitChunkPosition chunkPosition = new CommitChunkPositionImpl();
        if (numbers.size() == 2) {
            chunkPosition.setOffset(Integer.parseInt(numbers.get(0)));
            chunkPosition.setHeight(Integer.parseInt(numbers.get(1)));
        } else if (numbers.size() == 1) {
            chunkPosition.setOffset(0);
            chunkPosition.setHeight(Integer.parseInt(numbers.get(0)));
        } else {
            throw new IOException();
        }
        return chunkPosition;
    }

    private void parseFunctionNameInBlockSeparator(String line, DifferenceBlock differenceBlock) {
        int lastDogDog = line.lastIndexOf("@@");
        String functionName = line.substring(lastDogDog + 2, line.length());
        if (!functionName.equals("")) {
            differenceBlock.setFunctionName(functionName);
        }
    }
}
