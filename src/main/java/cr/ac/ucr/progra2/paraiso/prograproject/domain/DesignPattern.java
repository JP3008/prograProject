package cr.ac.ucr.progra2.paraiso.prograproject.domain;

import cr.ac.ucr.progra2.paraiso.prograproject.util.Utility;
import org.jdom2.JDOMException;

import java.io.IOException;


import java.io.IOException;
import java.io.Serializable;

import java.io.IOException;
import java.io.Serializable;

public class DesignPattern implements Serializable {
    private static final long serialVersionUID = 1L;

    // Attributes
    private String typeAsString;
    private int designID;
    private int type;
    private String context;
    private String problem;
    private String solution;
    private String example;
    private String image;

    // Constructors
    public DesignPattern(DesignPatternType type, String context, String problem, String solution, String example, String image) throws IOException {
        this.designID = Utility.getMaxID(Utility.usualDataFile());
        this.type = type.getID();
        this.context = context;
        this.problem = problem;
        this.solution = solution;
        this.example = example;
        this.image = Utility.encode(image);
    }

    public DesignPattern() {
        // Default constructor
    }

    // Getters and Setters
    public int getDesignID() {
        return designID;
    }

    public void setDesignID(int designID) {
        this.designID = designID;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String imagePath) throws IOException {
        this.image = Utility.encode(imagePath);
    }

    public void setImageAs64(String encodedImage) {
        this.image = encodedImage;
    }

    public int getType() {
        return this.type;
    }

    public void setTypeAsNumber(int type) {
        this.type = type;
    }

    public void setType(DesignPatternType type) {
        this.type = type.getID();
    }
    public String getTypeAsString() throws IOException, JDOMException {
        return Utility.getDesignType(this.type).getType();
    }

    public void setTypeAsString(String typeAsString) {
        this.typeAsString = typeAsString;
    }

    @Override
    public String toString() {
        return "DesignPattern{" +
                "designID=" + designID +
                ", type=" + type +
                ", context='" + context + '\'' +
                ", problem='" + problem + '\'' +
                ", solution='" + solution + '\'' +
                ", example='" + example + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}






