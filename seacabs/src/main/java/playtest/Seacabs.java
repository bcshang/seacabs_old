package playtest;

/**
 * Main functions for cocktail App
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;


public class Seacabs{ 

    public static void printHelp() {
        System.out.println("How to use SeaCabs:");
        System.out.println("Argument 1 should be folder path to data folder with the '/'");
        System.out.println("The program looks for a file called input.txt to parse from");
        System.exit(0);
    }


    ArrayList<SeaList> masterCocktailLists = new ArrayList<SeaList>();
    ArrayList<SeaList> personalCocktailLists = new ArrayList<SeaList>();
    ArrayList<SeaList> masterIngredientList = new ArrayList<SeaList>();
    String folder;
    ArrayList<String> servedList;
    ArrayList<String> styleList;
    ArrayList<String> unitList;

    public Seacabs(String [] args) {
        System.out.println( "Seacabs Created!" );
        if(args.length != 1) {
            printHelp();
        } 

        folder = args[0];
        importData();
    }

    ArrayList<SeaList> getMasterCocktailLists() {
        return masterCocktailLists;
    }
    ArrayList<SeaList> getPersonalCocktailLists() {
        return personalCocktailLists;
    }


    public ArrayList<String> getServedList() {
        return servedList;
    }

    public ArrayList<String> getStyleList() {
        return styleList;
    }

    public ArrayList<String> getUnitList() {
        return unitList;
    }

    public ArrayList<String> getCocktailFileList() {
        ArrayList<String> cFileList = new ArrayList<String>();
        for(int ii=0; ii<masterCocktailLists.size(); ii++) {
            cFileList.add(masterCocktailLists.get(ii).getFile());
        }
        return cFileList;
    }

    // TODO allow for more master lists
    public ArrayList<String> getIngredientListString() {
        ArrayList<Ingredient> ingList = (ArrayList<Ingredient>)masterIngredientList.get(0).getList();
        ArrayList<String> ingString = new ArrayList<String>();
        for(int ii=0; ii<ingList.size(); ii++) {
            ingString.add(ingList.get(ii).getName());
        }
        return ingString;
    }

    public SeaList getIngredientList() {
        return masterIngredientList.get(0);
    }


    public void importData()
    {

        SeaList.folderName = folder;
        // TODO fix to work with & without the folder path thing
        // TODO make it so I don't have to initialize?

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(folder + "inputs.txt"));
            String line = reader.readLine();
            while(line != null) {
                System.out.println(line);
                String [] splitString = line.split(":", 2);
                Common.XMLType type = null;
                switch(splitString[1]) {
                    case "MASTER_RECIPES":
                        type = Common.XMLType.MASTER_RECIPES;
                        masterCocktailLists.add(new SeaList((ArrayList<Cocktail>)Parse.parseFile(folder + splitString[0], type), type, splitString[0]));
                        break;
                    case "PERSONAL_RECIPES":
                        type = Common.XMLType.PERSONAL_RECIPES;
                        break;
                    case "MASTER_LIQUOR":
                        type = Common.XMLType.MASTER_LIQUOR;
                        break;
                    case "MASTER_INGREDIENTS":
                        type = Common.XMLType.MASTER_INGREDIENTS;
                        masterIngredientList.add(new SeaList((ArrayList<Ingredient>)Parse.parseFile(folder + splitString[0], type), type, splitString[0]));
                        break;
                    default:
                        System.out.println(splitString[1] + " is not supported");
                        System.exit(0);
                }

                // TODO check if the type is correct coming back to remove warning

                // read next
                line = reader.readLine();
            }

            reader.close();

        } catch(IOException e) {
            System.out.println("Error has occurred in parsing");
            printHelp();
            System.exit(0);
        }

        // Try parsing cocktail options
        servedList = Parse.parseServed(folder + "options.xml");
        styleList = Parse.parseStyle(folder + "options.xml");
        unitList = Parse.parseUnit(folder + "options.xml");

        // System.out.println();
        // for(int ii=0; ii<cocktailMasterList.size(); ii++) {
        //     System.out.println(cocktailMasterList.get(ii));
        // }


        // Start the GUI

    }
}
