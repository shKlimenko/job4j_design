package io;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class SearchTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenSearchCSV() throws IOException {
        File fileTxt = folder.newFile("source.txt");
        File fileCsv = folder.newFile("log.csv");
        File fileJpg = folder.newFile("image.jpg");
        List<Path> pathList = Search.search(folder.getRoot().toPath(), p -> p.toFile().getName().endsWith(".csv"));
        assertTrue(pathList.contains(fileCsv.toPath()));
        assertFalse(pathList.contains(fileTxt.toPath()));
    }
}