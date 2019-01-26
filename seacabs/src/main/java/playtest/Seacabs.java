/**
 * Main wrapper class for cocktail app
 */

package playtest;

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


    // All of the file lists, I use arraylist of the list to ensure we can add additional personal files if needed
    // For example if multiple people use this
    ArrayList<SeaList> masterCocktailLists = new ArrayList<SeaList>();
    ArrayList<SeaList> personalCocktailLists = new ArrayList<SeaList>();
    ArrayList<SeaList> masterIngredientList = new ArrayList<SeaList>();
    ArrayList<SeaList> masterBottlesList = new ArrayList<SeaList>();

    String folder;
    
    // Cocktail options
    ArrayList<String> servedList;
    ArrayList<String> styleList;
    ArrayList<String> unitList;
    ArrayList<String> groupList;
    ArrayList<String> ratingList;

    /**
     * Class Constructor
     * Initializes the cocktail app basically
     * @param  args arguments passed from command line
     */
    public Seacabs(String [] args) {
        System.out.println( "Seacabs Created!" );
        if(args.length != 1) {
            System.out.println("File Path incorrectly specified");
            printHelp();
            System.out.println("Assuming current folder");
            folder = System.getProperty("user.dir");
        } else {
            folder = args[0];
        }
        importData();
    }

    // Getters
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
    
    public ArrayList<String> getGroupList() {
        return groupList;
    }
    public ArrayList<String> getRatingList() {
        return ratingList;
    }

    public ArrayList<SeaList> getMasterIngredientList() {
        return masterIngredientList;
    }

    public ArrayList<SeaList> getMasterBottlesList() {
        return masterBottlesList;
    }


    // Returns all files which were parsed to find cocktail recipes
    public ArrayList<String> getCocktailFileList() {
        ArrayList<String> cFileList = new ArrayList<String>();
        for(int ii=0; ii<masterCocktailLists.size(); ii++) {
            cFileList.add(masterCocktailLists.get(ii).getFile());
        }
        for(int ii=0; ii<personalCocktailLists.size(); ii++) {
            cFileList.add(personalCocktailLists.get(ii).getFile());
        }
        return cFileList;
    }

    // Parses both master ingredients and bottles for the cocktail app and returns the names of all ingredients
    public ArrayList<String> getIngredientListString() {
        ArrayList<String> ingString = new ArrayList<String>();
        for(int jj=0; jj<masterIngredientList.size(); jj++) {
            ArrayList<Ingredient> ingList = (ArrayList<Ingredient>)masterIngredientList.get(jj).getList();
            for(int ii=0; ii<ingList.size(); ii++) {
                ingString.add(ingList.get(ii).getName());
            }
        }
        for(int jj=0; jj<masterBottlesList.size(); jj++) {
            ArrayList<Ingredient> ingList = (ArrayList<Ingredient>)masterBottlesList.get(jj).getList();
            for(int ii=0; ii<ingList.size(); ii++) {
                ingString.add(ingList.get(ii).getName());
            }
        }
        return ingString;
    }


    public void importData()
    {

        SeaList.folderName = folder;
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
                        personalCocktailLists.add(new SeaList((ArrayList<Cocktail>)Parse.parseFile(folder + splitString[0], type), type, splitString[0]));
                        break;
                    case "MASTER_BOTTLES":
                        type = Common.XMLType.MASTER_BOTTLES;
                        masterBottlesList.add(new SeaList((ArrayList<Ingredient>)Parse.parseFile(folder + splitString[0], type), type, splitString[0]));
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
        groupList = Parse.parseIngGroup(folder + "options.xml");
        ratingList = Parse.parseRating(folder + "options.xml");

        // System.out.println();
        // for(int ii=0; ii<masterCocktailLists.get(0).getList().size(); ii++) {
        //     System.out.println(masterCocktailLists.get(0).getList().get(ii));
        // }
    }
}
