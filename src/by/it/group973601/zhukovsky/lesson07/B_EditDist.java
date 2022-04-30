package by.it.group973601.zhukovsky.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Итерационно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class B_EditDist {


    private  char[] first;
    private  char[] second;

    int getDistanceEdinting(String one, String two) {
        first = one.toCharArray();
        second = two.toCharArray();
        return editDistTD(first, second);
    }

    private int editDistTD(char[] first, char[] second){
        int[][] array = new int[first.length][second.length];
        for(int i = 0; i < first.length; i++){
            array[i][0] = i;
        }
        for(int j = 0; j < second.length; j++){
            array[0][j] = j;
        }
        int c;
        for(int i = 1; i < first.length; i++){
            for(int j = 1; j < second.length; j++){
                c = diff(first[i], second[j]);
                array[i][j] = Math.min(array[i - 1][j], Math.min(array[i][j - 1]+1, array[i - 1][j - 1])) + c;
            }
        }
        return array[first.length-1][second.length-1];
    }

    private int diff(char a, char b){
        if(a == b){
            return 0;
        }else {
            return 1;
        }
    }



    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}