package io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor searcher = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), searcher);
        HashMap<FileProperty, List<Path>> map = searcher.getMap();
        for (FileProperty file : map.keySet()) {
            if (map.get(file).size() > 1) {
                map.get(file).forEach(System.out::println);
            }
        }
    }
}