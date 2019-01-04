package com.javacourse2018.service;

import com.javacourse2018.model.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public abstract class BaseGenerator<T extends CommitLine> implements Lines, Generator {

    protected static final Log LOG = LogFactory.getLog(BaseGenerator.class);

    protected static final String HTML_HEADER = "<!doctype html>" +
            "<html lang=\"en\">" +
            "  <head>" +
            "    <!-- Required meta tags -->" +
            "    <meta charset=\"utf-8\">" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">" +
            "" +
            "    <!-- Bootstrap CSS -->" +
            "    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">" +
            "" +
            "    <title>Hello, world!</title>" +
            "<style type=\"text/css\">" +
            "code {" +
            "white-space: pre;" +
            "color: black" +
            "}" +
            "</style>" +
            "  </head>" +
            "    <body>";

    protected static final String TABLE_PREFIX = "      <table class=\"table\">" +
            "        <thead class=\"thead-dark\">" +
            "        </thead>" +
            "        <tbody>";

    protected static final String HTML_FOOTER = "</tbody>" +
            "      </table>" +
            "" +
            "    <!-- Optional JavaScript -->" +
            "    <!-- jQuery first, then Popper.js, then Bootstrap JS -->" +
            "    <script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>" +
            "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js\" integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q\" crossorigin=\"anonymous\"></script>" +
            "    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js\" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\" crossorigin=\"anonymous\"></script>" +
            "  </body>" +
            "</html>";

    protected static final String TR = "<tr>";
    protected static final String TR_CLOSE = "</tr>";
    protected static final String TD_SUCCESS = "<td class=\"table-success\">";
    protected static final String TD_DANGEROUS = "<td class=\"table-danger\">";
    protected static final String TD = "<td>";
    protected static final String TD_CLOSE = "</td>";
    protected static final String CODE = "<code>";
    protected static final String CODE_CLOSE = "</code>";
    protected static final String H3 = "<h3>";
    protected static final String H3_CLOSE = "</h3>";
    private static final String FILE_NAME = "File name: ";

    protected String htmlPath;

    protected List<GeneralCommitLine> lines;

    protected CommitInfo commitInfo;

    public void setLines(List<GeneralCommitLine> lines) {
        this.lines = lines;
    }

    public List<GeneralCommitLine> getLines() {
        return lines;
    }

    public CommitInfo getCommitInfo() {
        return commitInfo;
    }

    public void setCommitInfo(CommitInfo commitInfo) {
        this.commitInfo = commitInfo;
    }

    public BaseGenerator(String path, List<GeneralCommitLine> lines, CommitInfo commitInfo) {
        this.htmlPath = path;
        this.lines = lines;
        this.commitInfo = commitInfo;
    }

    public void generate() {
        if (this.lines.isEmpty() && this.commitInfo == null) {
            LOG.warn("Empty lines and commit info");
            return;
        }
        File file = new File(this.htmlPath);
        FileWriter out = null;
        try {
            out = new FileWriter(file);
            out.write(HTML_HEADER);
            this.printCommitInformation(out);
            out.write(TABLE_PREFIX);
            this.printBody(out);
            out.write(HTML_FOOTER);
        } catch (IOException e) {
            LOG.error(e.fillInStackTrace());
        }finally{
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                LOG.error(e.fillInStackTrace());
            }
        }
    }

    protected void writePayload(FileWriter out, T line) throws IOException {
        if (line.getStatus() == CommitLineStatus.INSERTED) {
            out.write(TD_SUCCESS);
        } else if (line.getStatus() == CommitLineStatus.REMOVED) {
            out.write(TD_DANGEROUS);
        } else if (line.getStatus() == CommitLineStatus.DEFAULT) {
            out.write(TD);
        } else {
            LOG.warn("Unhandled CommitLineStatus.");
            out.write(TD);
        }
        out.write(CODE);
        out.write(line.getPayload());
        out.write(CODE_CLOSE);
        out.write(TD_CLOSE);
    }

    protected abstract void printBody(FileWriter out);

    private void printCommitInformation(FileWriter out) throws IOException {
        out.write(H3);
        out.write(this.commitInfo.getAuthor().getName());
        out.write(H3_CLOSE);

        out.write(H3);
        out.write(this.commitInfo.getDate());
        out.write(H3_CLOSE);

        out.write(H3);
        out.write(this.commitInfo.getSubject());
        out.write(H3_CLOSE);

        out.write(H3);
        out.write(FILE_NAME + this.commitInfo.getFileName());
        out.write(H3_CLOSE);
    }


}
