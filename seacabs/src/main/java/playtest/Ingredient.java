// Generic Ingredient Class
package playtest;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Ingredient {
    String name;
    String type;
    double amount;

    public Ingredient(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }
    public Ingredient(String name, String type, double amount) {
        this.name = name;
        this.type = type;
        this.amount = amount;
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

    @Override
    public String toString() {
        return "\tType: " + type +
                "\n\tName: " + name +
                "\n\tAmount: " + amount + "\n";
    }

    public Element toXML(Document doc) {
        Element xingredient = doc.createElement("ingredient");
        Attr xtype = doc.createAttribute("type");
        xtype.setValue(type);

        Element xname = doc.createElement("name");
        xname.appendChild(doc.createTextNode(this.name));
        xingredient.appendChild(xname);

        Element xamount = doc.createElement("amount");
        xamount.appendChild(doc.createTextNode(Double.toString(this.amount)));
        xingredient.appendChild(xamount);

        return xingredient; 
    }


}
