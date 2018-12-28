package playtest;

// TODO only import actually needed packages
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class Seacabs 
{
    public static void main( String[] args )
    {
        System.out.println( "SeaCabs Started!" );
        if(args.length != 1) {
            System.out.println("How to use SeaCabs:");
            System.out.println("Argument 1 should be folder path to data folder without the '/'");
            System.out.println("The program looks for a file called input.txt to parse from");
            System.exit(0);
        }
        // TODO fix to work with & without the folder path thing
        File inputFile = new File(args[0] + "/input.txt"); // Input.txt

    }
}
