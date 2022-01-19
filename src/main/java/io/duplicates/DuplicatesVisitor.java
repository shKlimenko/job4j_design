package io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private HashMap<FileProperty, List<Path>> map = new HashMap<>();

    public  HashMap<FileProperty, List<Path>> getMap() {
        return map;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        map.putIfAbsent(fileProperty, new LinkedList<>());
        map.get(fileProperty).add(file);
        return FileVisitResult.CONTINUE;
    }
}
