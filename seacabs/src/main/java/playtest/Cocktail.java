package playtest;

import java.util.ArrayList;

public class Cocktail {
    String name;
    String source;
    ArrayList<Ingredient> ingredients;
    String garnishes;
    String style;
    String served;
    String special;

    public Cocktail(String name, String source,
                    ArrayList<Ingredient> ingredients, String garnishes,
                    String style, String served, String special) {
        this.name = name;
        this.source = source;
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
        ret += "Ingredients: " + "\n";
        for(int ii=0; ii<ingredients.size(); ii++) {
            ret += ingredients.get(ii) + "\n";
        }

        ret += "Garnishes: " + garnishes + "\n";
        ret += "Style: " + style + "\n";
        ret += "Served: " + served + "\n";
        ret += "Special Notes: " + special + "\n";

        return ret;
    }

    
}
