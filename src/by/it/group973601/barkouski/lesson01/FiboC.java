package by.it.group973601.barkouski.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;

public class FiboC {

    private final long startTime = System.currentTimeMillis();

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    long fasterC(long n, int m) {
        //решение практически невозможно найти интуитивно
        //вам потребуется дополнительный поиск информации
        //см. период Пизано
        ArrayList<Long> periodPisano = new ArrayList<>();
        periodPisano.add(0L);
        periodPisano.add(1L);
        int i;
        for (i = 2; i < m * m; i++) {
            periodPisano.add((periodPisano.get(i - 1) + periodPisano.get(i - 2)) % m);
            if (periodPisano.get(i - 1) == 0 && periodPisano.get(i) == 1) {
                break;
            }
        }
        return periodPisano.get((int) n % (i - 1));
    }
}

