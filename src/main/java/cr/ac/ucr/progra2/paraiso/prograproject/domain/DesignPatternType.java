package cr.ac.ucr.progra2.paraiso.prograproject.domain;

import cr.ac.ucr.progra2.paraiso.prograproject.util.Utility;

public class DesignPatternType {
    private int ID;
    private String type;


    public DesignPatternType(String type) {
        this.ID = Utility.getMaxID(Utility.usualTypeFile());
        this.type = type;
    }

    public DesignPatternType(int id, String type){
        this.ID = id;
        this.type = type;
    }

    public DesignPatternType(int id) {
        this.ID = id;
    }

    public DesignPatternType(){

    }

    public int getID() {
        return ID;
    }

    public void setID() {
        this.ID = Utility.getMaxID(Utility.usualTypeFile());
    }


    public void setID(int ID) {
        this.ID = ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
