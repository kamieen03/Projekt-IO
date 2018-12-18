package pl.put.poznan.transformer.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LatexTest {
    private Latex t = null;

    @Before
    public void setUp() throws Exception {
        TextTransformer transformer = new SimpleText();
        t = new Latex(transformer);
    }

    @After
    public void tearDown() throws Exception {
        t = null;
    }
    @Test
    public void transform() {
        String[] words = {"#", "$", "%", "\\"};
        String[] expected = {"\\#", "\\$", "\\%", "\\textbackslash{}"};
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], t.transform(words[i]));
        }
    }
}