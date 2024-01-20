package org.example.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 1. Задан файл с текстом, найти и вывести в консоль все слова, начинающиеся с гласной буквы.
 * 2. Задан файл с текстом, найти и вывести в консоль все слова,
 * для которых последняя буква одного слова совпадает с первой буквой следующего слова
 * 3. Задан файл с текстом. В каждой строке найти и вывести наибольшее число цифр, идущих подряд.
 * 4. Задан файл с java-кодом.
 * Прочитать текст программы из файла и все слова public в объявлении атрибутов и методов класса заменить на слово private.
 * Результат сохранить в другой заранее созданный файл.
 * 5. Задан файл с java-кодом.
 * Прочитать текст программы из файла и записать в другой файл в обратном порядке символы каждой строки.
 */


public class RunnerNIO {

    public static void main(String[] args) throws IOException {
        Path path1 = Path.of("src/main/resources/niotask/nio.txt");
        Path path2 = Path.of("src/main/resources/niotask/Java.java");

        List<String> strings1 = Files.readAllLines(path1);
        List<String> strings2 = Files.readAllLines(path2);

        task1(strings1);
        task2(strings1);
        task3(strings1);
        task4(strings2);
        task5(strings2);

    }

    private static void task1(List<String> strings) {
        System.out.println("\ntask1");
        System.out.println("Все слова, начинающиеся с гласной буквы:");
        strings.stream()
                .flatMap(s -> Arrays.stream(s.split("\\W")))
                .filter(s -> s.matches("^(?i:[aeiouy]).*"))
                .forEach(System.out::println);
    }

    private static void task2(List<String> strings) {
        System.out.println();
        System.out.println("\ntask2");
        List<String> words = strings.stream()
                .flatMap(s -> Arrays.stream(s.split("\\W")))
                .filter(s -> !s.isEmpty())
                .toList();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.size() - 1; i++) {
            if (words.get(i).charAt(words.get(i).length() - 1) == words.get(i + 1).charAt(0)) {
                sb.append(words.get(i));
                sb.append(", ");
                sb.append(words.get(i + 1));
                sb.append("; \n");
            }
        }
        System.out.println("Пары слов, в которых последняя буква одного слова совпадает с первой буквой следующего слова:");
        System.out.println(sb);
    }

    private static void task3(List<String> strings) {
        System.out.println("\ntask3");
        for (int i = 0; i < strings.size(); i++) {
            int max = 0;
            int current = 0;

            for (String c : strings.get(i).split("")) {
                if (c.matches("\\d")) {
                    current += 1;
                } else {
                    if (max < current) {
                        max = current;
                    }
                    current = 0;
                }
            }
            System.out.println("В строке " + i + " наибольшее число цифр, идущих подряд: " + max);
        }
    }

    private static void task4(List<String> strings) throws IOException {
        Path pathOut = Path.of("src/main/resources/niotask/JavaOut1.java");

        try (RandomAccessFile file = new RandomAccessFile(pathOut.toFile(), "rw");
             FileChannel channel = file.getChannel()) {
            ByteBuffer buffer;
            for (String line : strings) {
                String newLine = Arrays.stream(line.split("\\s"))
                        .map(s -> {
                            if (s.matches("public")) return "private";
                            return s;
                        })
                        .collect(Collectors.joining(" ", "", "\n"));

                buffer = ByteBuffer.wrap(newLine.getBytes());
                channel.write(buffer);
            }

        }
        System.out.println("\ntask4 is done.");
    }

    private static void task5(List<String> strings) throws IOException {
        Path pathOut = Path.of("src/main/resources/niotask/JavaOut2.java");

        try (RandomAccessFile file = new RandomAccessFile(pathOut.toFile(), "rw");
             FileChannel channel = file.getChannel()) {
            ByteBuffer buffer;
            for (String line : strings) {
                StringBuilder sb = new StringBuilder(line);
                sb.append("\n");
                String rewert = sb.reverse().toString();

                buffer = ByteBuffer.wrap(rewert.getBytes());
                channel.write(buffer);
            }

        }
        System.out.println("\ntask5 is done.");
    }
}
