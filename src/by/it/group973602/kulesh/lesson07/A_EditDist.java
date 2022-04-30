package by.it.group973602.kulesh.lesson07;

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
    private String s1, s2;
    private double[][] d;

    double editDist(int i, int j) {
        if (d[i][j] == Double.POSITIVE_INFINITY) {
            if (i == 0)
                d[i][j] = j;
            else if (j == 0)
                d[i][j] = i;
            else {
                int c = s1.charAt(i - 1) != s2.charAt(j - 1) ? 1 : 0;
                double ins = editDist(i, j - 1) + 1;
                double del = editDist(i - 1, j) + 1;
                double sub = editDist(i - 1, j - 1) + c;
                d[i][j] = Math.min(Math.min(ins, del), sub);
            }
        }
        return d[i][j];
    }

    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int n = one.length();
        int m = two.length();
        s1 = one; s2 = two;
        d = new double[n + 1][m + 1];
        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= m; j++)
                d[i][j] = Double.POSITIVE_INFINITY;
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return (int) editDist(n, m);
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}
