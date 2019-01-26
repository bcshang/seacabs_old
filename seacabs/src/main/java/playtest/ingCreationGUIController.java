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
    private ChoiceBox ingGroupChoiceBox;

    @FXML
    private TextArea ingDescTextArea;

    @FXML
    private Button ingCreateButton;

    @FXML
    private ChoiceBox ingFileChoiceBox;


    Seacabs seac;

    public void setSeac(Seacabs seac) {
        this.seac = seac;
        updateGroupList(seac.getGroupList());
        setIngredientFileArray(seac.getMasterIngredientList());
        setIngredientFileArray(seac.getMasterBottlesList());
    }

    public void setIngredientFileArray(ArrayList<SeaList> ingredients) {
        for(int ii=0; ii<ingredients.size(); ii++) {
            ingFileChoiceBox.getItems().add(ingredients.get(ii).getFile());
        }
    }


    @FXML
    public void ingCreateButtonOnClick() {
        String name = ingNameTextField.getText();
        String type;
        String file;
        try {
             type = ingGroupChoiceBox.getValue().toString();
             file = ingFileChoiceBox.getValue().toString();
        } catch(Exception e) {
            System.out.println("Please select an appropriate type and/or file");
            return;
        }
        String description = ingDescTextArea.getText();
        Ingredient createdIng = new Ingredient(name, type, description);

        SeaList addedToList = null;
        ArrayList<SeaList> tempList = seac.getMasterIngredientList();
        for(int ii=0; ii < tempList.size(); ii++) {
            if(tempList.get(ii).getFile().equalsIgnoreCase(file)) {
                addedToList = tempList.get(ii);
                break;
            }
        }
        if(addedToList == null) {
            tempList = seac.getMasterBottlesList();
            for(int ii=0; ii < tempList.size(); ii++) {
                if(tempList.get(ii).getFile().equalsIgnoreCase(file)) {
                    addedToList = tempList.get(ii);
                    break;
                }
            }
        }
        
        if(addedToList == null) {
            System.out.println("Error finding list to add to");
            return;
        }

        addedToList.add(createdIng);

        ingNameTextField.clear();
        ingDescTextArea.clear();
        
        System.out.println("Created Ingredient");
    }

    public void updateGroupList(ArrayList<String> groupList) {
        ingGroupChoiceBox.setItems(FXCollections.observableArrayList(groupList));
    }

}