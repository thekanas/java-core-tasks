package org.example.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей
 *    к большей).
 *
 * 2. Вывести список неповторяющихся городов, в которых работают трейдеры.
 *
 * 3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.
 *
 * 4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном
 *    порядке.
 *
 * 5. Выяснить, существует ли хоть один трейдер из Милана.
 *
 * 6. Вывести суммы всех транзакций трейдеров из Кембриджа.
 *
 * 7. Какова максимальная сумма среди всех транзакций?
 *
 * 8. Найти транзакцию с минимальной суммой.
 */


public class PuttingIntoPractice {
    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


        //task1
        transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .forEach(System.out::println);

        //task2
        transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);

        //task3
        transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(t -> t.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);

        //task4
        String collect = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .map(Trader::getName)
                .sorted()
                .collect(Collectors.joining(", "));
        System.out.println(collect);

        //task5
        boolean milan = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .anyMatch(t -> t.getCity().equals("Milan"));
        System.out.println(milan);

        //task6
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        //task7
        int max = transactions.stream()
                .mapToInt(Transaction::getValue)
                .max().orElse(0);
        System.out.println("max: " + max);

        //task8
        int min = transactions.stream()
                .mapToInt(Transaction::getValue)
                .min().orElse(0);
        System.out.println("min: " + min);


    }
}
