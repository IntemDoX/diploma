package controllers;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import models.*;
import popup_windows.ModalChangeName;
import popup_windows.ModalTotalResults;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ControllerMain implements ModalChangeNameObserver, MakeScreenObservable, ModalChangeNameObservable{
    private OccasionsStorage occasions= OccasionsStorage.getInstance();
    @FXML
    private TableView<Occasion> tableViewResult;
    @FXML
    private TableColumn<Occasion,String> tableColumnProgram, tableColumnTime;
    @FXML
    private Label lblDirectory, lblName;

    private List<MakeScreenObserver> makeScreenObservers;
//    private List<ModalChangeNameObserver> modalChangeNameObservers;
    private List<ModalChangeNameObserver> modalChangeNameObservers;

    private String screenPath = "";


    private static boolean tableInit = false;
    HistoryStorage uniqueList;
    private ModalChangeName modalChangeName = new ModalChangeName();

    ScreenshotMaker screenshotMaker;

    public ControllerMain(){
        uniqueList = HistoryStorage.getInstance();
        makeScreenObservers = new ArrayList<>();
        screenshotMaker = ScreenshotMaker.getInstance();
        registerScreenObserver(screenshotMaker);
        modalChangeNameObservers = new ArrayList<>();
        screenshotMaker.setModalChangeName(this);
        //modalChangeName.registerObserver(screenshotMaker);

        initTable();
    }


    @FXML
    private void showTotalTime(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Отчёт: ");
        System.out.println(System.getProperty("user.name"));
        for(UniqueOccasion a : uniqueList.getUniqueList()){
            System.out.println("Программа " + a.getNameOfProgram() + " проработала " + a.getValue());
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        ModalTotalResults a = new ModalTotalResults();
    }

    @FXML
    private void changeDirectory(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(lblDirectory.getScene().getWindow());
        if(selectedDirectory == null)
            lblDirectory.setText("No Directory selected");
        else{
            lblDirectory.setText(selectedDirectory.getAbsolutePath());
            screenPath = selectedDirectory.getAbsolutePath();
            notifyObservers();
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
                        tableViewResult.setItems(occasions.getOccasions());
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

    @Override
    public void updateName(String text, String path) {
        lblName.setText(text);
    }

    @Override
    public void registerScreenObserver(MakeScreenObserver o) {
        makeScreenObservers.add(o);
    }

    @Override
    public void removeScreenObserver(MakeScreenObserver o) {
        makeScreenObservers.remove(o);
    }

    @Override
    public void notifyScreenObservers(boolean a) {
        for(MakeScreenObserver b : makeScreenObservers){
            b.setMakeScreenObserver(a);
        }
    }

    @Override
    public void registerObserver(ModalChangeNameObserver o) {
        modalChangeNameObservers.add(o);
    }

    @Override
    public void removeObserver(ModalChangeNameObserver o) {
        modalChangeNameObservers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(ModalChangeNameObserver s : modalChangeNameObservers){
            s.updateName("ПУУУУУУУУУть", screenPath);
            System.out.println("ЛАЛАЛАЛАЛАЛАЛ");
        }
    }

    @FXML
    private void makeScreenNo(){
        screenshotMaker.setMake(false);
        System.out.println("FALSE");
    }
    @FXML
    private void makeScreenYes(){
        screenshotMaker.setMake(true);
        System.out.println("TRUE");
    }
}