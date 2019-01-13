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
    enum ingType {
        MINIMAL, PERSONAL, FULL;
    }

    ingType ingt;

    String name;
    String type;
    double amount;
    String unit;

    String description;
    String review;
    public Ingredient(String name, String type, double amount, String unit) {
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.unit = unit;
        ingt = ingType.FULL;
    }

    public Ingredient(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
        ingt = ingType.MINIMAL;
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

    // Getters
    public String getName() {
        return name; 
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return "\tType: " + type +
                "\n\tName: " + name +
                "\n\tAmount: " + amount + 
                "\n\tUnit: " + unit + "\n";
    }


    public int compareTo(Ingredient other) {
        return this.getName().compareTo(other.getName());
    }


    /**
     * Creates an XML element of an ingredient that is a child to the document
     * Does not make it a child of any element. That is done externally
     * Typical usage is for adding ingredients to a cocktail element
     * @param  doc XML document object
     * @return     Ingredient XML object
     */
    public Element toXML(Document doc) {
        
        if(ingt == ingType.FULL) {
            Element xingredient = doc.createElement("ingredient");
            Attr xtype = doc.createAttribute("type");
            xtype.setValue(type);
            xingredient.setAttributeNode(xtype);

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
        } else { // Assuming minimal
            Element xingredient = doc.createElement("ingredient");
            Attr xtype = doc.createAttribute("type");
            xtype.setValue(type);
            xingredient.setAttributeNode(xtype);

            Element xname = doc.createElement("name");
            xname.appendChild(doc.createTextNode(this.name));
            xingredient.appendChild(xname);

            Element xunit = doc.createElement("description");
            xunit.appendChild(doc.createTextNode(this.description));
            xingredient.appendChild(xunit);
            return xingredient;
        }
    }

    public void toXML(Document doc, Element root, Common.XMLType type) { // TODO confusing type notation, fix
        if(type == Common.XMLType.MASTER_INGREDIENTS) {
            Element xingredient = doc.createElement("ingredient");
            root.appendChild(xingredient);
            Attr xtype = doc.createAttribute("type");
            xtype.setValue(this.type);
            xingredient.setAttributeNode(xtype);

            Element xname = doc.createElement("name");
            xname.appendChild(doc.createTextNode(this.name));
            xingredient.appendChild(xname);

            Element xunit = doc.createElement("description");
            xunit.appendChild(doc.createTextNode(this.description));
            xingredient.appendChild(xunit);        
        } else { // assume MASTER_BOTTLES TODO figure out how this is gonna work
            Element xingredient = doc.createElement("bottle");
            root.appendChild(xingredient);
            Attr xtype = doc.createAttribute("type");
            xtype.setValue(this.type);
            xingredient.setAttributeNode(xtype);

            Element xname = doc.createElement("name");
            xname.appendChild(doc.createTextNode(this.name));
            xingredient.appendChild(xname);

            Element xunit = doc.createElement("description");
            xunit.appendChild(doc.createTextNode(this.description));
            xingredient.appendChild(xunit); 
        }
    }


}
