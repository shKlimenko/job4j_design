package io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        String botPhrase;
        String dialog = CONTINUE;
        List<String> log = new ArrayList<>();
        System.out.println("Поговорим, кожаный мешок?");
        Scanner sc = new Scanner(System.in);
        while (!OUT.equals(dialog)) {
            String input = sc.nextLine();
            switch (input) {
                case OUT:
                    log.add(input);
                    dialog = OUT;
                    break;
                case STOP:
                    log.add(input);
                    dialog = STOP;
                    break;
                case CONTINUE:
                    log.add(CONTINUE);
                    dialog = CONTINUE;
                    botPhrase = readPhrases().get(new Random().nextInt(readPhrases().size()));
                    System.out.println(botPhrase);
                    log.add(botPhrase);
                    break;
                default:
                    if (CONTINUE.equals(dialog)) {
                        botPhrase = readPhrases().get(new Random().nextInt(readPhrases().size()));
                        System.out.println(botPhrase);
                        log.add(input);
                        log.add(botPhrase);
                    } else {
                        log.add(input);
                    }
                    break;
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.botAnswers))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(this.path, Charset.forName("WINDOWS-1251"), true)
        )) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/cclog.txt", "data/answers.txt");
        cc.run();
    }
}