package by.it.group973601.apenka.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: рюкзак без повторов

Первая строка входа содержит целые числа
    1<=W<=100000     вместимость рюкзака
    1<=n<=300        число золотых слитков
                    (каждый можно использовать только один раз).
Следующая строка содержит n целых чисел, задающих веса каждого из слитков:
  0<=w[1]<=100000 ,..., 0<=w[n]<=100000

Найдите методами динамического программирования
максимальный вес золота, который можно унести в рюкзаке.

Sample Input:
10 3
1 4 8
Sample Output:
9

*/

public class B_Knapsack {

    int getMaxWeight(InputStream stream) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        Scanner scanner = new Scanner(stream);
        int capacity = scanner.nextInt();
        int itemsCount = scanner.nextInt();
        int goldWeights[] = new int[itemsCount];
        for (int i = 0; i < itemsCount; i++) {
            goldWeights[i] = scanner.nextInt();
        }
        int result = 0;

        int[][] knapsack = new int[itemsCount + 1][capacity + 1];
        for (int i = 0; i <= capacity; i++) {
            knapsack[0][i] = 0;
        }
        for (int goldbar = 1; goldbar <= itemsCount; goldbar++) {
            for (int currentCapacity = 0; currentCapacity <= capacity; currentCapacity++) {
                if (currentCapacity > goldWeights[goldbar - 1] &&
                        (knapsack[goldbar - 1][currentCapacity - goldWeights[goldbar - 1]] + goldWeights[goldbar - 1] > knapsack[goldbar][currentCapacity])) {
                    knapsack[goldbar][currentCapacity] = knapsack[goldbar - 1][currentCapacity - goldWeights[goldbar - 1]] + goldWeights[goldbar - 1];
                } else {
                    knapsack[goldbar][currentCapacity] = knapsack[goldbar - 1][currentCapacity];
                }
            }
        }

        result = knapsack[itemsCount][capacity];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res = instance.getMaxWeight(stream);
        System.out.println(res);
    }

}
