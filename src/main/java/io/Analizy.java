package io;

import java.io.*;
import java.util.List;

public class Analizy {
    private final List<String> unavailable = List.of("400", "500");

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String line;
            boolean flag = true;
            while ((line = in.readLine()) != null) {
                String[] splittedLine = line.split(" ");
                if (unavailable.contains(splittedLine[0]) && flag) {
                    out.print(splittedLine[1] + ";");
                    flag = !flag;
                }
                if (!unavailable.contains(splittedLine[0]) && !flag) {
                    out.println(splittedLine[1]);
                    flag = !flag;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("log.csv", "unavailable.csv");
    }
}