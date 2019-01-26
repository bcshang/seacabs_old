/**
 * Writer class for my specific
 */
package playtest;

import java.io.File;
import java.util.ArrayList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.transform.OutputKeys; 
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Write {

    
    public static void write(SeaList seaList) {
        String folder = SeaList.getFolder();
        try {
            // Do not create so many _bak files, only do it once
            if(!seaList.getWritten()) {
                // Rename old file to _bak
                File fileOld = new File(SeaList.getFolder() + seaList.getFile());
                File fileRename = new File(SeaList.getFolder() + seaList.getFile() + "_bak");
                if(!fileOld.renameTo(fileRename)) {
                    System.out.println("Failed to rename file");
                }
                seaList.setWritten();
            }

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();

            Element rootElement;

            ArrayList<?> list = seaList.getList();
            switch(seaList.getType()) {
                    case MASTER_RECIPES:
                    case PERSONAL_RECIPES:
                        rootElement = doc.createElement("recipes");
                        doc.appendChild(rootElement);
                        for(int ii=0; ii<list.size(); ii++) {
                            ((Cocktail)(list.get(ii))).toXML(doc, rootElement, seaList.getType());
                        }          
                        break;
                    case MASTER_BOTTLES:
                    case MASTER_INGREDIENTS:
                        rootElement = doc.createElement("ingredients");
                        doc.appendChild(rootElement);
                        for(int ii=0; ii<list.size(); ii++) {
                            ((Ingredient)(list.get(ii))).toXML(doc, rootElement, seaList.getType());
                        }                
                        break;
                    default:
                        System.out.println("Failed to output, invalid type");
                        System.exit(0);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", 4);
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(folder + seaList.getFile()));
            transformer.transform(source, result);

            // Output to console for testing
            // StreamResult consoleResult = new StreamResult(System.out);
            // transformer.transform(source, consoleResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}