package playtest;

import java.util.ArrayList;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


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

    public void toXML(Document doc, Element root, Common.XMLType type) {

        // Wrapper element
        Element xcocktail = doc.createElement("recipe");
        root.appendChild(xcocktail);
        
        // Add elements
        Element xname = doc.createElement("name");
        xname.appendChild(doc.createTextNode(this.name));
        xcocktail.appendChild(xname);

        Element xsource = doc.createElement("source");
        xsource.appendChild(doc.createTextNode(this.source));
        xcocktail.appendChild(xsource);

        for(int ii=0; ii < ingredients.size(); ii++) {
            xcocktail.appendChild(ingredients.get(ii).toXML(doc));
        }

        Element xgarnish = doc.createElement("garnish");
        xgarnish.appendChild(doc.createTextNode(this.garnishes));
        xcocktail.appendChild(xgarnish);

        Element xstyle = doc.createElement("style");
        xstyle.appendChild(doc.createTextNode(this.style));
        xcocktail.appendChild(xstyle);

        Element xserved = doc.createElement("served");
        xserved.appendChild(doc.createTextNode(this.served));
        xcocktail.appendChild(xserved);

        Element xspecial = doc.createElement("special");
        xspecial.appendChild(doc.createTextNode(this.special));
        xcocktail.appendChild(xspecial);        
    } 
    
}
