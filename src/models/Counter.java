package models;

import javafx.concurrent.Task;
import views.Main;

import java.util.Date;

/**
 * Created by Vladislav on 21.11.2015.
 */

/**
 * класс счётчик. Считает, сколько времени проработала программа. Считает пока что криво, но всё же работает)
 */
public class Counter {
    public static boolean isRun = true;

    public static Date beginDate;
    public static Date endDate;
    public static long differentData;
    public static long total = 0;

    public static void startTimer(){
        isRun = true;
        Task task = new Task<Void>() {
            @Override public Void call() {
                beginDate = new Date();
                while(isRun) {
                    endDate = new Date();
                    differentData = (endDate.getTime() - beginDate.getTime());
                    try{
                        Thread.sleep(100);
                    }catch (Exception e){
                    }
                }
                total = differentData;
                return null;
            }
        };
        Thread a = new Thread(task);
        a.setDaemon(true);
        a.start();
    }

    public static void stopRun(){
        isRun = false;
    }


    public static String getWorkTimeMessage(long time){
        long timeInMilliseconds = time;
        int timeInSeconds = 0;
        int timeInMinutes = 0;
        int timeInHorses = 0;



        if(timeInMilliseconds>=1000){
            timeInSeconds = (int)timeInMilliseconds/1000;
            timeInMilliseconds = timeInMilliseconds%1000;
            if(timeInSeconds>=60){
                timeInMinutes = timeInSeconds/60;
                timeInSeconds = timeInSeconds%60;
                if(timeInMinutes>=60){
                    timeInHorses = timeInMinutes/60;
                    timeInMinutes = timeInMinutes%60;
                }
            }
        }
        String s =timeInHorses + ":" + timeInMinutes + ":" + timeInSeconds + ":" + timeInMilliseconds;


        return s;
    }
}
