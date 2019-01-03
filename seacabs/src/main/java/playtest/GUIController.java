package playtest;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;


public class GUIController {

    @FXML 
    private TextField cnameTextField;

    @FXML
    private Button saveRecipeButton;


    @FXML
    private void saveRecipeOnClick() {
        System.out.println("Recipe Saved!");
    }

    @FXML
    private void initialize(){

    }

}
