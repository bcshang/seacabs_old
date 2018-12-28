package playtest;

import java.util.ArrayList;

public class Cocktail {
    String name;
    String source;
    Ingredient spirit;
    ArrayList<Ingredient> ingredients;
    String garnishes;
    String style;
    String served;
    String special;

    public Cocktail(String name, String source, Ingredient spirit,
                    ArrayList<Ingredient> ingredients, String garnishes,
                    String style, String served, String special) {
        this.name = name;
        this.source = source;
        this.spirit = spirit;
        this.ingredients = ingredients;
        this.garnishes = garnishes;
        this.style = style;
        this.served = served;
        this.special = special;
    }


    @Override
    public String toString() {
        String ret = "";
        ret += "Name: " + name + "\n";
        ret += "Source: " + source + "\n";
        ret += "Spirit: " + spirit + "\n";
        
        for(int ii=0; ii<ingredients.size(); ii++) {
            ret += ingredients.get(ii);
        }

        ret += "Garnishes: " + garnishes + "\n";
        ret += "Style: " + style + "\n";
        ret += "Served: " + served + "\n";
        ret += "Special Notes: " + special + "\n";

        return ret;
    }

    
}
