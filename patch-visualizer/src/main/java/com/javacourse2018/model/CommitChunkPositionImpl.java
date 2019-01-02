package com.javacourse2018.model;

public class CommitChunkPositionImpl implements CommitChunkPosition {

    private Integer offset;
    private Integer height;

    @Override
    public Integer getHeight() {
        return height;
    }

    @Override
    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public Integer getOffset() {
        return offset;
    }

    @Override
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
