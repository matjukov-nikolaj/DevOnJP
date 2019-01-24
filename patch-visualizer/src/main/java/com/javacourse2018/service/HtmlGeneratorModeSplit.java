package com.javacourse2018.service;

import com.javacourse2018.model.CommitInfo;
import com.javacourse2018.model.CommitLineStatus;
import com.javacourse2018.model.GeneralCommitLine;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HtmlGeneratorModeSplit extends BaseHtmlGenerator<GeneralCommitLine> {
    private static final Log LOG = LogFactory.getLog(CommitLineGenerator.class);

    private List<GeneralCommitLine> tmpCurLines;
    private List<GeneralCommitLine> tmpPrevLines;


    public HtmlGeneratorModeSplit(String path, List<GeneralCommitLine> lines, CommitInfo commitInfo) {
        super(path, lines, commitInfo);

        this.tmpCurLines = new ArrayList<>();
        this.tmpPrevLines = new ArrayList<>();
        this.splitLinesByCommits();
    }

    private void splitLinesByCommits() {
        for (GeneralCommitLine line: lines) {
            if (line.getStatus() == CommitLineStatus.DEFAULT) {
                this.balanceCommits();
                tmpPrevLines.add(line);
                tmpCurLines.add(line);
            } else if (line.getStatus() == CommitLineStatus.REMOVED) {
                tmpPrevLines.add(line);
            } else if (line.getStatus() == CommitLineStatus.INSERTED) {
                tmpCurLines.add(line);
            } else {
                LOG.warn("This display mode is not available");
            }
        }
        this.balanceCommits();
    }

    private void balanceCommits() {
        while (tmpPrevLines.size() != tmpCurLines.size()) {
            GeneralCommitLine emptyLine = new GeneralCommitLine();
            emptyLine.setStatus(CommitLineStatus.DEFAULT);
            emptyLine.setPayload("");
            emptyLine.setIndex(-1);
            emptyLine.setPrevIndex(-1);
            if (tmpPrevLines.size() > tmpCurLines.size()) {
                tmpCurLines.add(emptyLine);
            } else {
                tmpPrevLines.add(emptyLine);
            }
        }
    }

    protected void printBody(BufferedWriter fr) {
        for (int i = 0; i < this.tmpCurLines.size(); ++i) {
            try {
                GeneralCommitLine curLine = this.tmpCurLines.get(i);
                GeneralCommitLine prevLine = this.tmpPrevLines.get(i);
                fr.write(TR);

                // prev index col
                fr.write(TD);
                String prevLineNumber = (prevLine.getPrevIndex() != -1) ? String.valueOf(prevLine.getPrevIndex()) : " ";
                fr.write(prevLineNumber);
                fr.write(TD_CLOSE);

                // prev index payload
                writePayload(fr, prevLine);

                // cur index col
                fr.write(TD);
                String curLineNumber = (curLine.getIndex() != -1) ? String.valueOf(curLine.getIndex()) : " ";
                fr.write(curLineNumber);
                fr.write(TD_CLOSE);

                // cur col payload
                writePayload(fr, curLine);

                fr.write(TR_CLOSE);
            } catch (IOException e) {
                LOG.error(e.fillInStackTrace());
            }
        }
    }
}
