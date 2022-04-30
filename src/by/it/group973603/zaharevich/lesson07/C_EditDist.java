package by.it.group973603.zaharevich.lesson07;

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
        int[][] array = new int[one.length() + 1][two.length() + 1];

        for (int i = 0; i < one.length() + 1; i++) {
            array[i][0] = i;
        }
        for (int j = 0; j < two.length() + 1; j++) {
            array[0][j] = j;
        }

        for (int i = 0; i < one.length(); i++) {
            for (int j = 0; j < two.length(); j++) {
                int equality = getDiff(one.charAt(i), two.charAt(j));
                array[i + 1][j + 1] = findMin(array[i][j + 1] + 1, array[i + 1][j] + 1, array[i][j] + equality);
            }
        }

        String revertResult = "";
        int i = one.length();
        int j = two.length();
        while (i >= 1) {
            while (j >= 1) {

                int delete = array[i - 1][j];
                int insert = array[i][j - 1];
                int substitute = array[i - 1][j - 1];
                int min = findMin(delete, insert, substitute);

                int coefficient = getDiff(one.charAt(i - 1), two.charAt(j - 1));

                if (min == substitute) {
                    if (coefficient == 0) {
                        revertResult = revertResult + "#,";
                    } else {
                        revertResult = revertResult + "~" + two.charAt(j - 1) + ",";
                    }
                    i--;
                    j--;
                }
                if (min == delete) {
                    revertResult = revertResult + "-" + one.charAt(i - 1) + ",";
                    i--;
                } else {
                    if (min == insert) {
                        revertResult = revertResult + "+" + two.charAt(j - 1) + ",";
                        j--;
                    }

                }

            }
        }

        String[] arrayResult = revertResult.split(",");
        String result = "";
        for (int k = arrayResult.length - 1; k >= 0; k--) {
            result += arrayResult[k] + ",";
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }
    private int findMin(int delete, int insert, int substitute) {
        int min = -1;
        if (delete < insert) {
            min = delete;
        } else {
            min = insert;
        }
        if (min > substitute) {
            min = substitute;
        }
        return min;
    }

    private int getDiff(char one, char two) {
        return one != two ? 1 : 0;
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