package find;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Find {

    private static void check(String[] args, ArgsName argsName) {
        if (args.length != 4 || Paths.get(argsName.get("d")).toFile().isFile()) {
            throw new IllegalArgumentException("Missing arguments. Use "
                    + "java -jar pack.jar ROOT_FOLDER REQUIRED_FILE SEARCH_TYPE OUTPUT_FILE");
        }
        if (!(Objects.equals(argsName.get("t"), "mask") || Objects.equals(argsName.get("t"), "name")
                || Objects.equals(argsName.get("t"), "regex"))) {
            throw new IllegalArgumentException("Use right type of search. "
                    + "\"-t\" should be equals to: mask, name or regex");
        }
    }

    private static Predicate<Path> typeOfSearch(ArgsName argsName) {
        Predicate<Path> predicate = p -> p.toFile()
                .getName()
                .matches(argsName.get("n"));

        if (Objects.equals(argsName.get("t"), "mask")) {
            String regex = argsName.get("n")
                    .replace("?", ".?")
                    .replace("*", ".*?");
            predicate = p -> p.toFile()
                    .getName()
                    .matches(regex);
        }

        if (Objects.equals(argsName.get("t"), "name")) {
            predicate = p -> p.toFile()
                    .getName()
                    .equalsIgnoreCase(argsName.get("n"));
        }
        return predicate;
    }

    private static void writeToFile(List<Path> sources, File target) throws IOException {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            for (Path s : sources) {
                out.println(s);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        check(args, argsName);

        List<Path> sources = Search.search(Paths.get(argsName.get("d")), typeOfSearch(argsName));

        writeToFile(sources, Paths.get(argsName.get("o")).toFile());
    }
}