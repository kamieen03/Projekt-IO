package pl.put.poznan.transformer.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LowerCaseTest {
    private LowerCase t = null;

    @Before
    public void setUp() throws Exception {
        TextTransformer transformer = new SimpleText();
        t = new LowerCase(transformer);
    }

    @After
    public void tearDown() throws Exception {
        t = null;
    }

    @Test
    public void transformUpper() {
        String[] words = {"ABC", " ABC GDE", "A r D u I N o", ".XYZ,", "12S4g78U"};
        String[] expected = {"abc", " abc gde", "a r d u i n o", ".xyz,", "12s4g78u"};
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], t.transform(words[i]));
        }
    }

    @Test
    public void transformLower() {
        String[] words = {" null", "f or ", "in,t", ".", " 1 2 3 "};
        String[] expected = {" null", "f or ", "in,t", ".", " 1 2 3 "};
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], t.transform(words[i]));
        }
    }
}