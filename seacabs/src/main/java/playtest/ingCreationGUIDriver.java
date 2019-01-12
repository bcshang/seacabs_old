package playtest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;


import java.util.concurrent.CountDownLatch;

public class ingCreationGUIDriver extends Application {
    private ingCreationGUIController guiControl;
    private Seacabs seac;


    public static final CountDownLatch latch = new CountDownLatch(1);
    public static ingCreationGUIDriver ingGUI = null;

    public static ingCreationGUIDriver waitForStart() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ingGUI;
    }

    public static void setIngCreationGUI(ingCreationGUIDriver startUpTest0) {
        ingGUI = startUpTest0;
        latch.countDown();
    }

    public ingCreationGUIDriver() {
        setIngCreationGUI(this);
    }

    public void setSeac(Seacabs seac) {
        this.seac = seac;
        guiControl.setSeac(seac);
    }

    @Override
    public void start(Stage stage) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ingCreationGUI.fxml"));

            root = loader.load();
            guiControl = (ingCreationGUIController) loader.getController();


            stage.setTitle("Ingredient Creation GUI");
            stage.setScene(new Scene(root));


            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}