package by.it.group973601.apenka.lesson01;

import java.util.ArrayList;
import java.util.List;


/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */



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
        List<Long> sequence = getSequence(m);
        long period = sequence.size() - 2;
        int index = (int) (n % period);
        return sequence.get(index);
    }

    private List<Long> getSequence(long m) {
        List<Long> sequence = new ArrayList<>();
        sequence.add((long) 0);
        sequence.add((long) 1);
        boolean continueLooping = true;
        for (int index = 2; index < m * 6 && continueLooping; index++) {
            sequence.add((sequence.get(index - 1) + sequence.get(index - 2)) % m);
            if(sequence.get(index) == 1 && sequence.get(index - 1) == 0) {
                continueLooping = false;
            }
        }
        return sequence;
    }


}

