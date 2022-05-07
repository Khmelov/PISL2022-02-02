package by.it.group973601.apenka.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
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

        for (int i = 0; i <= one.length(); i++) {
            for (int j = 0; j <= two.length(); j++) {
                if (i == 0) {
                    d[i][j] = j;
                } else if (j == 0) {
                    d[i][j] = i;
                } else {
                    d[i][j] = minm_edits(
                            d[i - 1][j - 1] + NumOfReplacement(one.charAt(i - 1), two.charAt(j - 1)), // replace
                            d[i - 1][j] + 1, // delete
                            d[i][j - 1] + 1); // insert
                }
            }
        }

        int i = one.length(), j = two.length();
        while (i >= 0 && j >= 0) {
            if (i == 0 && j == 0) {
                break;
            }
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
                if (one.charAt(i - 1) != two.charAt(j - 1)) {
                    c1 += 1;
                }
                c += c1;
                if (a < b && a < c) {
                    s.append(one.charAt(i - 1)).append('-');
                    i -= 1;
                } else if (b < a && b < c) {
                    s.append(two.charAt(j - 1)).append('+');
                    j -= 1;
                } else {
                    if (c1 == 0) {
                        s.append('#');
                    } else {
                        s.append(two.charAt(j - 1)).append('~');
                    }
                    i -= 1;
                    j -= 1;
                }
            }
        }
        result = s.reverse().toString();
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    static int NumOfReplacement(char c1, char c2) {
        return c1 == c2 ? 0 : 1;
    }

    static int minm_edits(int... nums) {
        return Arrays.stream(nums).min().orElse(Integer.MAX_VALUE);
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