package pl.put.poznan.transformer.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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

    @Test
    public void afterLowerCase(){
        String[] words = {"Abc", "ABC", "abc", "aBC"};
        String[] expected = {"Abc", "Abc", "Abc", "Abc"};
        LowerCase mock = mock(LowerCase.class);
        when(mock.transform("Abc")).thenReturn("abc");
        when(mock.transform("ABC")).thenReturn("abc");
        when(mock.transform("abc")).thenReturn("abc");
        when(mock.transform("aBC")).thenReturn("abc");
        Capitalize c = new Capitalize(mock);
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], c.transform(words[i]));
            verify(mock).transform(words[i]);
        }
        verify(mock, times(words.length)).transform(anyString());
    }
}