package com.javacourse2018.service;

import com.javacourse2018.model.CommitInfo;
import com.javacourse2018.model.DisplayMode;
import com.javacourse2018.model.GeneralCommitLine;
import com.javacourse2018.model.Lines;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class HtmlGenerator implements Generator, Lines {
    private static final Log LOG = LogFactory.getLog(CommitLineGenerator.class);

    private DisplayMode mode;
    private List<GeneralCommitLine> lines;
    private String outFilePath;
    private CommitInfo commitInfo;

    public HtmlGenerator(DisplayMode mode,
                         List<GeneralCommitLine> lines,
                         String outFilePath,
                         CommitInfo commitInfo) {
        this.mode = mode;
        this.commitInfo = commitInfo;
        this.lines = lines;
        this.outFilePath = outFilePath;
    }

    @Override
    public void setLines(List<GeneralCommitLine> lines) {
        this.lines = lines;
    }

    @Override
    public List<GeneralCommitLine> getLines() {
        return lines;
    }

    public void generate() {
        switch (this.mode) {
            case SPLIT:
                HtmlGeneratorModeSplit generatorSplit = new HtmlGeneratorModeSplit(this.outFilePath, this.lines, this.commitInfo);
                generatorSplit.generate();
                break;
            case UNIFIED:
                HtmlGeneratorModeUnified generatorUnified = new HtmlGeneratorModeUnified(this.outFilePath, this.lines, this.commitInfo);
                generatorUnified.generate();
                break;
            default:
                LOG.warn("This display mode is not available");
                break;
        }
    }


}
