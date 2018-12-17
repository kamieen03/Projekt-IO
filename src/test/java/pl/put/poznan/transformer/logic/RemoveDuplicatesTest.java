package pl.put.poznan.transformer.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RemoveDuplicatesTest {
    private RemoveDuplicates t = null;

    @Before
    public void setUp() throws Exception {
        TextTransformer transformer = new SimpleText();
        t = new RemoveDuplicates(transformer);
    }

    @After
    public void tearDown() throws Exception {
        t = null;
    }

    @Test
    public void transformDuplicated(){
        String[] words = {"a a a", "a a b", "dwa dwa osiem", "jeden dwa trzy trzy cztery", ". . ."};
        String[] expected = {"a", "a b", "dwa osiem", "jeden dwa trzy cztery", "."};
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], t.transform(words[i]));
        }
    }

    @Test
    public void transformNonDuplicated(){
        String[] words = {"a", "raz dwa raz", "x . x ."};
        String[] expected = {"a", "raz dwa raz", "x . x ."};
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], t.transform(words[i]));
        }
    }

}