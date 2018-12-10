package pl.put.poznan.transformer.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InverseTest {
    private Inverse t = null;
    @Before
    public void setUp() throws Exception {
        TextTransformer transformer = new SimpleText();
        t = new Inverse(transformer);
    }

    @After
    public void tearDown() throws Exception {
        t = null;
    }

    @Test
    public void transformLower(){
        String[] words = {".", "abcdefgh", "kajak", "a b", "xy "};
        String[] expected = {".", "hgfedcba", "kajak", "b a", " yx"};
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], t.transform(words[i]));
        }
    }

    @Test
    public void transformUpper(){
        String[] words = {"Kajak", "xD", "Xd", "XD", "FiLip", "Wwa"};
        String[] expected = {"Kajak", "dX", "Dx", "DX", "PiLif", "Aww"};
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], t.transform(words[i]));
        }
    }
}