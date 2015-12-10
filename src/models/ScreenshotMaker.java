package models;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ScreenshotMaker implements ModalChangeNameObserver, MakeScreenObserver {
    private static ScreenshotMaker instance;

    private static int countScreen = 0;
    private static String path;
    private static String screenName = "screenDiploma";
    private static final String SCREEN_FORMAT = ".jpg";
    private static boolean make;
    public ModalChangeNameObservable modalChangeName;

    private ScreenshotMaker(){
        System.out.println("Create ScreenshotMaker");
        path = "";
        make = false;
    }

    public static ScreenshotMaker getInstance(){
        if(instance == null) instance = new ScreenshotMaker();
        return instance;
    }

    public void setModalChangeName(ModalChangeNameObservable m){
        modalChangeName = m;
        modalChangeName.registerObserver(this);
    }

    public void getScreenshot(){
        if(isMake()) {
            try {
                String fullScreenName = path + screenName + countScreen + SCREEN_FORMAT;
                Robot robot = new Robot();
                BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                ImageIO.write(screenShot, "JPG", new File(fullScreenName));
                countScreen++;
            } catch (Exception e) {
                System.out.println("Error :(");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateName(String text, String p) {
        if(text !=null)
            setScreenName(text);
        if(p != null)
            setPath(p+"\\");
    }

    public static void setPath(String p) {
        path = p;
    }

    public static void setScreenName(String s) {
        screenName = s;
    }

    @Override
    public void setMakeScreenObserver(boolean a) {
        setMake(a);
    }

    public boolean isMake(){
        return make;
    }

    public void setMake(boolean a){
        make = a;
    }

}
