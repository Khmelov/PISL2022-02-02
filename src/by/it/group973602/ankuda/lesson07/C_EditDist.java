package by.it.group973602.ankuda.lesson07;

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


        StringBuilder result = new StringBuilder();

        int[][] dp = new int[one.length() + 1][two.length() + 1];
        char[][] operation = new char[one.length() + 1][two.length() + 1];

        for (int i = 0; i <= one.length(); i++) {
            for (int j = 0; j <= two.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                    operation[i][j] = '+';
                } else if (j == 0) {
                    dp[i][j] = i;
                    operation[i][j] = '-';
                } else {
                    int eq = one.charAt(i - 1) == two.charAt(j - 1) ? 0 : 1;
                    int[] options = new int[]{
                            dp[i - 1][j - 1] + eq,
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1};

                    dp[i][j] = Arrays.stream(options).min().orElse(Integer.MAX_VALUE);

                    if(dp[i][j] == options[0])
                        operation[i][j] = eq == 0 ? '#' : '~';
                    else if(dp[i][j] == options[1])
                        operation[i][j] = '-';
                    else
                        operation[i][j] = '+';
                }
            }
        }

        int i = one.length(), j = two.length();
        do {
            result.append(',');
            switch (operation[i][j]) {
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
        InputStream stream = new FileInputStream(root + "by/it/group973602/ankuda/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}