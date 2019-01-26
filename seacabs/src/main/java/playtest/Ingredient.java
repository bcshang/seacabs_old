// Generic Ingredient Class
package playtest;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.Collections;

public class Ingredient {
    // MINIMAL means it's part of a master ingredient list xml with no unit/amount information
    // BOTTLE means that it's part of a bottle ingredient list and will have different informatione because of that
    // FULL means it's part of a personal recipe
    enum ingType {
        MINIMAL, BOTTLE, FULL;
    }

    ingType ingt;

    String name;
    String type;
    String group;
    double amount;
    String unit;


    String tasting;
    String rating;

    String description;

    /**
     * Ingredient constructor for FULL type
     * Typically a part of a cocktail recipe
     * @param  name   name of ingredient
     * @param  group  group of ingredient
     * @param  amount amount of ingredient
     * @param  unit   unit of ingredient
     */
    public Ingredient(String name, String group, double amount, String unit) {
        this.name = name;
        this.group = group;
        this.amount = amount;
        this.unit = unit;
        ingt = ingType.FULL;
    }

    /**
     * Ingredient Constructor for a bottle
     * @param  name    [description]
     * @param  type    [description]
     * @param  tasting [description]
     * @param  rating  [description]
     * @return         [description]
     */
    public Ingredient(String name, String type, String tasting, String rating) {
        this.name = name;
        this.type = type;
        this.tasting = tasting;
        this.rating = rating;
        this.group = "spirit";
        ingt = ingType.BOTTLE;
    }

    /**
     * Ingredient Constructor for MINIMAL type
     * @param  name        name
     * @param  group        group
     * @param  description description
     */
    public Ingredient(String name, String group, String description) {
        this.name = name;
        this.group = group;
        this.description = description;
        ingt = ingType.MINIMAL;
    }

    // Getters
    public String getName() {
        return name; 
    }

    public String getType() {
        return type;
    }

    public String getGroup() {
        return group;
    }

    public double getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        switch(this.ingt) {
            case FULL:
                return "\tType: " + type +
                        "\n\tName: " + name +
                        "\n\tAmount: " + amount + 
                        "\n\tUnit: " + unit + "\n";
                
            case BOTTLE:
                return "\tType: " + type +
                        "\n\tName: " + name +
                        "\n\ttasting: " + tasting + 
                        "\n\trating: " + rating + "\n";
            case MINIMAL:
                return "\tGroup: " + group +
                        "\n\tName: " + name +
                        "\n\tdescription: " + description + "\n";

        }
        return "Error in ingredient list";

    }

    /**
     * CompareTo method for easy sorting
     * @param  other Ingredient being compared to
     * @return       compareTo output
     */
    public int compareTo(Ingredient other) {
        if(this.ingt == ingType.BOTTLE && other.ingt == ingType.BOTTLE) {
                int comp = this.getType().compareTo(other.getType());
                if(comp == 0) {
                    return this.getName().compareTo(other.getName());
                }
                else{
                    return comp;
                } 
        }
                
        if(this.group == "spirit")
            return 1;
        else if(other.group == "spirit")
            return -1;
        else
            return this.getName().compareTo(other.getName());
    }


    /**
     * Creates an XML element of an ingredient that is a child to the document
     * Does not make it a child of any element. That is done externally
     * Typical usage is for adding ingredients to a cocktail element (which probably is the only usage)
     * @param  doc XML document object
     * @return     Ingredient XML object
     */
    public Element toXML(Document doc) {
        Element xingredient = doc.createElement("ingredient");
        Attr xgroup = doc.createAttribute("group");
        xgroup.setValue(group);
        xingredient.setAttributeNode(xgroup);

        Element xname = doc.createElement("name");
        xname.appendChild(doc.createTextNode(this.name));
        xingredient.appendChild(xname);

        Element xamount = doc.createElement("amount");
        xamount.appendChild(doc.createTextNode(Double.toString(this.amount)));
        xingredient.appendChild(xamount);

        Element xunit = doc.createElement("unit");
        xunit.appendChild(doc.createTextNode(this.unit));
        xingredient.appendChild(xunit);

        return xingredient; 
    }


    /**
     * Other method for XML creation for when we are writing for a document as a whole
     * @param doc  Document to create element under
     * @param root root element to create element under
     * @param type the type of list that we are adding to (MASTER_INGREDIENTS or MASTER_BOTTLES)
     */
    public void toXML(Document doc, Element root, Common.XMLType type) { // TODO confusing type notation, fix
        if(type == Common.XMLType.MASTER_INGREDIENTS) {
            Element xingredient = doc.createElement("ingredient");
            root.appendChild(xingredient);
            Attr xgroup = doc.createAttribute("group");
            xgroup.setValue(this.group);
            xingredient.setAttributeNode(xgroup);

            Element xname = doc.createElement("name");
            xname.appendChild(doc.createTextNode(this.name));
            xingredient.appendChild(xname);

            Element xunit = doc.createElement("description");
            xunit.appendChild(doc.createTextNode(this.description));
            xingredient.appendChild(xunit);        
        } else { // assume MASTER_BOTTLES TODO figure out how this is gonna work
            Element xingredient = doc.createElement("bottle");
            root.appendChild(xingredient);

            Element xname = doc.createElement("name");
            xname.appendChild(doc.createTextNode(this.name));
            xingredient.appendChild(xname);

            Element xtype = doc.createElement("type");
            xtype.appendChild(doc.createTextNode(this.type));
            xingredient.appendChild(xtype);

            Element xtasting = doc.createElement("tasting");
            xtasting.appendChild(doc.createTextNode(this.tasting));
            xingredient.appendChild(xtasting); 

            Element xrating = doc.createElement("rating");
            xrating.appendChild(doc.createTextNode(this.rating));
            xingredient.appendChild(xrating); 
        }
    }


}
