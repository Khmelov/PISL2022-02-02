package by.it.group973601.Lukianenko.lesson07;

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
        int[][] results = new int[one.length() + 1][two.length() + 1];
        char[][] p = new char[one.length() + 1][two.length() + 1];

        for (int i = 0; i < one.length(); i++) {
            p[i][0] = '-';
        }

        for (int i = 0; i < two.length(); i++) {
            p[0][i] = '+';
        }

        for (int n = 0; n <= one.length(); n++) {
            for (int m = 0; m <= two.length(); m++) {
                if (n == 0 && m == 0) {
                    results[n][m] = 0;
                    continue;
                }
                if (n == 0) {
                    results[n][m] = m;
                    continue;
                }
                if (m == 0) {
                    results[n][m] = n;
                    continue;
                }

                int cost = (one.charAt(n - 1) == two.charAt(m - 1) ? 0 : 1);

                int insert = results[n][m - 1] + 1;
                int delete = results[n - 1][m] + 1;
                int replace = results[n - 1][m - 1] + cost;

                if (insert <= delete && insert <= replace) {
                    results[n][m] = insert;
                    p[n][m] = '+';
                }
                else if (delete < insert && delete < replace) {
                    results[n][m] = delete;
                    p[n][m] = '-';
                }
                else {
                    results[n][m] = replace;
                    p[n][m] = cost == 0 ? '#' : '~';
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        int i = one.length();
        int j = two.length();

        do {
            char c = p[i][j];
            stringBuilder.append(",");
            if(c == '~' || c == '#') {
                i --;
                j --;

                if (c == '~') {
                    stringBuilder.append(two.charAt(j));
                }
            }
            else if(c == '-') {
                i --;
                stringBuilder.append(one.charAt(i));
            }
            else {
                j --;
                stringBuilder.append(two.charAt(j));
            }
            stringBuilder.append(c);
        } while((i != 0) || (j != 0));

        result = stringBuilder.reverse().toString();
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
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