package by.it.group973602.kisel.lesson01;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        List<BigInteger> fList = new ArrayList();
        for(int counter =0;counter < n + 1;counter++) {
            fList.add(BigInteger.valueOf(counter));
        }
        for(int i =0;i < n + 1;i++) {
            if(i >= 2) {
                fList.set(i,fList.get(i-1).add(fList.get(i-2)));
            }
        }
        return fList.get(n);
    }

}

