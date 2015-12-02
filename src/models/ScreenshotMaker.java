package models;

import popup_windows.ModalChangeName;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ScreenshotMaker implements ModalChangeNameObserver {
    private static int countScreen = 0;
    private static String path = "/";
    private static String screenName = "screenDiploma";
    private static String screenFormat = ".jpg";
    private static String fullScreenName = path + screenName + countScreen + screenFormat;
    private ModalChangeName modalChangeName;
    public ScreenshotMaker(ModalChangeName modalChangeName){
        this.modalChangeName = modalChangeName;
        this.modalChangeName.registerObserver(this);
    }
    public static void getScreenshot(){
        try {
            Robot robot = new Robot();
            BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(screenShot, "JPG", new File(fullScreenName));
        }catch (Exception e){
            System.out.println("Error :(");
            e.printStackTrace();
        }
        countScreen++;
    }

    public static void setScreenName(String screenName) {
        ScreenshotMaker.screenName = screenName;
    }

    public static void setPath(String path) {
        ScreenshotMaker.path = path;
    }

    @Override
    public void update(String text) {
        screenName = text;
    }
}
