package io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    public final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (this.values.get(key) == null) {
            throw new IllegalArgumentException("This key is not in the map yet. Please use a valid key!");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            if (!arg.startsWith("-") || arg.matches(".*=$") || arg.matches("^=.*")) {
                throw new IllegalArgumentException("Wrong templates. Please use valid templates!");
            }
            String[] splittedArg = arg.split("=", 2);
            if (splittedArg.length == 2) {
                this.values.put(splittedArg[0].substring(1), splittedArg[1]);
            } else {
                throw new IllegalArgumentException("Something went wrong");
            }
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip"});
        System.out.println(zip.get("out"));
    }
}