package playtest;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;


public class GUIController {

    @FXML 
    private TextField cnameTextField;

    @FXML 
    private TextField cSourceTextField;

    @FXML 
    private TextField cGarnishTextField;

    @FXML 
    private TextField cSpecialTextField;

    @FXML 
    private TextArea cTasNotTextArea;

    @FXML
    private Button saveRecipeButton;


    @FXML
    private void saveRecipeOnClick() {
        System.out.println("Recipe Saved!");
        String name = cnameTextField.getText();
        System.out.println("Name is " + name);
        String source = cSourceTextField.getText();
        System.out.println("Source is " + source);
        String garnish = cGarnishTextField.getText();
        System.out.println("Garnish is " + garnish);
        String special = cSpecialTextField.getText();
        System.out.println("Special is " + special);
        String tastingNotes = cTasNotTextArea.getText();
        System.out.println("Tasting is " + tastingNotes);


    }

    @FXML
    private void initialize(){
        System.out.println("Initializing GUI Controller");
    }

}
