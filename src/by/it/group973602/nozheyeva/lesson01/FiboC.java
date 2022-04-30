package by.it.group973602.nozheyeva.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //решение практически невозможно найти интуитивно
        //вам потребуется дополнительный поиск информации
        //см. период Пизано

        var Array = new ArrayList<Long>();

        long previous = 0;
        long current = 1;
        long temporary;

        for(int i = 0; i < m * m; i++)
        {
            Array.add(previous);
            temporary = current;
            current = (previous + current) % m;
            previous = temporary;

            if (previous == 0 && current == 1)
                break;
        }

        n = n % Array.size();
        return Array.get((int) n);
    }


}

