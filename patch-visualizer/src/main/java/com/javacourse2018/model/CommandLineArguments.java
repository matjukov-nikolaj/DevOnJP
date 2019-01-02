package com.javacourse2018.model;

public class CommandLineArguments implements Arguments {

    private String file;
    private String patchFile;
    private String outFile;
    private DisplayMode mode;

    @Override
    public DisplayMode getDisplayMode() {
        return mode;
    }

    @Override
    public void setDisplayMode(DisplayMode mode) {
        this.mode = mode;
    }

    @Override
    public String getFile() {
        return file;
    }

    @Override
    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public String getOutFile() {
        return outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }

    @Override
    public String getPatchFile() {
        return patchFile;
    }

    @Override
    public void setPatchFile(String patchFile) {
        this.patchFile = patchFile;
    }
}
