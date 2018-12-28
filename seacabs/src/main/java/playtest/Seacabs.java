package playtest;

// TODO only import actually needed packages

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;



public class Seacabs 
{
    public static void main( String[] args )
    {
        System.out.println( "SeaCabs Started!" );
        if(args.length != 1) {
            System.out.println("How to use SeaCabs:");
            System.out.println("Argument 1 should be folder path to data folder with the '/'");
            System.out.println("The program looks for a file called input.txt to parse from");
            System.exit(0);
        }

        String folder = args[0];

        // TODO fix to work with & without the folder path thing
        // TODO make it so I don't have to initialize?
        ArrayList<Cocktail> cocktailMasterList = new ArrayList<Cocktail>();
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

                cocktailMasterList = (ArrayList<Cocktail>)Parse.parseFile(folder + splitString[0], type);

                // read next
                line = reader.readLine();
            }

            reader.close();

        } catch(IOException e) {
            e.printStackTrace();
            System.exit(0);
        }


        System.out.println();
        for(int ii=0; ii<cocktailMasterList.size(); ii++) {
            System.out.println(cocktailMasterList.get(ii));
        }

    }
}
