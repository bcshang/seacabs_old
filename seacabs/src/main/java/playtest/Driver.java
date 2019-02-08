package playtest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;


public class Driver extends Application {

    static Seacabs seac;
    static databaseController guiControl;

    @Override
    public void start(Stage stage) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("databaseGUI.fxml"));

            root = loader.load();
            guiControl = (databaseController) loader.getController();

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

