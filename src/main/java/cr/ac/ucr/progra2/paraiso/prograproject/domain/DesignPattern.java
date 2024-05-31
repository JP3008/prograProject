package cr.ac.ucr.progra2.paraiso.prograproject.domain;

import cr.ac.ucr.progra2.paraiso.prograproject.util.Utility;

import java.io.IOException;


public class DesignPattern {

    //Attributes
    private int designID;
    private int type;
    private String context;
    private String problem;
    private String solution;
    private String example;
    private String image;

    //Different kind of constructors
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
    }

    public int getDesignID() {
        return designID;
    }

    public void setDesignID(int designID) {
        this.designID = designID;
    }

//Getter and setter

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

    public void setImageAs64(String encodedImage){
        this.image = encodedImage;
    }

    public void setTypeAsNumber(int type){
        this.type = type;
    }

    public void setType(DesignPatternType type) {
        this.type = type.getID();
    }

    public int getType() {
        return this.type;
    }

//To string


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




