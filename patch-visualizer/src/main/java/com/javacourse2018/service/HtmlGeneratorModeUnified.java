package com.javacourse2018.service;

import com.javacourse2018.model.CommitInfo;
import com.javacourse2018.model.GeneralCommitLine;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlGeneratorModeUnified extends BaseHtmlGenerator<GeneralCommitLine> {

    public HtmlGeneratorModeUnified(String path, List<GeneralCommitLine> lines, CommitInfo commitInfo) {
        super(path, lines, commitInfo);
    }

    protected void printBody(BufferedWriter out) {
        for (GeneralCommitLine commitLine: this.lines) {
            try {
                String lineNumber = "";
                out.write(TR);

                //prev index col
                out.write(TD);
                lineNumber = (commitLine.getPrevIndex() != -1) ? String.valueOf(commitLine.getPrevIndex()) : " ";
                out.write(lineNumber);
                out.write(TD_CLOSE);

                //curr index col
                out.write(TD);
                lineNumber = (commitLine.getIndex() != -1) ? String.valueOf(commitLine.getIndex()) : " ";
                out.write(lineNumber);
                out.write(TD_CLOSE);

                //payload col
                this.writePayload(out, commitLine);

                out.write(TR_CLOSE);
            } catch (IOException e) {
                LOG.error(e.fillInStackTrace());
            }
        }
    }

}
