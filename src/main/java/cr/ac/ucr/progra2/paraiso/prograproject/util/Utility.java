package cr.ac.ucr.progra2.paraiso.prograproject.util;

import cr.ac.ucr.progra2.paraiso.prograproject.data.DesignPatternData;
import cr.ac.ucr.progra2.paraiso.prograproject.data.DesignPatternTypeData;
import cr.ac.ucr.progra2.paraiso.prograproject.domain.DesignPattern;
import cr.ac.ucr.progra2.paraiso.prograproject.domain.DesignPatternType;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import org.jdom2.JDOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Utility {

    private int saveID;

    public int returnID() {
        return saveID;
    }

    public void saveID(int ID) {
        this.saveID = ID;
    }

    public static int getMaxID(File givenFile){
        int maxID=0;
        File file = new File(String.valueOf(givenFile));
        if (file.exists()) {
            try {

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new File(String.valueOf(givenFile)));


                NodeList designList = document.getElementsByTagName("Design");
                for (int i = 0; i < designList.getLength(); i++) {
                    String idStr = designList.item(i).getAttributes().getNamedItem("ID").getNodeValue();
                    int currentID = Integer.parseInt(idStr);
                    if (currentID > maxID) {
                        maxID = currentID;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return maxID+1;
    }

    public static List<Integer> getIDList(File givenFile){
        File file = new File(String.valueOf(givenFile));
        List<Integer> list = new ArrayList<>();
        if (file.exists()) {
            try {

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new File(String.valueOf(givenFile)));


                NodeList designList = document.getElementsByTagName("Design");
                for (int i = 0; i < designList.getLength(); i++) {
                    int currentID = Integer.parseInt(designList.item(i).getAttributes().getNamedItem("ID").getNodeValue());
                    list.add(currentID);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return list;
    }


    public static String encode(String path) throws IOException {
        byte[] fileContent = Files.readAllBytes(Paths.get(path));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        return encodedString;

    }

    public static Image decode(String b64String){
        byte[] imageBytes = Base64.getDecoder().decode(b64String);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
        return new Image(inputStream);
    }

    public static File usualDataFile(){
        File xml = new File("patterns.xml");
        return xml;
    }

    public static File usualTypeFile(){
        File xml = new File("types.xml");
        return xml;
    }

    public static DesignPattern getDesign(int i) throws IOException, JDOMException {
        DesignPatternData data = new DesignPatternData(String.valueOf(Utility.usualDataFile()));
        return data.xmlToObject(i);
    }
    public static DesignPatternType getDesignType(int i) throws IOException, JDOMException {
        DesignPatternTypeData data = new DesignPatternTypeData(String.valueOf(Utility.usualTypeFile()));
        return data.xmlToObject(i);
    }
}
