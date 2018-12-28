package playtest;

import java.util.ArrayList;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Parse {
    public static ArrayList<?> parseFile(String fileName, Common.XMLType type) {
        switch(type) {
            case MASTER_RECIPES:
                return parseRecipe(fileName);
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

    public static String getInner(Element n, String tag) {
        return n.getElementsByTagName(tag).item(0).getTextContent().trim();
    }

    public static ArrayList<Cocktail> parseRecipe(String fileName) {
        ArrayList<Cocktail> cocktails = new ArrayList<Cocktail>();
        System.out.println("Parsing from " + fileName);
        try {
            File inputFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("recipe");
            System.out.println("Found " + nList.getLength() + " recipe(s)");
            for(int ii = 0; ii < nList.getLength(); ii++) {
                Node nNode = nList.item(ii);

                if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String name = getInner(eElement, "name");
                    String source = getInner(eElement, "source");

                    // Parse ingredients
                    NodeList ingList = eElement.getElementsByTagName("ingredient");
                    ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
                    for(int jj = 0; jj < ingList.getLength(); jj++) {
                        Element ingElement = (Element) ingList.item(jj);
                        String ingName = getInner(ingElement, "name");
                        double amount = Double.parseDouble(getInner(ingElement, "amount"));
                        String type = ingElement.getAttribute("type");
                        Ingredient ing = new Ingredient(ingName, type, amount); // TODO add Type
                        ingredients.add(ing);
                    }


                    String garnish = getInner(eElement, "garnish");
                    String style = getInner(eElement, "style");
                    String served = getInner(eElement, "served");
                    String special = getInner(eElement, "special");
                    
                    Cocktail ct = new Cocktail(name, source, ingredients, garnish, style, served, special);

                    cocktails.add(ct);
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return cocktails;
    }


}