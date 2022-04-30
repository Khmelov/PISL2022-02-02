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
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

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

public class A_EditDist {

    private char[] firstString;
    private char[] secondString;

    int getDistanceEdinting(String one, String two) {
        firstString = one.toCharArray();
        secondString = two.toCharArray();
        return editDist(one.length(), two.length());
    }

    private int editDist(int i, int j) {
        if (i == 0 && j == 0) {
            return 0;
        } else if (i == 0) {
            return j;
        } else if (j == 0) {
            return i;
        } else if (firstString[i - 1] == secondString[j - 1]) {
            return editDist(i - 1, j - 1);
        } else {
            return Math.min(editDist(i - 1, j), Math.min(editDist(i, j - 1), editDist(i - 1, j - 1))) + 1;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group973603/zemnov/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}
