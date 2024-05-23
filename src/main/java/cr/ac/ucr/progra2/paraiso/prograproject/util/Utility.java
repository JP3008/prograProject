package cr.ac.ucr.progra2.paraiso.prograproject.util;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Utility {

    public static int getMaxID(){
        int numDesigns=0;
        File file = new File("File.xml");
        if (file.exists()) {
            try {

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new File(String.valueOf(usualFile())));


                NodeList designList = document.getElementsByTagName("Design");
                numDesigns = designList.getLength();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return numDesigns+1;
    }

    public static String encode(String path) throws IOException {
        byte[] fileContent = Files.readAllBytes(Paths.get(path));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        return encodedString;

    }

    public static File usualFile(){
        File xml = new File("patterns.xml");
        return xml;
    }

}
