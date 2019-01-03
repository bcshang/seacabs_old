package playtest;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ChoiceBox;
import javafx.collections.FXCollections;

import java.util.ArrayList;

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
    private ChoiceBox servedChoiceBox;

    @FXML
    private ChoiceBox styleChoiceBox;

    @FXML
    private ChoiceBox fileChoiceBox;


    public void updateServedList(ArrayList<String> servedList) {
        servedChoiceBox.setItems(FXCollections.observableArrayList(servedList));
    }

    public void updateStyleList(ArrayList<String> styleList) {
        styleChoiceBox.setItems(FXCollections.observableArrayList(styleList));
    }

    public void updateCFileList(ArrayList<String> cFileList) {
        fileChoiceBox.setItems(FXCollections.observableArrayList(cFileList));
    }


    public void printDebug() {
        System.out.println("Recipe Saved!");
        
        System.out.println("Name is " + cnameTextField.getText());
        
        System.out.println("Source is " + cSourceTextField.getText());
        
        System.out.println("Garnish is " + cGarnishTextField.getText());
        
        System.out.println("Special is " + cSpecialTextField.getText());
        
        System.out.println("Tasting is " + cTasNotTextArea.getText());   

        System.out.println("Served is " + servedChoiceBox.getValue());

        System.out.println("Style is " + styleChoiceBox.getValue());

        System.out.println("File is " + fileChoiceBox.getValue());
    }


    @FXML
    private void saveRecipeOnClick() {

        printDebug();

    }

    @FXML
    private void initialize(){
        System.out.println("Initializing GUI Controller");
    }

}
