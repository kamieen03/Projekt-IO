package pl.put.poznan.transformer.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleTextTest {
    private SimpleText t = null;

    @Before
    public void setUp() throws Exception {
        t = new SimpleText();
    }

    @After
    public void tearDown() throws Exception {
        t = null;
    }

    @Test
    public void transform() {
        String[] words = {"a", " xD ", ". . . ", " -123.9"};
        String[] expected = {"a", " xD ", ". . . ", " -123.9"};
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], t.transform(words[i]));
        }
    }
}