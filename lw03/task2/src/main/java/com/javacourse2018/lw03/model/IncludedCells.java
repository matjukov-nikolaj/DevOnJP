package com.javacourse2018.lw03.model;

import java.util.List;
import java.util.Map;

public interface IncludedCells {

    List<Position> getCellsIncludedInTheFormula();

    void clearSellsIncludedInTheFormula();

    void addPositionToCellsIncludedInTheFormula(Position position);

    Map<Position, Position> getCellsWithFormula();

    void setCellsIncludedInTheFormula(List<Position> list);

}
