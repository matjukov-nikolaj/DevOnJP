package com.javacourse2018.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainFileReader {
    private static final Log LOG = LogFactory.getLog(MainFileReader.class);

    private List<String> lines;
    private String mainFilePath;

    public MainFileReader(String mainFilePath) {
        this.mainFilePath = mainFilePath;
        this.lines = new ArrayList<>();
    }

    public void read() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.mainFilePath))) {
            String line;
            // Lines index starts with '1'
            this.lines.add("");
            while ((line = br.readLine()) != null) {
                this.lines.add(line);
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    public String getMainFilePath() {
        return mainFilePath;
    }

    public void setMainFilePath(String mainFilePath) {
        this.mainFilePath = mainFilePath;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }
}
