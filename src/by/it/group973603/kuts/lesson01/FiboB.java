package by.it.group973603.kuts.lesson01;


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
        if(n == 0)
            return BigInteger.ZERO;
        if(n == 1)
            return BigInteger.ONE;
        BigInteger prev = BigInteger.ONE, next = BigInteger.ONE;
        n -= 2;
        while (n>0){
            next = next.add(prev);
            prev = next.subtract(prev);
            n--;
        }
        return next;
    }

}


