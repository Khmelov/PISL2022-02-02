package by.it.group973602.radetskaya.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии,
            - за сам массив отрезков - сортировка на месте
            - рекурсивные вызовы должны проводиться на основе 3-разбиения

        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска
        для первого отрезка решения, а затем найдите оставшуюся часть решения
        (т.е. отрезков, подходящих для точки, может быть много)

    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/


public class C_QSortOptimized {

    //отрезок
    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            return start - o.start != 0 ? start - o.start : stop - o.stop;
        }
    }

    void quickSort(Segment[] s, int left, int right) {
        int i = left, j = right, middle = (left + right) / 2, rMiddle;
        int lMiddle = rMiddle = middle;
        Segment m = s[middle];
        while (true) {
            while (i < lMiddle && s[i].compareTo(m) < 0) {
                if (s[i].compareTo(m) == 0) {
                    swap(s, i, --lMiddle);
                } else i++;
            }
            while (j > rMiddle && s[j].compareTo(m) >= 0) {
                if (s[j].compareTo(m) == 0) {
                    swap(s, j, ++rMiddle);
                } else j--;
            }
            if (i >= lMiddle && j <= rMiddle) break;
            else if (i >= lMiddle) {
                swap(s, ++rMiddle, j);
                swap(s, rMiddle, lMiddle);
                i = lMiddle++;
            } else if (j <= rMiddle) {
                swap(s, --lMiddle, i);
                swap(s, lMiddle, rMiddle);
                j = rMiddle--;
            } else {
                swap(s, i, j);
                i++; j--;
            }
        }
        if (lMiddle > left) {
            quickSort(s, left, --lMiddle);
        }
        if (rMiddle < right) {
            quickSort(s, ++rMiddle, right);
        }
    }

    void swap(Segment[] s, int a, int b) {
        Segment tmp = s[a];
        s[a] = s[b];
        s[b] = tmp;
    }

    int binarySearch(Segment[] s, int point) {
        int left = 0, right = s.length - 1, middle, idx = -1;
        while (left <= right) {
            middle = (left + right) / 2;
            if (point >= s[middle].start) {
                idx = middle;
                left = middle + 1;
            }
            else right = middle - 1;
        }
        return idx;
    }

    int[] getAccessory2(InputStream stream) {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i] = new Segment(scanner.nextInt(),scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }

        quickSort(segments, 0, segments.length - 1);

        for (int i = 0; i < points.length; i++) {
            int lastIdx = binarySearch(segments, points[i]);
            for (int j = 0; j <= lastIdx; j++) {
                if (points[i] >= segments[j].start && points[i] <= segments[j].stop)
                    result[i]++;
            }
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
