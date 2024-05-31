package domain;

import cr.ac.ucr.progra2.paraiso.prograproject.data.DesignPatternData;
import cr.ac.ucr.progra2.paraiso.prograproject.data.DesignPatternTypeData;
import cr.ac.ucr.progra2.paraiso.prograproject.domain.DesignPattern;
import cr.ac.ucr.progra2.paraiso.prograproject.domain.DesignPatternType;
import cr.ac.ucr.progra2.paraiso.prograproject.util.Utility;
import org.jdom2.JDOMException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class DesignPatternDataTest {

    @Test
    public void Test() throws IOException, JDOMException {


        DesignPatternData dt = new DesignPatternData(String.valueOf(Utility.usualDataFile()));
        DesignPattern designPattern = new DesignPattern();

        System.out.println(dt.deleteDesign(1));

        //List<DesignPattern> list =  dt.getAll();
        //for (int i = 0; i<list.size(); i++) {
          //  System.out.println(list.get(i));
        //}
/*
        DesignPatternTypeData data = new DesignPatternTypeData(String.valueOf(Utility.usualTypeFile()));
        DesignPatternType dpt = data.xmlToObject(2);

        System.out.println(dpt);
        System.out.println(dpt.getID());
*/
    }
}

