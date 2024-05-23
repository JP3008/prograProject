package domain;

import cr.ac.ucr.progra2.paraiso.prograproject.domain.DesignPattern;
import cr.ac.ucr.progra2.paraiso.prograproject.domain.DesignPatternData;
import org.jdom2.JDOMException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class DesignPatternDataTest {

    @Test
    public void Test() throws IOException, JDOMException {


        DesignPattern dp = new DesignPattern("Context2","Problem2","Solution2","Example","class","C:\\Users\\d4ni3\\OneDrive\\Escritorio\\F-RE2c4a4AAA4z8.jpg",1);
        DesignPatternData dt = new DesignPatternData("File.xml");
        dt.addDesign(dp);



        // 30 10 5 3 15 12 20 50 40 80 70 90
        // 3 5 10 12 15 20 30 40 50 70 80 90
        // 3 5 12 20 15 10 40 70 90 80 50 30

    }

}