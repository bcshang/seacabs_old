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
    String folder;
    ArrayList<String> servedList;

    public Seacabs(String [] args) {
        System.out.println( "Seacabs Created!" );
        if(args.length != 1) {
            printHelp();
        } 

        folder = args[0];
        importData();
    }




    public ArrayList<String> getServedList() {
        return servedList;
    }


    public void importData()
    {

        SeaList.folderName = folder;
        // TODO fix to work with & without the folder path thing
        // TODO make it so I don't have to initialize?
        // 

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
        // System.out.println();
        // for(int ii=0; ii<cocktailMasterList.size(); ii++) {
        //     System.out.println(cocktailMasterList.get(ii));
        // }

        Write.write(masterCocktailLists.get(0));


        // Start the GUI

    }
}
