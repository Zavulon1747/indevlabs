package org.example;

import java.util.IntSummaryStatistics;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    /*
    Для поиска буду использовать формулу суммы любой натуральной последовательности: среднее арифметическое самого
    малого и самого большого чисел, умноженное на количество чисел последовательности. То, насколько реальная сумма
    чисел в массиве будет меньше ожидаемой, подскажет, какого числа не хватает.

    Метод работает исходя из условий, что входной массив состоит только из натуральных чисел, имеет лишь один пропуск и
    все числа умещаются в диапазон int.
    */
    public static int findMissingNumber(int[] array) {
        /*
        Из условий ясно, что ряд чисел может начинаться не с 0, следовательно, нужно найти, с какого же числа он
        начинается и каким заканчивается. Сортировки всех видов займут больше времени, чем просто поиск максимума и
        минимума, поэтому их не рассматриваем. Дальше есть три варианта работы: с чистым массивом, с коллекцией или со
        стримом. Коллекция позволит нам сэкономить во времени, но может работать только с объектами (Integer против int),
        что затратно по памяти. Массив же может работать с примитивами, но обяжет вручную перебрать все объекты, чтобы
        найти максимум и минимум, а также их сумму. IntStream же позволит нам работать с примитивами и имеет все
        необходимые методы.
         */
        IntSummaryStatistics stats = IntStream.of(array).summaryStatistics();

        //Для читаемости кода заведем несколько переменных для важных элементов расчета
        int max = stats.getMax();
        int min = stats.getMin();
        int count = (int)stats.getCount() + 1; //Добавляю 1, т.к. количество чисел на одно меньше, чем должно быть
        long sum = stats.getSum();
        long expectedSum = (max + min) * count / 2;
        int missingNumber = (int)(expectedSum - sum);

        return missingNumber;
    }

    public static void main(String[] args) {
        /*
        При решении полагаюсь на корректность входных данных, но можно также добавить проверки:
        - Элементов в массиве не меньше двух
        - Все элементы - натуральные числа либо 0
        - Все элементы умещаются в int
        - Пропуск числа есть и только один
         */
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scan.nextInt();
        }

        System.out.println(findMissingNumber(array));
    }
}