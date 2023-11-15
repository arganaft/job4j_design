package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> phrases = new ArrayList<>();
        List<String> log = new ArrayList<>();
        readPhrases(phrases);
        String answer;
        Random random = new Random();
        Scanner in = new Scanner(System.in);
        byte flag = 1;
        String question;
        while (true) {
            System.out.println("Введите свой вопрос...");
            question = in.nextLine();
            log.add("Вопрос:  -  " + question);
            if (question.equals(OUT)) {
                answer = "Запись истории диалога в файл --> " + path;
                System.out.println(answer);
                log.add("Ответ:  -  " + answer);
                saveLog(log);
                break;
            }
            if (question.equals(STOP)) {
                flag = 2;
                continue;
            }
            if (question.equals(CONTINUE)) {
                flag = 1;
                continue;
            }
            if (flag == 1) {
                answer = phrases.get(random.nextInt(phrases.size() - 1));
                System.out.println(answer);
                log.add("Ответ:  -  " + answer);
            }
        }

    }

    private void readPhrases(List<String> phrases) {
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, Charset.forName("UTF-8")))) {
            for (String line : log) {
                pw.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/LogOfDialogue.txt", "data/botAnswers.txt");
        cc.run();
    }
}