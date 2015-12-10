package popup_windows;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class ModalTotalResults{
   public ModalTotalResults(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/resources/sceneModalTotalResults.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Results");
            stage.showAndWait();
        }catch (IOException e) {
            System.out.println("Произошла ошибка в методе initContent  -> IOException");
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("Какая-то атата произошла");
            e.printStackTrace();
        }
    }
}
