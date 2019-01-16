package com.javacourse2018.service;

import com.javacourse2018.model.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

public class CommitLineGenerator implements Lines, Generator {
    private static final Log LOG = LogFactory.getLog(CommitLineGenerator.class);

    private List<GeneralCommitLine> lines;
    private List<String> mainFileLines;
    private List<DifferenceBlock> differenceBlocks;

    private int mainFileIndex;
    private int prevIndex;
    private int currIndex;

    public CommitLineGenerator(List<String> mainFileLines, List<DifferenceBlock> differenceBlocks) {
        this.differenceBlocks = differenceBlocks;
        this.mainFileLines = mainFileLines;
        this.lines = new ArrayList<>();
        this.mainFileIndex = 1;
        this.prevIndex = 1;
        this.currIndex = 1;
    }

    public void setLines(List<GeneralCommitLine> lines) {
        this.lines = lines;
    }

    public List<GeneralCommitLine> getLines() {
        return lines;
    }

    public void generate() {
        for (DifferenceBlock block: this.differenceBlocks) {
            this.copyDefaultLines(block.getCurrCommit().getOffset());

            for (CommitLine rawLine: block.getCommitLines()) {
                handleRawLine(rawLine);
            }
            CommitChunkPosition position = block.getCurrCommit();
            if (position.getHeight() == 0 || position.getOffset() == 0) {
                this.mainFileIndex = position.getHeight() + position.getOffset() + 1;
            } else {
                this.mainFileIndex = position.getHeight() + position.getOffset();
            }
        }
        this.copyDefaultLines(mainFileLines.size());
    }

    private void copyDefaultLines(int offset) {
        for (int i = this.mainFileIndex; i < offset; i++) {
            GeneralCommitLine line = new GeneralCommitLine();
            line.setPrevIndex(this.prevIndex++);
            line.setIndex(this.currIndex++);
            line.setPayload(this.mainFileLines.get(i));
            line.setStatus(CommitLineStatus.DEFAULT);

            this.lines.add(line);
        }
    }

    private void handleRawLine(CommitLine line) {
        GeneralCommitLine newLine = new GeneralCommitLine();
        newLine.setPayload(line.getPayload());
        newLine.setPrevIndex(-1);
        newLine.setIndex(-1);

        if (line.getStatus() == CommitLineStatus.DEFAULT) {
            newLine.setStatus(CommitLineStatus.DEFAULT);
            newLine.setIndex(this.currIndex++);
            newLine.setPrevIndex(this.prevIndex++);
        } else if (line.getStatus() == CommitLineStatus.INSERTED) {
            newLine.setStatus(CommitLineStatus.INSERTED);
            newLine.setIndex(this.currIndex++);
        } else if (line.getStatus() == CommitLineStatus.REMOVED) {
            newLine.setStatus(CommitLineStatus.REMOVED);
            newLine.setPrevIndex(this.prevIndex++);
        } else {
            LOG.warn("Unhandled CommitLineStatus");
        }
        this.lines.add(newLine);
    }
}
