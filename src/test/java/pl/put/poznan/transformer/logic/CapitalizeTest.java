package pl.put.poznan.transformer.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CapitalizeTest {
    private Capitalize t = null;

    @Before
    public void setUp() throws Exception {
        TextTransformer transformer = new SimpleText();
        t = new Capitalize(transformer);
    }

    @After
    public void tearDown() throws Exception {
        t = null;
    }

    @Test
    public void transformNonCapitalized(){
        String[] words = {"a b c", "dwa", "f"};
        String[] expected = {"A B C", "Dwa", "F"};
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], t.transform(words[i]));
        }
    }

    @Test
    public void transformCapitalized(){
        String[] words = {"OSIEM", "Dwa", "JEdEN", ". A. bC;", ";cd"};
        String[] expected = {"Osiem", "Dwa", "Jeden", ". A. Bc;", ";cd"};
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], t.transform(words[i]));
        }
    }
}