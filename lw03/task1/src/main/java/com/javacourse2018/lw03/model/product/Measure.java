package com.javacourse2018.lw03.model.product;

import java.util.HashMap;
import java.util.Map;

public enum Measure {

    KG,
    G,
    PIECE,
    LITER,
    ML;

    public static String measureToString(Measure measure) {
        Map<Measure, String> measures = new HashMap<>();
        measures.put(KG, "Kilogram");
        measures.put(G, "Gram");
        measures.put(PIECE, "Piece");
        measures.put(LITER, "Liter");
        measures.put(ML, "Milliliter");
        return measures.get(measure);
    }

}
