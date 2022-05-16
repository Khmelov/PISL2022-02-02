package by.it.group973601.Lukianenko.lesson01;

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
        long[] fArr = new long[m * 6 + 1];
        fArr[0] = 0;
        fArr[1] = 1;
        long size = 0;
        for (int i = 2; i < m * 6; i++) {
            fArr[i] = (fArr[i - 1] + fArr[i - 2]) % m;
            if (fArr[i] == 1 && fArr[i - 1] == 0) {
                size = i - 1;
                break;
            }
        }
        return fArr[(int) (n % size)];
    }


}

