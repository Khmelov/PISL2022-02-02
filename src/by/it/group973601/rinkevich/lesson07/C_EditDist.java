package by.it.group973601.rinkevich.lesson07;

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
    Итерационно вычислить алгоритм преобразования двух данных непустых строк
    Вывести через запятую редакционное предписание в формате:
     операция("+" вставка, "-" удаление, "~" замена, "#" копирование)
     символ замены или вставки

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    #,#,

    Sample Input 2:
    short
    ports
    Sample Output 2:
    -s,~p,#,#,#,+s,

    Sample Input 3:
    distance
    editing
    Sample Output 2:
    +e,#,#,-s,#,~i,#,-c,~g,


    P.S. В литературе обычно действия редакционных предписаний обозначаются так:
    - D (англ. delete) — удалить,
    + I (англ. insert) — вставить,
    ~ R (replace) — заменить,
    # M (match) — совпадение.
*/


public class C_EditDist {

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        StringBuilder result = new StringBuilder();

        int[][] results = new int[one.length() + 1][two.length() + 1];
        char[][] p = new char[one.length() + 1][two.length() + 1];

        for (int i = 0; i < one.length(); i++) {
            p[i][0] = '-';
        }

        for (int i = 0; i < two.length(); i++) {
            p[0][i] = '+';
        }

        for (int i = 0; i <= one.length(); i++) {
            for (int j = 0; j <= two.length(); j++) {
                if (i == 0 && j == 0) {
                    results[i][j] = 0;
                    continue;
                }
                if (i == 0) {
                    results[i][j] = j;
                    continue;
                }
                if (j == 0) {
                    results[i][j] = i;
                    continue;
                }

                int cost = (one.charAt(i - 1) == two.charAt(j - 1) ? 0 : 1);

                int insert = results[i][j - 1] + 1;
                int delete = results[i - 1][j] + 1;
                int replace = results[i - 1][j - 1] + cost;

                if (insert <= delete && insert <= replace) {
                    results[i][j] = insert;
                    p[i][j] = '+';
                }
                else if (delete < insert && delete < replace) {
                    results[i][j] = delete;
                    p[i][j] = '-';
                }
                else {
                    results[i][j] = replace;
                    p[i][j] = cost == 0 ? '#' : '~';
                }
            }
        }
        int i = one.length(), j = two.length();
        do {
            result.append(',');
            switch (p[i][j]) {
                case '#' -> {
                    i--; j--;
                    result.append("#");
                }
                case '~' -> {
                    i--; j--;
                    result.append(two.charAt(j)).append("~");
                }
                case '-' -> {
                    i--;
                    result.append(one.charAt(i)).append("-");
                }
                case '+' -> {
                    j--;
                    result.append(two.charAt(j)).append("~");
                }
            }
        } while (i != 0 || j != 0);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result.reverse().toString();
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}