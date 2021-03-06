package by.it.group973601.SitskoM.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {

        //вычисление чисел простым быстрым методом
        FiboB fibo = new FiboB();
        int n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {

        BigInteger result = BigInteger.ONE;
        BigInteger k = new BigInteger("0");

        BigInteger[] fibonacci = new BigInteger[n + 1];

        for (int i = 0; i < n; i++) {
            fibonacci[i] = result;

            BigInteger temp = k.add(result);
            k = result;
            result = temp;
        }

        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        return fibonacci[n - 1];
    }

}

