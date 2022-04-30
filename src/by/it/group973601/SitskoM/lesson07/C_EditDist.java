package by.it.group973601.SitskoM.lesson07;

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
        String result = "";

        StringBuilder s = new StringBuilder();
        int[][] d = new int[one.length() + 1][two.length() + 1];

        d[0][0] = 0;
        for (int j = 1; j <= two.length(); j++) {
            d[0][j] = d[0][j - 1] + 1;
        }
        for (int i = 1; i <= one.length(); i++) {
            d[i][0] = d[i - 1][0] + 1;
            for (int j = 1; j <= two.length(); j++) {
                int min = d[i - 1][j] + 1;
                if (d[i][j - 1] + 1 < min)
                    min = d[i][j - 1] + 1;
                int c = d[i - 1][j - 1];
                if (one.charAt(i - 1) != two.charAt(j - 1))
                    c += 1;
                if (c < min)
                    min = c;
                d[i][j] = min;
            }
        }
        int i = one.length(), j = two.length();
        while (i >= 0 && j >= 0) {
            if (i == 0 && j == 0)
                break;
            s.append(',');
            if (i == 0) {
                s.append(two.charAt(j - 1)).append('+');
                j -= 1;
            } else if (j == 0) {
                s.append(one.charAt(i - 1)).append('-');
                i -= 1;
            } else {
                int a = d[i - 1][j] + 1;
                int b = d[i][j - 1] + 1;
                int c = d[i - 1][j - 1];
                int c1 = 0;
                if (one.charAt(i - 1) != two.charAt(j - 1))
                    c1 += 1;
                c += c1;
                if (a < b && a < c) {
                    s.append(one.charAt(i - 1)).append('-');
                    i -= 1;
                } else if (b < a && b < c) {
                    s.append(two.charAt(j - 1)).append('+');
                    j -= 1;
                } else {
                    if (c1 == 0)
                        s.append('#');
                    else
                        s.append(two.charAt(j - 1)).append('~');
                    i -= 1;
                    j -= 1;
                }
            }
        }
        result = s.reverse().toString();
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }

}