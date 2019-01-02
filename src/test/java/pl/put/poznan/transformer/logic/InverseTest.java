package pl.put.poznan.transformer.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

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

    @Test
    public void afterCapitalize(){
        String[] words = {"Kajak", "xD", "Xd", "XD", "FiLip", "wwa"};
        String[] expected = {"Kajak", "Dx", "Dx", "Dx", "Pilif", "Aww"};
        Capitalize mock = mock(Capitalize.class);
        when(mock.transform("Kajak")).thenReturn("Kajak");
        when(mock.transform("xD")).thenReturn("Xd");
        when(mock.transform("Xd")).thenReturn("Xd");
        when(mock.transform("XD")).thenReturn("Xd");
        when(mock.transform("FiLip")).thenReturn("Filip");
        when(mock.transform("wwa")).thenReturn("Wwa");
        Inverse inv = new Inverse(mock);
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], inv.transform(words[i]));
            verify(mock).transform(words[i]);
        }
        verify(mock, times(words.length)).transform(anyString());
    }
}