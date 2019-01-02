package com.javacourse2018.service;

import com.javacourse2018.model.CommitInfo;
import com.javacourse2018.model.DifferenceBlock;

import java.util.List;

public interface PatchFileParser {

    List<DifferenceBlock> getBlocks();

    void setBlocks(List<DifferenceBlock> blocks);

    CommitInfo getCommitInfo();

    void setCommitInfo(CommitInfo userInfo);

    public void parse();

}
