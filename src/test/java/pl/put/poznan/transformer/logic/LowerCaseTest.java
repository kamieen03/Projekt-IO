package pl.put.poznan.transformer.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

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

    @Test
    public void extendCapitalize(){
        String[] words = {"A r D u I N o", ".XyZ,", "12S4g78U"};
        String[] expected = {"a r d u i n o", ".xyz,", "12s4g78u"};
        Capitalize mock = mock(Capitalize.class);
        when(mock.transform("A r D u I N o")).thenReturn("A r d u i n o");
        when(mock.transform(".XyZ,")).thenReturn(".xyz,");
        when(mock.transform("12S4g78U")).thenReturn("12s4g78u");
        UpperCase up = new UpperCase(mock);
        LowerCase low = new LowerCase(up);
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], low.transform(words[i]));
            verify(mock).transform(words[i]);
        }
        verify(mock, times(words.length)).transform(anyString());
    }
}