package models;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Vladislav on 26.11.2015.
 */
public class PopUpChangeName extends Application {
    Button btnscene1, btnscene2;
    Label lblscene1, lblscene2;
    FlowPane pane1, pane2;
    Scene scene1, scene2;
    Stage thestage, newStage;


    @Override
    public void start(Stage primaryStage) throws Exception {
        pane1 = new FlowPane();
        pane2 = new FlowPane();
        pane1.setHgap(20);
        pane2.setHgap(10);
        pane1.setStyle("-fx-background-color:tan;-fx-padding:10px;");
        pane2.setStyle("-fx-background-color:red;-fx-padding:10px;");
        pane1.getChildren().addAll(lblscene1, btnscene1);
        pane2.getChildren().addAll(lblscene2, btnscene2);

        scene1 = new Scene(pane1, 200,100);
        scene2 = new Scene(pane2, 200,100);

        newStage = new Stage();
        newStage.setScene(scene2);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setTitle("Hello world");
        primaryStage.setScene(scene1);
        primaryStage.setMinWidth(300);
        primaryStage.show();

    }
    public void ButtonClicked(ActionEvent e)
    {
        if (e.getSource()==btnscene1)
            newStage.showAndWait();
        else
            newStage.close();
    }
}
