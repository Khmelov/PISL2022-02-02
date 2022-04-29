package by.it.group973602.kisel.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Рассчитать число инверсий одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо посчитать число пар индексов 1<=i<j<n, для которых A[i]>A[j].

    (Такая пара элементов называется инверсией массива.
    Количество инверсий в массиве является в некотором смысле
    его мерой неупорядоченности: например, в упорядоченном по неубыванию
    массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию,
    инверсию образуют каждые (т.е. любые) два элемента.
    )

Sample Input:
5
2 3 9 2 9
Sample Output:
2

Головоломка (т.е. не обязательно).
Попробуйте обеспечить скорость лучше, чем O(n log n) за счет многопоточности.
Докажите рост производительности замерами времени.
Большой тестовый массив можно прочитать свой или сгенерировать его программно.
*/


public class C_GetInversions {

    public int inversions = 0;

    int[] merge(int[] ar_1, int[] ar_2) {
        int max = ar_1.length + ar_2.length;
        int[] result = new int[max];
        int m = 0, n = 0;
        for (int i = 0; i < max; i++) {
            if (m >= ar_1.length && n < ar_2.length) {
                result[i] = ar_2[n++];
            } else if (n >= ar_2.length && m < ar_1.length) {
                result[i] = ar_1[m++];
            } else if (ar_1[m] <= ar_2[n]) {
                result[i] = ar_1[m++];
            } else {
                result[i] = ar_2[n++];
                inversions += ar_1.length - m;
            }
        }
        return result;
    }

    int[] mergeSort(int[] arr, int l, int r){
        int[] result = new int[1];
        int index = (l + r) / 2;
        if (l < r) {
            return merge(mergeSort(arr, l, index), mergeSort(arr, index + 1, r));
        } else {
            result[0] = arr[l];
            return result;
        }
    }

    int calc(InputStream stream) {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        mergeSort(a, 0, a.length - 1);
        return inversions;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        int result = instance.calc(stream);
        System.out.print(result);
    }
}
