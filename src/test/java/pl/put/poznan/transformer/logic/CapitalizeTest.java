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
    public void transform(){
        String[] words = {"a b c", "Jeden", "dwa", "f", "ABC", ". A. bC;", ";cd"};
        String[] expected = {"A B C", "Jeden", "Dwa", "F", "Abc", ". A. Bc;", ";cd"};
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], t.transform(words[i]));
        }
    }
}