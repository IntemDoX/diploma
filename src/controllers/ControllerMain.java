package controllers;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import models.ModalChangeNameObserver;
import models.ScreenshotMaker;
import popup_windows.ModalChangeName;
import models.Occasion;
import popup_windows.ModalTotalTime;
import views.Main;

import java.io.File;
import java.util.Map;


/**
 * это котроллер к FXML - представлени.
 */
public class ControllerMain implements ModalChangeNameObserver {


    @FXML
    private TableView<Occasion> tableViewResult;
    @FXML
    private TableColumn<String,String> tableColumnProgram, tableColumnTime;
    /*@FXML
    private Button btnTotalTime, btnChooseDirectory, btnChangeName;*/
    @FXML
    private Label lblDirectory, lblName;
    /*@FXML
    private RadioButton rbYes, rbNo;*/

    private ModalTotalTime modalTotalTime = new ModalTotalTime();
    private static boolean tableInit = false;

   // private static Stage mainStage;

    private ModalChangeName modalChangeName = new ModalChangeName();
   // ScreenshotMaker screenshotMaker = new ScreenshotMaker(modalChangeName);

    public ControllerMain(){
        //this.modalChangeName = modalChangeName;
        modalChangeName.registerObserver(this);
      initTable();
    }

    @FXML
    private void showTotalTime(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Отчёт: ");

        //Тут открываем табличку, где у нас загружаются все программы. Для этого создать отдельное представление. Вывести в отдельный класс. New TotalResults.

        modalTotalTime.showAndWait();

     /* for(Map.Entry<String, Long> m : Main.storyList.entrySet()){
          System.out.println("Программа: " + m.getKey() + " проработала " + m.getValue() + " секунд");
      }*/
    }

    /**
     * Change the directory to save screenshots.
     * To fix: do it right. Don't use static methods of ScreenshotMaker.
     */
    @FXML
    private void changeDirectory(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(lblDirectory.getScene().getWindow());
        if(selectedDirectory == null){
            lblDirectory.setText("No Directory selected");
        }
        else{
            lblDirectory.setText(selectedDirectory.getAbsolutePath());
            ScreenshotMaker.setPath(selectedDirectory.getAbsolutePath());                                               //TO FIX!
        }
    }
    @FXML
    private void changeName(){
        System.out.println("Change name -> Open modal window;");
        modalChangeName.showAndWait();
    }

    private void initTable(){

        Task task = new Task<Void>() {
            @Override public Void call() {
                System.out.println("Initialize table...");
                while(!isTableInit()) {
                    if (tableColumnProgram != null && tableColumnTime != null) {
                        tableViewResult.setEditable(true);
                        tableColumnProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
                        tableColumnTime.setCellValueFactory(new PropertyValueFactory<>("time"));
                        tableViewResult.setItems(Main.occasions);
                        tableInit = true;
                        System.out.println("SUCCESS: Table was initialize;");
                    } else {
                        System.out.println("The table was not initialize. Waiting for 1 second...");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            System.out.println("The thread was interrupted;");
                        }
                    }
                }
                return null;
            }
        };
        Thread a = new Thread(task);
        a.setDaemon(true);
        a.start();
    }

    public static boolean isTableInit() {
        return tableInit;
    }

    public void setLblNameText(String s) {
        this.lblName.setText(s);
    }

    @Override
    public void update(String text) {
        lblName.setText(text);
    }
}
