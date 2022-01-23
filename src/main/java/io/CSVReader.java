package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    private static List<String> outList = new ArrayList<>();

    public static void handle(ArgsName argsName) throws Exception {
        Scanner scanner = new Scanner(Paths.get(argsName.get("path")).toFile());
        String input = scanner.nextLine();
        List<Integer> head = listOfFilterIndices(input, argsName);

        addSBToOutList(input, head, argsName);

        while (scanner.hasNext()) {
            addSBToOutList(scanner.nextLine(), head, argsName);
        }

        if (Objects.equals(argsName.get("out"), "stdout")) {
            outList.forEach(System.out::println);
        } else {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(Paths.get(argsName.get("out")).toFile()))) {
                for (String s : outList) {
                    bw.write(s + System.lineSeparator());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void addSBToOutList(String input, List<Integer> head, ArgsName argsName) {
        String[] splittedLine = input.split(argsName.get("delimiter"));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < splittedLine.length; i++) {
            if (head.contains(i)) {
                sb.append(splittedLine[i]);
                sb.append(";");
            }
        }
        outList.add(sb.substring(0, sb.length() - 1));
    }

    private static List<Integer> listOfFilterIndices(String line, ArgsName argsName) {
        List<Integer> list = new ArrayList<>();
        List<String> columnsList = Arrays.asList(argsName.get("filter").split(","));
        String[] splittedLine = line.split(argsName.get("delimiter"));
        for (int i = 0; i < splittedLine.length; i++) {
            if (columnsList.contains(splittedLine[i])) {
                list.add(i);
            }
        }
        return list;
    }

    private static boolean isFileAndCSV(String file) {
        return Paths.get(file).toFile().isFile() && file.endsWith(".csv");
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        if (args.length != 4 || !isFileAndCSV(argsName.get("path"))) {
            throw new IllegalArgumentException("Missing arguments. "
                    + "Use java -jar csvReader.jar PATH DELIMITER STDOUT COLUMNS");
        }
        handle(argsName);
    }
}
