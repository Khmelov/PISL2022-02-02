package by.it.group973602.gomonchuk.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Даны число 1<=n<=100 ступенек лестницы и
целые числа −10000<=a[1],…,a[n]<=10000, которыми помечены ступеньки.
Найдите максимальную сумму, которую можно получить, идя по лестнице
снизу вверх (от нулевой до n-й ступеньки), каждый раз поднимаясь на
одну или на две ступеньки.

Sample Input 1:
2
1 2
Sample Output 1:
3

Sample Input 2:
2
2 -1
Sample Output 2:
1

Sample Input 3:
3
-1 2 1
Sample Output 3:
3

*/

public class C_Stairs {
    int getMaxSum(InputStream stream ) {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] stairs = new int[n];
        for (int i = 0; i < n; i++) {
            stairs[i] = scanner.nextInt();
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int[] results = new int[n];

        // :)
        for (int i = 0; i < n; i++)
            results[i] =
                    (i == 0 ? stairs[i] :
                    (i == 1 ? Math.max(stairs[i], results[i - 1] + stairs[i]) :
                              Math.max(results[i - 2] + stairs[i], results[i - 1] + stairs[i])));

        // :(
//        for (int i = 0; i < n; i++) {
//            if (i == 0) {
//                results[i] = stairs[i];
//            } else {
//                int b = results[i - 1] + stairs[i];
//                if (i == 1) {
//                    results[i] = Math.max(stairs[i], b);
//                } else {
//                    results[i] = Math.max(results[i - 2] + stairs[i], b);
//                }
//            }
//        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return results[n - 1];
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataC.txt");
        C_Stairs instance = new C_Stairs();
        int res=instance.getMaxSum(stream);
        System.out.println(res);
    }

}
