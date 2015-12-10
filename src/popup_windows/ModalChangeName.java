package popup_windows;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.ModalChangeNameObservable;
import models.ModalChangeNameObserver;
import models.ScreenshotMaker;

import java.util.ArrayList;
import java.util.List;

public class ModalChangeName implements ModalChangeNameObservable {
    private String newName = "";
    private List<ModalChangeNameObserver> modalChangeNameObservers;
    ScreenshotMaker screenshotMaker;

    private Stage changeNameStage;
    private TextField textField;

    public ModalChangeName(){
        screenshotMaker = ScreenshotMaker.getInstance();
        modalChangeNameObservers = new ArrayList<>();
        changeNameStage = new Stage();
        screenshotMaker.setModalChangeName(this);
        Pane root = new Pane();
        root.setStyle("-fx-background-color:tan;-fx-padding:10px;");

        textField = new TextField();
        textField.setMinWidth(180);
        textField.setTranslateY(10);
        textField.setTranslateX(10);
        textField.setPromptText("Enter new name");

        Button btnOk = new Button("Ok");
        btnOk.setMinWidth(100);
        btnOk.setTranslateY(60);
        btnOk.setTranslateX(50);

        btnOk.setOnAction(e->changeName());

        Scene scene = new Scene(root, 200,100);
        changeNameStage.setScene(scene);
        changeNameStage.setTitle("Change name");
        changeNameStage.initModality(Modality.APPLICATION_MODAL);

        root.getChildren().addAll(textField, btnOk);

    }
    private void changeName(){
        newName = textField.getText();
        notifyObservers();
        System.out.println(newName);
        textField.clear();
        changeNameStage.close();
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
        for(ModalChangeNameObserver modalChangeNameObserver : modalChangeNameObservers){
            modalChangeNameObserver.updateName(newName, null);
        }
    }

    public void showAndWait(){
        changeNameStage.showAndWait();
    }

}
