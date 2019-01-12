package playtest;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ChoiceBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;


import java.util.ArrayList;
import java.util.List;

public class ingCreationGUIController {
    @FXML
    private TextField ingNameTextField;

    @FXML
    private ChoiceBox ingTypeChoiceBox;

    @FXML
    private TextArea ingDescTextArea;

    @FXML
    private TextArea ingRevTextArea;

    @FXML
    private Button ingCreateButton;

    Seacabs seac;
    SeaList ingredientMaster;

    public void setSeac(Seacabs seac) {
        this.seac = seac;
        updateTypeList(seac.getTypeList());
    }

    public void updateTypeList(ArrayList<String> typeList) {
        ingTypeChoiceBox.setItems(FXCollections.observableArrayList(typeList));
    }

}