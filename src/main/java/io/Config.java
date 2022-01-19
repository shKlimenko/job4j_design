package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    public final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            String line;
            while ((line = read.readLine()) != null) {
                if (line.matches("^=.*") || line.matches(".*=$") || line.matches(".*={2,}.*")) {
                    throw new IllegalArgumentException("Wrong templates. Please use valid templates!");
                }
                if (line.startsWith("#") || line.isEmpty()) {
                    continue;
                }
                String[] splittedLine = line.split("=");
                if (splittedLine.length == 2) {
                    this.values.put(splittedLine[0], splittedLine[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        if (this.values.get(key) == null) {
            throw new UnsupportedOperationException("This key is not in the map yet. Please use a valid key!");
        }
        return this.values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}