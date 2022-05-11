package by.it.group973602.zabelo.lesson01;

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
        ArrayList<Long> pisano = new ArrayList<>();
        pisano.add(0L);
        pisano.add(1L);
        long n0 = 0;
        long n1 = 1;
        long old;
        for (int i = 2; i <= n; i++) {
            old = n1;
            n1 = (n1 + n0) % m;
            n0 = old;
            if (n0 == 0 && n1 == 1) {
                pisano.remove(pisano.size() - 1);
                break;
            }
            pisano.add(n1);
        }
        return pisano.get((int) (n % pisano.size()));
    }
}

