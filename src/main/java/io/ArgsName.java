package io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (this.values.get(key) == null) {
            throw new IllegalArgumentException("This key is not in the map yet. Please use a valid key!");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            check(arg);
            String[] splittedArg = arg.split("=", 2);
            String key = splittedArg[0].substring(1);
            if (splittedArg.length == 2 && !key.isEmpty() && !splittedArg[1].isEmpty()) {
                this.values.put(key, splittedArg[1]);
            } else {
                throw new IllegalArgumentException("Mismatch pattern");
            }
        }
    }

    private void check(String arg) {
        if (!arg.startsWith("-") && !arg.contains("=")) {
            throw new IllegalArgumentException("Wrong templates. Please use valid templates!");
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