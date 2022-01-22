package io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                                        new BufferedOutputStream(
                                                new FileOutputStream(target)))) {
            for (Path sPath : sources) {
                zip.putNextEntry(new ZipEntry(sPath.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(sPath.toFile().getPath()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                                        new BufferedOutputStream(
                                             new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        if (args.length != 3 || Paths.get(argsName.get("d")).toFile().isFile()) {
            throw new IllegalArgumentException("Missing arguments. Use "
                    + "java -jar pack.jar ROOT_FOLDER EXCLUDED_FILE OUTPUT_FILE");
        }

        List<Path> sources = Search.search(Paths.get(argsName.get("d")),
                p -> !p.toFile().getName().endsWith(argsName.get("e")));

        packFiles(sources, Paths.get(argsName.get("o")).toFile());
    }
}