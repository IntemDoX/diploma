/*package models;

import javafx.concurrent.Task;
import views.Main;

import java.util.Date;

public class Counter {
    public static boolean isRun = true;

    public static Date beginDate;
    public static Date endDate;
  //  public static long differentData;
    public static long total = 0;

    public static void startTimer(){

        isRun = true;
        Task task = new Task<Void>() {
            @Override
            public Void call() {
                beginDate = new Date();
                while(isRun) {
                    endDate = new Date();
//                    differentData = (endDate.getTime() - beginDate.getTime());
                    try{
                        Thread.sleep(100);
                    }catch (Exception e){
                        endDate = new Date();
                        total = (endDate.getTime() - beginDate.getTime());
                        System.out.println(total);
                    }
                }
                System.out.println("ААЛАЛАЛААЛ");
                total = (endDate.getTime() - beginDate.getTime());
                System.out.println(total);
//                total = differentData;
                return null;
            }
        };
        Thread a = new Thread(task);
        //a.setDaemon(true);
        a.start();
    }

    public static void stopRun(){
        isRun = false;
    }



}
*/