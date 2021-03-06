package by.it.group973601.shidlovskiy.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
даны события events
реализуйте метод calcStartTimes, так, чтобы число включений регистратора на
заданный период времени (1) было минимальным, а все события events
были зарегистрированы.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/


//комментарии от проверочного решения сохранены для подсказки, но вы можете их удалить.
//подготовка к жадному поглощению массива событий
//hint: сортировка Arrays.sort обеспечит скорость алгоритма
//C*(n log n) + C1*n = O(n log n)

//пока есть незарегистрированные события
//получим одно событие по левому краю
//и запомним время старта видеокамеры
//вычислим момент окончания работы видеокамеры
//и теперь пропустим все покрываемые события
//за время до конца работы, увеличивая индекс


public class A_VideoRegistrator {

    public static void main(String[] args) {
        A_VideoRegistrator instance=new A_VideoRegistrator();
        double[] events=new double[]{1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7};
        List<Double> starts=instance.calcStartTimes(events,1);
        System.out.println(starts);
    }
    List<Double> calcStartTimes(double[] events, double workDuration){
        List<Double> result;
        result = new ArrayList<>();
        int i=0;
        Arrays.sort(events);
        double eventLength = events[i] + workDuration;
        result.add(events[i]);

        for (double e : events){
            if (e > eventLength){
                eventLength = e + workDuration;
                result.add(e);
            }
        }
        return result;
    }
}