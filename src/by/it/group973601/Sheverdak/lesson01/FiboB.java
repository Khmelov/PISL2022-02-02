package by.it.group973601.Sheverdak.lesson01;

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
        BigInteger[] fArr = new BigInteger[n+1];
        fArr[0] = BigInteger.ZERO;
        fArr[1]=BigInteger.ONE;
        for (int i = 2; i <= n; i++){
            fArr[i]=fArr[i-1].add(fArr[i-2]);
        }
        return fArr[n];
    }

}

