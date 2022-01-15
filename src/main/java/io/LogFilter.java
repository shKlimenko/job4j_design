package io;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader log = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = log.readLine()) != null) {
                String[] splittedLine = line.split(" ");
                if (Objects.equals(Integer.parseInt(splittedLine[splittedLine.length - 2]), 404)) {
                    list.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void save(List<String> log, String file) {
        Iterator<String> logIter = log.listIterator();
        try(PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            while (logIter.hasNext()) {
                out.println(logIter.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}