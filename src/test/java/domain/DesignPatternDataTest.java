package domain;

import cr.ac.ucr.progra2.paraiso.prograproject.domain.DesignPattern;
import cr.ac.ucr.progra2.paraiso.prograproject.domain.DesignPatternData;
import org.jdom2.JDOMException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class DesignPatternDataTest {

    @Test
    public void Test() throws IOException, JDOMException {


        DesignPattern dp = new DesignPattern("Context2","Problem2","Solution2","Example","class","C:\\Users\\d4ni3\\OneDrive\\Escritorio\\F-RE2c4a4AAA4z8.jpg");
        DesignPatternData dt = new DesignPatternData("File.xml");
        dt.addDesign(dp);

    }

}