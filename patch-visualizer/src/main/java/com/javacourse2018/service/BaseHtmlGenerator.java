package com.javacourse2018.service;

import com.javacourse2018.model.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseHtmlGenerator<T extends CommitLine> implements Lines, Generator {

    protected static final Log LOG = LogFactory.getLog(BaseHtmlGenerator.class);

    protected static final String TEMPLATE_PATH = "\\src\\main\\resources\\template.txt";

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

    public BaseHtmlGenerator(String path, List<GeneralCommitLine> lines, CommitInfo commitInfo) {
        this.htmlPath = path;
        this.lines = lines;
        this.commitInfo = commitInfo;
    }

    public void generate() {
        if (this.lines.isEmpty() && this.commitInfo == null) {
            LOG.warn("Empty lines and commit info");
            return;
        }

        FileWriter out = null;

        try (BufferedWriter w = new BufferedWriter(new FileWriter(new File(this.htmlPath)))) {
            List<String> htmlParts = this.readHtmlTemplate();
            if (htmlParts.size() != 3) {
                LOG.error("Error while reading template file");
            }
            w.write(htmlParts.get(0));
            this.printCommitInformation(w);
            w.write(htmlParts.get(1));
            this.printBody(w);
            w.write(htmlParts.get(2));
        } catch (IOException e) {
            LOG.error(e.fillInStackTrace());
        }
    }

    protected void writePayload(BufferedWriter out, T line) throws IOException {
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

    protected abstract void printBody(BufferedWriter out);

    private void printCommitInformation(BufferedWriter out) throws IOException {
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

    private List<String> readHtmlTemplate() throws IOException {
        Path currentRelativePath = Paths.get("");
        String projectPath = currentRelativePath.toAbsolutePath().toString();
        String filePath = projectPath + TEMPLATE_PATH;
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String st;
            while ((st = br.readLine()) != null) {
                lines.add(st);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return lines;
    }

}
