package by.it.group973603.zemnov.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Итерационно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class B_EditDist {


    int getDistanceEdinting(String one, String two) {
        return editDist(one, two);
    }

    private int editDist(String one, String two) {
        int[] distance_h = new int[two.length() + 1];
        int[] distance = new int[two.length() + 1];

        for (int j = 0; j <= two.length(); j++) {
            distance[j] = j;
        }

        for (int i = 1; i <= one.length(); i++) {
            System.arraycopy(distance, 0, distance_h, 0, distance_h.length);

            distance[0] = i;
            for (int j = 1; j <= two.length(); j++) {
                int cost = (one.charAt(i - 1) != two.charAt(j - 1)) ? 1 : 0;
                distance[j] = min(
                        distance_h[j] + 1,
                        distance[j - 1] + 1,
                        distance_h[j - 1] + cost);
            }
        }
        return distance[distance.length - 1];
    }

    private int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group973603/zemnov/lesson07/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}