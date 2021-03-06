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
            out.println("name=Alexey\n"
                    + "age=37\n"
                    + "hobby=travelling");
        }
        Config config = new Config(source.getAbsolutePath());
        config.load();
        assertThat(config.value("name"), is("Alexey"));
        assertThat(config.value("age"), is("37"));
        }

    @Test
    public void whenPairWithCommentsAndSpacelines() throws IOException {
        File source = folder.newFile("source.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("# this is first comment\n"
                    + "name=Alexey\n"
                    + "age=37\n"
                    + "hobby=travelling\n"
                    + "\n"
                    + "# second=comment\n"
                    + "programm.language=java\n"
                    + "years.learning=0.6\n"
                    + "bday=20.10\n"
                    + "\n");
        }
        Config config = new Config(source.getAbsolutePath());
        config.load();
        assertThat(config.value("programm.language"), is("java"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenStartsWithEquals() throws IOException {
        File source = folder.newFile("source.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("# this is first comment\n"
                    + "=name=Alexey\n"
                    + "age=37\n"
                    + "hobby=travelling");
        }
        Config config = new Config(source.getAbsolutePath());
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTwoEqualsInTheMiddleThrowsException() throws IOException {
        File source = folder.newFile("source.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("age==37");
        }
        Config config = new Config(source.getAbsolutePath());
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLineHasNoKeyThenThrowException() throws IOException {
        File source = folder.newFile("source.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("# this is first comment\n"
                    + "name=Alexey\n"
                    + "age=37\n"
                    + "=hobby_travelling");
        }
        Config config = new Config(source.getAbsolutePath());
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenEndsWithEquals() throws IOException {
        File source = folder.newFile("source.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("# this is first comment\n"
                    + "name=Alexey\n"
                    + "something_smart=\n"
                    + "hobby=travelling");
        }
        Config config = new Config(source.getAbsolutePath());
        config.load();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenKeyIsNotInTheMap() throws IOException {
        File source = folder.newFile("source.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("# this is first comment\n"
                    + "name=Alexey\n"
                    + "something=smart\n"
                    + "hobby=travelling");
        }
        Config config = new Config(source.getAbsolutePath());
        config.load();
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }
}