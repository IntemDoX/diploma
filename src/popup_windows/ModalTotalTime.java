package popup_windows;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Counter;
import models.Occasion;
import views.Main;

import java.util.Map;

public class ModalTotalTime {

    private Stage stage;
    TableColumn<UnicOccasion, String> programCol;
    TableColumn<UnicOccasion,String> timeCol;

    public ObservableList<UnicOccasion> unicOccasions;
    TableView<UnicOccasion> table;
    private static boolean tableInit = false;

    public ModalTotalTime(){
        stage = new Stage();
        table = new TableView<>();
        unicOccasions = FXCollections.observableArrayList();

        Scene scene = new Scene(table,800,600);
        stage.setScene(scene);



    }

    public void showAndWait(){
        programCol = new TableColumn<>("Program");
        timeCol = new TableColumn<>("Working Time");

        table.getColumns().add(programCol);
        table.getColumns().add(timeCol);

        System.out.println("ИНИЦИАЛИЗАЦИЯ ТАБЛИЦЫ");
        initTable();
        System.out.println("ОКНО");
        stage.showAndWait();

    }

    private void initTable() {
       // unicOccasions = FXCollections.observableArrayList();
        for(Map.Entry<String, Long> m : Main.storyList.entrySet()){
            // System.out.println("Программа: " + m.getKey() + " проработала " + m.getValue() + " секунд");
            unicOccasions.add(new UnicOccasion(m.getKey(), Counter.getWorkTimeMessage(m.getValue())));
        }

        System.out.println("Проходим по таблице");
        for(UnicOccasion s : unicOccasions){
            System.out.println(s.getProgram() + " : " + s.getTime() + "ssssss");
        }


        tableInit = false;
        Task task = new Task<Void>() {
            @Override
            public Void call() {
                System.out.println("Initialize table...");
                while (!isTableInit()) {
                    if (programCol != null && timeCol != null) {
                        table.setEditable(true);
                        programCol.setCellValueFactory(new PropertyValueFactory<>("prog"));
                        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
                        table.setItems(unicOccasions);
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




    private static class UnicOccasion{
        private final SimpleStringProperty program;
        private final SimpleStringProperty time;


        public UnicOccasion(String name, String workTime) {
            this.program = new SimpleStringProperty(name);
            this.time = new SimpleStringProperty(workTime);
        }

        public String getTime() {
            return time.get();
        }

        public String getProgram() {
            return program.get();
        }

    }
}
