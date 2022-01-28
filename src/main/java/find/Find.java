package find;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Find {
    private static void find(List<Path> sources, File target) {

    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        if (args.length != 4 || Paths.get(argsName.get("d")).toFile().isFile()) {
            throw new IllegalArgumentException("Missing arguments. Use "
                    + "java -jar pack.jar ROOT_FOLDER EXCLUDED_FILE MASK OUTPUT_FILE");
        }

        List<Path> sources = Search.search(Paths.get(argsName.get("d")),
                p -> !p.toFile().getName().endsWith(argsName.get("e")));

        find(sources, Paths.get(argsName.get("o")).toFile());
    }
}
