package domain;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class DesignPatternData {
    private String routeDocument;
    private Element root;
    private Document doc;


    public DesignPatternData(String routeDocument) throws IOException, JDOMException {
        File file = new File(routeDocument);

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

    private void save() throws IOException {

        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(this.doc,new PrintWriter(this.routeDocument));
        System.out.println("Elemento guardado");
        //xmlOutputter.output(this.doc,System.out);

    }

    private void createDesign(DesignPattern DP) throws IOException {
        Element mDesign = new Element("Design");
        mDesign.setAttribute("ID",String.valueOf(DP.getId())); //Para hacerlo un atributo
        mDesign.setAttribute("Classification",DP.getClassification());

        Element eContext = new Element("Context");
        eContext.addContent(DP.getContext()); //Es un contenido del "objeto"

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

        root.addContent(mDesign);//Se añade a la raíz
        save();

    }

    public void addDesign(DesignPattern DP){

        try {
            DesignPatternData data = new DesignPatternData("file.xml");
            data.createDesign(DP);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }

    }



}
