package views;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.MainAnimationTimer;
import models.Occasion;
import models.WindowsFactory;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;


public class Main extends Application{
    private Scene scene;
    private Stage stage;
    private Parent root;

    private static String iconImageLoc;


//    private void initTable(){
//        System.out.println("init()");
//        tableViewResult.setEditable(true);
//        tableColumnProgram = new TableColumn("s");
//        tableColumnTime = new TableColumn("ss");
//        tableColumnProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
//        tableColumnTime.setCellValueFactory(new PropertyValueFactory<>("time"));
//        tableViewResult.setItems(Main.occasions);
//        tableViewResult.getItems().add(Main.occasions.get(1));
//    }

    private void initContent(){
        try {
            root = FXMLLoader.load(getClass().getResource("/resources/sceneMain.fxml"));
        }catch (IOException e) {
            System.out.println("Произошла ошибка в методе initContent  -> IOException");
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("Какая-то атата произошла");
            e.printStackTrace();
        }

        try{
            iconImageLoc = "http://icons.iconarchive.com/icons/scafer31000/bubble-circle-3/16/GameCenter-icon.png";
        }catch (Exception e){
            System.out.println("Error loading images");
        }
        this.scene = new Scene(root);

       // this.scene.setOnMouseClicked(event -> stage.hide());
        //scene.setFill(Color.TRANSPARENT); // - сцена прозрачная

    }

    private void setStage(){
        this.stage.setScene(scene);
        this.stage.setTitle("Какаааа");
        //this.stage.initStyle(StageStyle.TRANSPARENT); //Стиль Окна. Убирает Кнопку ЗАКРЫТЬ и вообще это.
        this.stage.setResizable(false);
        //this.stage.fullScreenProperty();
        this.stage.toBack();
        //this.stage.close();
        this.stage.show();
    }

    private void doSettingsOfWindow(final Stage stage){
        javax.swing.SwingUtilities.invokeLater(this::addAppToTray);
    }

    private void doSettingsOfWindowClosing(final Stage stage){
        Platform.setImplicitExit(false);//Закрывая программу, процессы продолжаются.  То есть программа не закрывается.
        stage.setOnCloseRequest(e->{
            System.out.println("Главное окно пытается закрыться, но игра продолжается :D");
            e.consume();
        });
    }

    @Override
    public void start(final Stage stage) throws Exception {
        initContent();
        this.stage = stage;

        doSettingsOfWindow(this.stage);
        doSettingsOfWindowClosing(this.stage);
        setStage();


//"-fx-background-color: rgba(255, 255, 255, 0.5);"
        /*root.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.5);"//Прозрачность окна = 0
        );*/

        AnimationTimer aa = new MainAnimationTimer();
        aa.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void addAppToTray() {
        try {
            // ensure awt toolkit is initialized.
            java.awt.Toolkit.getDefaultToolkit();

            // app requires system tray support, just exit if there is no support.
            if (!java.awt.SystemTray.isSupported()) {
                System.out.println("No system tray support, application exiting.");
                Platform.exit();
            }

            // set up a system tray icon.
            java.awt.SystemTray tray = java.awt.SystemTray.getSystemTray();
            URL imageLoc = new URL(
                    iconImageLoc
            );
            java.awt.Image image = ImageIO.read(imageLoc);
            java.awt.TrayIcon trayIcon = new java.awt.TrayIcon(image);

            // if the user double-clicks on the tray icon, show the main app stage.
            trayIcon.addActionListener(event -> Platform.runLater(this::showStage));

            // if the user selects the default menu item (which includes the app name),
            // show the main app stage.
            java.awt.MenuItem openItem = new java.awt.MenuItem("hello, world");
            openItem.addActionListener(event -> Platform.runLater(this::showStage));

            // the convention for tray icons seems to be to set the default icon for opening
            // the application stage in a bold font.
            java.awt.Font defaultFont = java.awt.Font.decode(null);
            java.awt.Font boldFont = defaultFont.deriveFont(java.awt.Font.BOLD);
            openItem.setFont(boldFont);

            // to really exit the application, the user must go to the system tray icon
            // and select the exit option, this will shutdown JavaFX and remove the
            // tray icon (removing the tray icon will also shut down AWT).
            java.awt.MenuItem exitItem = new java.awt.MenuItem("Exit");
            exitItem.addActionListener(event -> {
                Platform.exit();
                tray.remove(trayIcon);
            });
            // setup the popup menu for the application.
            final java.awt.PopupMenu popup = new java.awt.PopupMenu();
            popup.add(openItem);
            popup.addSeparator();
            popup.add(exitItem);
            trayIcon.setPopupMenu(popup);
            tray.add(trayIcon);

        }catch (Exception e){
            System.out.println("Unable to init system tray");
            e.printStackTrace();
        }
    }

    private void showStage() {
        if (stage != null) {
            stage.show();
            stage.toFront();
        }
    }


}