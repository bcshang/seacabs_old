package playtest;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

// For launching another window
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;



import java.util.ArrayList;
import java.util.List;

public class ctCreationGUIController {

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
    private Button removeIngredientButton;

    @FXML
    private ChoiceBox servedChoiceBox;

    @FXML
    private ChoiceBox styleChoiceBox;

    @FXML
    private ChoiceBox fileChoiceBox;

    @FXML
    private TableView ingredientTableView;
    @FXML
    private TableColumn cTableName;
    @FXML
    private TableColumn cTableType;
    @FXML
    private TableColumn cTableAmount;
    @FXML
    private TableColumn cTableUnit;


    @FXML
    private ChoiceBox ingredientPickerChoiceBox;
    @FXML
    private ChoiceBox unitChoiceBox;
    @FXML
    private Button addIngredientButton;
    @FXML
    private TextField ingredientAmountTextField;

    @FXML
    private Button createIngButton;

    Seacabs seac;
    ArrayList<SeaList> ingredientMaster;

    public void setSeac(Seacabs seac) {
        this.seac = seac;
        updateServedList(seac.getServedList());
        updateStyleList(seac.getStyleList());
        updateUnitList(seac.getUnitList());
        updateCFileList(seac.getCocktailFileList());
        updateIngredientPickerChoiceBox(seac.getIngredientListString());
        updateIngredientMaster(seac.getMasterIngredientList());
    }

    public void updateIngredientMaster(ArrayList<SeaList> list) {
        ingredientMaster = list;
    }

    public void updateServedList(ArrayList<String> servedList) {
        servedChoiceBox.setItems(FXCollections.observableArrayList(servedList));
    }

    public void updateStyleList(ArrayList<String> styleList) {
        styleChoiceBox.setItems(FXCollections.observableArrayList(styleList));
    }

    public void updateUnitList(ArrayList<String> unitList) {
        unitChoiceBox.setItems(FXCollections.observableArrayList(unitList));
    }

    public void updateCFileList(ArrayList<String> cFileList) {
        fileChoiceBox.setItems(FXCollections.observableArrayList(cFileList));
    }

    public void updateIngredientPickerChoiceBox(ArrayList<String> ingList) {
        ingredientPickerChoiceBox.setItems(FXCollections.observableArrayList(ingList));
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

        for(int ii=0; ii<ingredientTableView.getItems().size(); ii++) {
            System.out.println(ingredientTableView.getItems().get(ii));
        }

    }

    @FXML
    private void removeIngredientOnClick() {
        ObservableList<Ingredient> ingredientSelected, allIngredients;
        allIngredients = ingredientTableView.getItems();
        ingredientSelected = ingredientTableView.getSelectionModel().getSelectedItems();

        ingredientSelected.forEach(allIngredients::remove);
    }

    @FXML
    private void saveRecipeOnClick() {
        printDebug();
        List<Ingredient> ingList = ingredientTableView.getItems();
        ArrayList<Ingredient> ingArrayList;
        if (ingList instanceof ArrayList<?>) {
            ingArrayList = (ArrayList<Ingredient>) ingList;
        } else {
            ingArrayList = new ArrayList<Ingredient>(ingList);
        }

        String name = cnameTextField.getText();
        String source = cSourceTextField.getText();
        String garnish = cGarnishTextField.getText();
        String special = cSpecialTextField.getText();
        String tasting = cSpecialTextField.getText();
        String served, style, file;
        try {
            served = servedChoiceBox.getValue().toString();
            style = styleChoiceBox.getValue().toString();
            file = fileChoiceBox.getValue().toString();
        } catch (Exception e) {
            System.out.println("Error please select proper options from choice boxes");
            return;
        }

        // Find what file to add to
        SeaList addedToList = null;
        ArrayList<SeaList> tempList = seac.getMasterCocktailLists();
        for(int ii=0; ii < tempList.size(); ii++) {
            if(tempList.get(ii).getFile().equalsIgnoreCase(file)) {
                addedToList = tempList.get(ii);
                break;
            }
        }
        if(addedToList == null) {
            tempList = seac.getPersonalCocktailLists();
            for(int ii=0; ii < tempList.size(); ii++) {
                if(tempList.get(ii).getFile().equalsIgnoreCase(file)) {
                    addedToList = tempList.get(ii);
                    break;
                }
            }
        }
        
        if(addedToList == null) {
            System.out.println("Error finding list to add to");
            System.exit(0);
        }

        Cocktail ct;
        if(addedToList.getType() == Common.XMLType.MASTER_RECIPES)
            ct = new Cocktail(name, source, ingArrayList, garnish, style, served, special);
        else // assume personal recipe
            ct = new Cocktail(name, source, ingArrayList, garnish, style, served, special, tasting);

        addedToList.add(ct);
        // Cocktail addedCT = new Cocktail();


    }

    @FXML
    private void addIngredientOnClick() {
        String ingredientName = (String)ingredientPickerChoiceBox.getValue();
        String amount = ingredientAmountTextField.getText();
        String unit = (String) unitChoiceBox.getValue();
        Ingredient masterIng = null;
        for(int ii=0; ii<ingredientMaster.size(); ii++) {
            masterIng = ingredientMaster.get(ii).getIngredient(ingredientName);
            if(masterIng != null)
                break;
        }
        if(masterIng == null) {
            System.out.println("Invalid ingredient or list");
        }

        double damount;
        try {
            damount = Double.parseDouble(amount); 
        } catch(Exception e) {
            System.out.println("Failed to add ingredient");
            return;
        }
        if(!ingredientName.equals("")) { // TODO Fix this to check for null
            Ingredient addedIng = new Ingredient(ingredientName, masterIng.getType(), damount, unit);

            ingredientTableView.getItems().add(addedIng);

            
            ingredientAmountTextField.clear();
            System.out.println("Added " + amount + " " + unit + " of " + ingredientName);
        }
        else {
            System.out.println("Failed to add ingredient");
        }
    }


    @FXML
    private void createIngButtonOnClick() {
        System.out.println("Creating Ingredient GUI Launching");

        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ingCreationGUI.fxml"));
            Stage stage = new Stage();
            root = loader.load();
            ingCreationGUIController guiControl = (ingCreationGUIController) loader.getController();
            guiControl.setSeac(seac);

            stage.setTitle("Ingredient Creation GUI");
            stage.setScene(new Scene(root));


            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void initialize(){

        System.out.println("Initializing GUI Controller");
        cTableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cTableType.setCellValueFactory(new PropertyValueFactory<>("type"));
        cTableAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        cTableUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
    }

}
