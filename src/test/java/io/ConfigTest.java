package io;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenPairWithoutComment() throws IOException {
        File source = folder.newFile("source.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("name=Alexey\nage=37\nhobby=travelling");
        }
        Config config = new Config(source.getAbsolutePath());
        config.load();
        assertThat(config.value("name"), is("Alexey"));
        assertThat(config.value("age"), is("37"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithCommentsAndSpacelines() throws IOException {
        File source = folder.newFile("source.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("# this is first comment\nname=Alexey\nage=37\nhobby=travelling\n"
                    + "\n# second=comment\nprogramm.language=java\nyears.learning=0.6\nbday==20.10\n"
                    + "\n#third = comment\nhouse=of.the.rising.sun\nold.town=road");
        }
        Config config = new Config(source.getAbsolutePath());
        config.load();
        assertThat(config.value("programm.language"), is("java"));
        assertThat(config.value("# second"), is(Matchers.nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidHyphen() throws IOException {
        File source = folder.newFile("source.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("# this is first comment\n"
                    + "-name=Alexey\n"
                    + "age=37\n"
                    + "hobby=travelling");
        }
        Config config = new Config(source.getAbsolutePath());
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidEquals() throws IOException {
        File source = folder.newFile("source.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("# this is first comment\n"
                    + "name=Alexey\n"
                    + "=something_smart\n"
                    + "hobby=travelling");
        }
        Config config = new Config(source.getAbsolutePath());
        config.load();
    }
}