package com.oneofever.functions;

import com.oneofever.Pair;
import com.oneofever.shapes.Shape;
import java.util.ArrayList;
import java.util.Comparator;

public class History {
    static ArrayList<Pair<String, Shape>> createdShapes = new ArrayList<Pair<String, Shape>>();

    private History() {}

    public static void add(String name, Shape toAdd) {
        createdShapes.add(new Pair<>(name, toAdd));
    }

    public static String[] getHistory() {
        return createdShapes.stream()
                .sorted(Comparator.comparingDouble(ó -> ó.b.getArea()))
                .map(ć -> ć.a + " " + ć.b.toString())
                .toArray(String[]::new);
    }
}
