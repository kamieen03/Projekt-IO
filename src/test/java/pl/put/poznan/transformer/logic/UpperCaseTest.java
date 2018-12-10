package pl.put.poznan.transformer.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UpperCaseTest {
    private UpperCase t = null;

    @Before
    public void setUp() throws Exception {
        TextTransformer transformer = new SimpleText();
        t = new UpperCase(transformer);
    }

    @After
    public void tearDown() throws Exception {
        t = null;
    }


    @Test
    public void transformUpper(){
        String[] words = {"Dwa", "F", "ORACLE"};
        String[] expected = {"DWA", "F", "ORACLE"};
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], t.transform(words[i]));
        }

    }

    @Test
    public void transformLower(){
        String[] words = {"dwa", "f", "oRA cLe", "sun"};
        String[] expected = {"DWA", "F", "ORA CLE", "SUN"};
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], t.transform(words[i]));
        }
    }

    @Test
    public void transformSpecialChar(){
        String[] words = {".", "I.N.T,.El", "2* 7", "micr,osoft"};
        String[] expected = {".", "I.N.T,.EL", "2* 7","MICR,OSOFT"};
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], t.transform(words[i]));
        }
    }
}