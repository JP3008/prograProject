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

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

public class DesignPatternData {
    private Element root;
    private Document doc;
    private String routeDocument;

    public DesignPatternData(String routeDocument) throws IOException, JDOMException {
        File file = new File(routeDocument);

        if (!file.exists()) {
            this.routeDocument = routeDocument;
            this.root = new Element("Designs");
            this.doc = new Document(root);
            save();
        } else {
            SAXBuilder saBuilder = new SAXBuilder();
            saBuilder.setIgnoringElementContentWhitespace(true);
            this.doc = saBuilder.build(new File(routeDocument));
            this.root = doc.getRootElement();
            this.routeDocument = routeDocument;
        }
    }

    private void save() throws IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(this.doc, new PrintWriter(this.routeDocument));
    }

    private void createDesign(DesignPattern DP) throws IOException {
        Element mDesign = new Element("Design");
        mDesign.setAttribute("ID", String.valueOf(DP.getDesignID()));
        mDesign.setAttribute("typeID", String.valueOf(DP.getType()));

        Element eContext = new Element("Context");
        eContext.addContent(DP.getContext());

        Element eProblem = new Element("Problem");
        eProblem.addContent(DP.getProblem());

        Element eSolution = new Element("Solution");
        eSolution.addContent(DP.getSolution());

        Element eExample = new Element("Example");
        eExample.addContent(DP.getExample());

        Element eImage = new Element("Image");
        eImage.addContent(DP.getImage());

        mDesign.addContent(eContext);
        mDesign.addContent(eProblem);
        mDesign.addContent(eSolution);
        mDesign.addContent(eExample);
        mDesign.addContent(eImage);

        root.addContent(mDesign);
        save();
    }

    public void addDesign(DesignPattern DP) {
        try {
            createDesign(DP);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String deleteDesign(int code) throws IOException {
        boolean deleted = false;
        List<Element> list = root.getChildren("Design");
        for (Element design : list) {
            String current = design.getAttributeValue("ID");
            if (current.equals(String.valueOf(code))) {
                root.removeContent(design);
                deleted = true;
                save();
                break;
            }
        }

        if (deleted) {
            return "The design " + code + " has been deleted";
        } else {
            return "ID not found";
        }
    }

    public void modifyDesign(int code, DesignPattern newPattern) throws IOException {
        Element save = null;
        List<Element> list = root.getChildren("Design");
        for (Element design : list) {
            String current = design.getAttributeValue("ID");
            if (current.equals(String.valueOf(code))) {
                save = design;
                break;
            }
        }

        if (save != null) {
            save.getAttribute("typeID").setValue(String.valueOf(newPattern.getType()));
            save.getChild("Context").setText(newPattern.getContext());
            save.getChild("Problem").setText(newPattern.getProblem());
            save.getChild("Example").setText(newPattern.getExample());
            save.getChild("Solution").setText(newPattern.getSolution());
            save.getChild("Image").setText(newPattern.getImage());

            save();
        }
    }

    public DesignPattern xmlToObject(int code) {
        Element save = null;
        List<Element> list = root.getChildren("Design");
        for (Element design : list) {
            String current = design.getAttributeValue("ID");
            if (current.equals(String.valueOf(code))) {
                save = design;
                break;
            }
        }

        if (save != null) {
            DesignPattern design = new DesignPattern();
            design.setDesignID(Integer.parseInt(save.getAttributeValue("ID")));
            design.setTypeAsNumber(Integer.parseInt(save.getAttributeValue("typeID")));
            design.setContext(save.getChildText("Context"));
            design.setSolution(save.getChildText("Solution"));
            design.setProblem(save.getChildText("Problem"));
            design.setExample(save.getChildText("Example"));
            design.setImageAs64(save.getChildText("Image"));
            return design;
        } else {
            return null;
        }
    }

    public List<DesignPattern> getAll() {
        List<Element> data = root.getChildren();
        List<DesignPattern> designs = new ArrayList<>();
        DesignPattern aux = null;
        for (int i = 1; i <= Utility.getMaxID(Utility.usualDataFile()); i++) {
            if (xmlToObject(i) != null) {
                aux = xmlToObject(i);
                designs.add(aux);
            }
        }
        return designs;
    }

    public List<Integer> getTypesID(String name) throws IOException, JDOMException {

        List<Integer> types = new ArrayList<>();
        List<Element> elementList= root.getChildren("Design");
        for (Element element : elementList) {

            int saveInt = Integer.parseInt(element.getAttributeValue("typeID"));

            DesignPatternTypeData dataType = new DesignPatternTypeData(String.valueOf(Utility.usualTypeFile()));
            String typeName = dataType.xmlToObject(saveInt).getType();

            if (name.equals(typeName)){
                int saveID = Integer.parseInt(element.getAttributeValue("ID"));
                types.add(saveID);
            }

        }
        return types;
    }
}

