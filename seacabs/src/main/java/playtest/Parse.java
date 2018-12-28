package playtest;

import java.util.ArrayList;

public class Parse {
    public static <T> ArrayList<T> parseFile(String fileName, Common.XMLType type) {
        switch(type) {
            case MASTER_RECIPES:
                return null;
                // break;
            case PERSONAL_RECIPES:
                return null;
                // break; 
            case MASTER_LIQUOR:
                return null;
                // break; 
            case MASTER_INGREDIENTS:
                return null;
                // break;
            default:
                return null;
        }
    }


}