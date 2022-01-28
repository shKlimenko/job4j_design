package find;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Missing arguments. "
                    + "Usage java -jar search.jar ROOT_FOLDER FILE_EXTENSION");
        }
        Path start = Paths.get(args[0]);
        if (!start.toFile().exists() || start.toFile().isFile()) {
            throw new IllegalArgumentException("ROOT_FOLDER is not exist or not Directory");
        }
        search(start, p -> p.toFile().getName().endsWith(args[1]))
                .forEach(el -> System.out.println("File name: " + el.getFileName()
                        + " - File size: " + el.toFile().length() + " byte"));
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}