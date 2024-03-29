/**
 * Generic list class for me to use to keep track of various things
 * I assume all files are in the same folder
 */

package playtest;


import java.util.ArrayList;
import java.util.Collections;

public class SeaList {
    static String folderName;

    ArrayList<?> list; // Unknown list type but we expect a finite type
    Common.XMLType type;
    String fileName;
    boolean written;

    public SeaList(ArrayList<?> list, Common.XMLType type, String fileName) {
        this.list = list;
        this.type = type;
        this.fileName = fileName;
        written = false;
    }

    /**
     * Sort the list
     */
    public void sortList() {
        if(type == Common.XMLType.MASTER_INGREDIENTS || type == Common.XMLType.MASTER_BOTTLES) {
            Collections.sort((ArrayList<Ingredient>)list, (o1, o2) -> o1.compareTo(o2));
        } else if(type == Common.XMLType.MASTER_RECIPES || type == Common.XMLType.PERSONAL_RECIPES) {
            Collections.sort((ArrayList<Cocktail>)list, (o1, o2) -> o1.compareTo(o2));
        }
    }

    /**
     * Add an object to the list
     * @param obj Object to add
     */
    public void add(Object obj) {
        if(type == Common.XMLType.MASTER_INGREDIENTS && obj instanceof Ingredient) {
            ((ArrayList<Ingredient>)list).add((Ingredient)obj);
        } else if((type == Common.XMLType.MASTER_RECIPES || type == Common.XMLType.PERSONAL_RECIPES) && obj instanceof Cocktail) {
            ((ArrayList<Cocktail>)list).add((Cocktail)obj);
        } 
        sortList();
        Write.write(this);
    }

    // Getters
    public ArrayList<?> getList() {
        return list;
    }

    public Common.XMLType getType() {
        return type;
    }

    public String getFile() {
        return fileName;
    }

    public static String getFolder() {
        return folderName;
    }

    public boolean getWritten() {
        return written;
    }

    public int size() {
        return list.size();
    }

    // Setters
    public void setWritten() {
        written = true;
    }

    /**
     * Returns the ingredient associated with a name
     * Assumes names are unique
     * @param  name name of ingredient searched for
     * @return      null if invalid list of ingredient not found
     *              Ingredient object if ingredient is found
     */
    public Ingredient getIngredient(String name) {
        switch(type) {
            case MASTER_INGREDIENTS:
            case MASTER_BOTTLES:
                ArrayList<Ingredient> ingList = (ArrayList<Ingredient>) list;
                for(int ii=0; ii<ingList.size(); ii++) {
                    if(ingList.get(ii).getName().equalsIgnoreCase(name)) {
                        return ingList.get(ii);
                    }
                }
            default:
                return null;
        }
    }
}
