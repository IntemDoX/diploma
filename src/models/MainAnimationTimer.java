package models;
import javafx.animation.AnimationTimer;

import java.util.Date;

public class MainAnimationTimer extends AnimationTimer {
    private ActiveWindowsStorage winList;
    private OccasionsStorage occasions;
    private HistoryStorage storyList;
    private HistoryStorage uniqueList;
    private String actWin;
    private Date beginDate;
    ScreenshotMaker screenshotMaker;
    public MainAnimationTimer() {
        storyList = HistoryStorage.getInstance();
        occasions = OccasionsStorage.getInstance();
        winList = ActiveWindowsStorage.getInstance();
        uniqueList = HistoryStorage.getInstance();
        screenshotMaker = ScreenshotMaker.getInstance();
        actWin = null;
        beginDate = new Date();
        this.start();
    }

    @Override
    public void handle(long now) {
        int indexOfCurrentProgram = 0;
        Date endDate;
        long diff;
        WindowsFactory.takeActWin();
        if(actWin!=null){
            if (!actWin.equals(winList.getActiveWindowsList().get(indexOfCurrentProgram))) {
                endDate = new Date();
                diff = endDate.getTime() - beginDate.getTime();

                uniqueList.getUniqueList().add(new UniqueOccasion(actWin,(String.valueOf(diff))));

                if(storyList.getStoryList().containsKey(actWin))
                    storyList.put(actWin, storyList.getStoryList().get(actWin) + diff);
                else
                    storyList.put(actWin, diff);

                System.out.println(actWin + " проработала в общем " + getWorkTimeMessage(storyList.get(actWin)));
                occasions.add(new Occasion(actWin, getWorkTimeMessage(storyList.get(actWin)), beginDate));

                beginDate = new Date();
                actWin = winList.get(indexOfCurrentProgram);
                System.out.println("Сейчас работает " + actWin);
                //Делаем скриншот
                screenshotMaker.getScreenshot();
            }
        }
        else
            actWin = winList.get(indexOfCurrentProgram);
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
        return timeInHorses + ":" + timeInMinutes + ":" + timeInSeconds + ":" + timeInMilliseconds;
    }

}

