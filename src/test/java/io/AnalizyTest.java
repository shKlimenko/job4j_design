package io;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenPairWithoutComment() throws IOException {
        File source = folder.newFile("log.csv");
        File target =  folder.newFile("unavailable.csv");
        try (PrintWriter printOut = new PrintWriter(source)) {
            printOut.println("200 10:56:01\n"
                    + "500 10:57:01\n"
                    + "400 10:58:01\n"
                    + "500 10:59:01\n"
                    + "400 11:01:02\n"
                    + "200 11:02:02");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader readerIn = new BufferedReader(new FileReader(target))) {
            readerIn.lines().forEach(rsl :: append);
        }
        assertThat(rsl.toString(), is("10:57:01;11:02:02"));

    }

}