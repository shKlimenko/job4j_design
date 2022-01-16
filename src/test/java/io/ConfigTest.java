package io;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Alexey"));
        assertThat(config.value("age"), is("37"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithCommentsAndSpacelines() {
        String path = "./data/pair_with_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("programm.language"), is("java"));
        assertThat(config.value("# second"), is(Matchers.nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidHyphen() {
        String path = "./data/pair_with_hyphen.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidEquals() {
        String path = "./data/pair_with_equals.properties";
        Config config = new Config(path);
        config.load();
    }
}