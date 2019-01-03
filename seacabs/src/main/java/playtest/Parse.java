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
                return parseMasterRecipe(fileName);
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


    /**
     * Parses master recipe list and returns an arraylist of the cocktails found
     * @param  fileName The complete filepath of the file to be parsed
     * @return          Arraylist of Cocktail Classes
     */
    public static ArrayList<Cocktail> parseMasterRecipe(String fileName) {
        ArrayList<Cocktail> cocktails = new ArrayList<Cocktail>();
        System.out.println("Parsing master recipes from " + fileName);
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
                        String ingUnit = getInner(ingElement, "unit");
                        double ingAmount = Double.parseDouble(getInner(ingElement, "amount"));
                        String ingType = ingElement.getAttribute("type");
                        Ingredient ing = new Ingredient(ingName, ingType, ingAmount, ingUnit);
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








    // Parses Cocktail Options
    public static ArrayList<String> parseServed(String fileName) {
        ArrayList<String> servedList = new ArrayList<String>();
        System.out.println("Parsing served from " + fileName);
        try {
            File inputFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("option");

            // Search for correct type option
            for(int ii = 0; ii < nList.getLength(); ii++) {
                Node nNode = nList.item(ii);

                if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String type = eElement.getAttribute("type");
                    if(type.equals("served")) {
                        System.out.println("Found Served");
                        NodeList servList = eElement.getElementsByTagName("served");
                        for(int jj = 0; jj < servList.getLength(); jj++) {
                            String servedOption = servList.item(jj).getTextContent().trim();
                            servedList.add(servedOption);
                        }
                        break;
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return servedList;
    }

    // Parses Cocktail Options
    public static ArrayList<String> parseStyle(String fileName) {
        ArrayList<String> styleList = new ArrayList<String>();
        System.out.println("Parsing served from " + fileName);
        try {
            File inputFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("option");

            // Search for correct type option
            for(int ii = 0; ii < nList.getLength(); ii++) {
                Node nNode = nList.item(ii);

                if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String type = eElement.getAttribute("type");
                    if(type.equals("style")) {
                        System.out.println("Found Style");
                        NodeList styList = eElement.getElementsByTagName("style");
                        for(int jj = 0; jj < styList.getLength(); jj++) {
                            String styleOption = styList.item(jj).getTextContent().trim();
                            styleList.add(styleOption);
                        }
                        break;
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return styleList;
    }
}