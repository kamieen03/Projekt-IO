package pl.put.poznan.transformer.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

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

    @Test
    public void afterUpper(){
        String[] words = {"a A a", "A a b", "dWa dwa osiem",
                "jeden dwa trzy Trzy cztery", ". . ."};
        String[] expected = {"A", "A B", "DWA OSIEM", "JEDEN DWA TRZY CZTERY", "."};
        Capitalize mock = mock(Capitalize.class);
        when(mock.transform("a A a")).thenReturn("A A A");
        when(mock.transform("A a b")).thenReturn("A A B");
        when(mock.transform("dWa dwa osiem")).thenReturn("DWA DWA OSIEM");
        when(mock.transform("jeden dwa trzy Trzy cztery"))
                .thenReturn("JEDEN DWA TRZY TRZY CZTERY");
        when(mock.transform(". . .")).thenReturn(". . .");
        RemoveDuplicates inv = new RemoveDuplicates(mock);
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], inv.transform(words[i]));
            verify(mock).transform(words[i]);
        }
        verify(mock, times(words.length)).transform(anyString());
    }

}