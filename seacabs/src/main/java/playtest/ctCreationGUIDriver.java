package playtest;


import java.util.ArrayList;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class ctCreationGUIDriver extends Application {

    static ctCreationGUIController guiControl;
    static Seacabs seac;


    @Override
    public void start(Stage stage) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("cocktailCreationGUI.fxml"));

            root = loader.load();
            guiControl = (ctCreationGUIController) loader.getController();

            guiControl.setSeac(seac);

            stage.setTitle("Welcome to Seacabs!");
            stage.setScene(new Scene(root));


            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        seac = new Seacabs(args);
        
        launch();
    }

}
