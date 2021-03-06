package com.javacourse2018.service;

import com.javacourse2018.model.CommitLine;
import com.javacourse2018.model.CommitLineStatus;
import com.javacourse2018.model.DifferenceBlock;

import java.io.IOException;
import java.util.List;

public class DifferenceBlockValidator {

    private List<DifferenceBlock> differenceBlocks;

    private List<String> mainFileLines;

    public DifferenceBlockValidator(List<DifferenceBlock> differenceBlocks,
                                    List<String> mainFileLines) {
        this.differenceBlocks = differenceBlocks;
        this.mainFileLines = mainFileLines;
    }

    public void check() throws IOException {
        for (DifferenceBlock block: differenceBlocks) {
            Integer difLineNumber = block.getCurrCommit().getOffset();
            for (CommitLine line: block.getCommitLines()) {
                if (line.getStatus() != CommitLineStatus.REMOVED) {
                    compareLines(difLineNumber, line);
                    difLineNumber++;
                }
            }
        }
    }

    private void compareLines(Integer difLineNumber, CommitLine line) throws IOException {
        if (mainFileLines.size() < difLineNumber) {
            throw new IOException();
        }
        String mainLine = mainFileLines.get(difLineNumber);
        String difLine = line.getPayload();
        if (difLine.length() > 0) {
            difLine = difLine.substring(1);
        }

        if (!mainLine.equals(difLine)) {
            throw new IOException();
        }
    }

}
