package cr.ac.ucr.progra2.paraiso.prograproject.data;

import cr.ac.ucr.progra2.paraiso.prograproject.domain.DesignPattern;
import cr.ac.ucr.progra2.paraiso.prograproject.domain.DesignPatternType;
import cr.ac.ucr.progra2.paraiso.prograproject.util.Utility;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DesignPatternTypeData {

    //Attributes
    private String routeDocument;
    private Element root;
    private Document doc;

    //Constructor
    public DesignPatternTypeData(String routeDocument) throws IOException, JDOMException {

        File file = new File(routeDocument);


        //Different caases of file existing or not
        if (!file.exists()){

            this.routeDocument = routeDocument;
            this.root = new Element("Designs");
            this.doc= new Document(root);
            save();

        }else{
            SAXBuilder saBuilder = new SAXBuilder();
            saBuilder.setIgnoringElementContentWhitespace(true);
            this.doc = saBuilder.build(new File(routeDocument));
            this.root = doc.getRootElement();
            this.routeDocument = routeDocument;

        }


    }


    //Writes the Design into the XML
    private void save() throws IOException {

        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(this.doc,new PrintWriter(this.routeDocument));
        System.out.println("Elemento guardado");
        xmlOutputter.output(this.doc,System.out);

    }

    //Creating the design
    private void createDesign(DesignPatternType DPT) throws IOException {
        Element mDesign = new Element("Design");

        mDesign.setAttribute("ID",String.valueOf(DPT.getID()));


        Element eClass = new Element("typeID");
        eClass.addContent(DPT.getType());


        mDesign.addContent(eClass);

        //Rooted and saved
        root.addContent(mDesign);//Se añade a la raíz
        save();

    }

    //Method to be instantiated to add a given Design Pattern Object
    public void addDesign(DesignPatternType DPT){

        try {
            DesignPatternTypeData data = new DesignPatternTypeData(String.valueOf(Utility.usualTypeFile()));//Gets the usual saveFile
            data.createDesign(DPT); //Creates the design
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }

    }

    public List<DesignPatternType> findAll(){
        List<Element> typesDesigns = root.getChildren();
        List<DesignPatternType> allTypes = new ArrayList<>();
        for (Element type : typesDesigns){
            DesignPatternType designPatternType = new DesignPatternType();
            designPatternType.setID(Integer.parseInt(type.getAttributeValue("ID").toString()));
            designPatternType.setType(type.getChildText("typeID"));
            allTypes.add(designPatternType);
        }
        return allTypes;
    }

    public DesignPatternType xmlToObject(int code) {

        Element save = null;
        List<Element> list = root.getChildren("Design");
        for (Element design : list) {
            String current = design.getAttributeValue("ID");
            if (current.equals(String.valueOf(code))) {
                save = design;
                break;
            }
        }

        if (save!=null) {
            DesignPatternType design = new DesignPatternType();
            design.setID(code);
            design.setType(save.getChildText("typeID"));
            return design;
        }else{
            return null;
        }

    }

}
