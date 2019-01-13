package pl.put.poznan.transformer.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class ShortcutsTest {
    private Shorten sh = null;
    private ExtendShortcut es = null;

    @Before
    public void setUp() throws Exception {
        TextTransformer transformer = new SimpleText();
        sh = new Shorten(transformer);
        es = new ExtendShortcut(transformer);
    }

    @After
    public void tearDown() throws Exception {
        sh = null;
    }

    @Test
    public void toFullForm() {
        String[] words = {"mgr", "nr", "str.", "tj.", "pl.", "zob.", "RP" };
        String[] expected = {"magister", "numer", "strona", "to jest",
                "plac", "zobacz", "Rzeczpospolita Polska"};
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], es.transform(words[i]));
        }
    }

    @Test
    public void toShortcut() {
        String[] words = {"Unia Europejska", "ulica", "Rzeczpospolita Polska",
                "to znaczy", "to jest"};
        String[] expected = {"UE", "ul.", "RP", "tzn.", "tj."};
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], sh.transform(words[i]));
        }
    }

    @Test
    public void toShortcutPolishChars() {
        String[] words = {"południowy", "ubiegły", "ciąg dalszy"};
        String[] expected = {"płd.", "ub.", "cd."};
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], sh.toShortcut(words[i]));
        }
    }

    @Test
    public void nonShortcuts() {
        String[] words = {"xD", "abc", ", o s iemnaście"};
        String[] expected = {"xD", "abc", ", o s iemnaście"};
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], sh.transform(words[i]));
        }
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], es.transform(words[i]));
        }
    }

    @Test
    public void shortAfterExtend(){
        String[] words = {"mgr", "nr", "s.", "pl.", "zob." };
        String[] expected = words.clone();
        ExtendShortcut mock = mock(ExtendShortcut.class);
        when(mock.transform("mgr")).thenReturn("magister");
        when(mock.transform("nr")).thenReturn("numer");
        when(mock.transform("s.")).thenReturn("strona");
        when(mock.transform("pl.")).thenReturn("plac");
        when(mock.transform("zob.")).thenReturn("zobacz");
        Shorten c = new Shorten(mock);
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], c.transform(words[i]));
            verify(mock).transform(words[i]);
        }
        verify(mock, times(words.length)).transform(anyString());
    }

    @Test
    public void extendAfterShort(){
        String[] words = {"Unia Europejska", "profesor", "ciąg dalszy"};
        String[] expected = words.clone();
        Shorten mock = mock(Shorten.class);
        when(mock.transform("Unia Europejska")).thenReturn("UE");
        when(mock.transform("profesor")).thenReturn("prof.");
        when(mock.transform("ciąg dalszy")).thenReturn("cd.");
        ExtendShortcut c = new ExtendShortcut(mock);
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], c.transform(words[i]));
            verify(mock).transform(words[i]);
        }
        verify(mock, times(words.length)).transform(anyString());
    }
}