package playtest;

import javafx.scene.control.Button;

// For launching another window for ingredient creation
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;

public class databaseController {
    Seacabs seac;

    @FXML
    private Button createCtButton;



    public void setSeac(Seacabs seac) {
        this.seac = seac;
    }

    public void createCtOnClick() {
        System.out.println("Creating Cocktail GUI launch");
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("cocktailCreationGUI.fxml"));
            Stage stage = new Stage();
            root = loader.load();
            ctCreationGUIController guiControl = (ctCreationGUIController) loader.getController();
            guiControl.setSeac(seac);

            stage.setTitle("Create Cocktail");
            stage.setScene(new Scene(root));


            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}