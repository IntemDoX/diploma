package controllers;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.HistoryStorage;
import models.UniqueOccasion;

public class ControllerModalTotalResults {
    @FXML
    private TableView<UniqueOccasion> tableViewTotal;
    @FXML
    private TableColumn<UniqueOccasion,String> tableColumnTotalProgram;
    @FXML
    private TableColumn<UniqueOccasion,Long> tableColumnTotalTime;


    public HistoryStorage uniqueOccasions;
    private static boolean tableInit = false;

    public ControllerModalTotalResults(){
        uniqueOccasions = HistoryStorage.getInstance();
        initTable();
    }

    private void initTable() {
        Task task = new Task<Void>() {
            @Override
            public Void call() {
                System.out.println("Initialize table...");
                while (!isTableInit()) {
                    if (tableColumnTotalProgram != null && tableColumnTotalTime != null) {
                        tableViewTotal.setEditable(true);
                        tableColumnTotalProgram.setCellValueFactory(new PropertyValueFactory<>("uniqueProgram"));
                        tableColumnTotalTime.setCellValueFactory(new PropertyValueFactory<>("totalTime"));
                        tableViewTotal.setItems(uniqueOccasions.getUniqueList());
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
    public static boolean isTableInit(){
        return tableInit;
    }
}
