package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        System.out.println("Поговорим, кожаный мешок?");
        Scanner sc = new Scanner(System.in);
        boolean speak = true;
        //String input = new String();
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (speak) {
                System.out.println(readPhrases().get(new Random().nextInt(readPhrases().size())));
                if ("стоп".equals(input)) {
                    speak = !speak;
                }

            } else {
                if ("продожить".equals(input)) {
                    speak = !speak;
                }
            }
            if ("закончить".equals(input)) {
                break;
            }



            //String input = sc.nextLine();
//            if (speak) {
//                System.out.println(readPhrases().get(new Random().nextInt(readPhrases().size())));
//                input = sc.nextLine();
//            }
//            if ("стоп".equals(input) && speak) {
//                speak = false;
//                input = sc.nextLine();
//            }
//            if ("продолжить".equals(input)) {
//                System.out.println(readPhrases().get(new Random().nextInt(readPhrases().size())));
//                speak = true;
//                input = sc.nextLine();
//            }
//            if ("закончить".equals(input)) {
//                break;
//            }
        }


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

    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("cclog.txt", "answers.txt");
        cc.run();
    }
}