package pl.put.poznan.transformer.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShortcutTransformerTest {
    private ShortcutTransformer sh = null;

    @Before
    public void setUp() throws Exception {
        sh = new ShortcutTransformer();
    }

    @After
    public void tearDown() throws Exception {
        sh = null;
    }

    @Test
    public void toFullForm() {
        String[] words = {"mgr", "nr", "str.", "tj.", "pl.", "zob." };
        String[] expected = {"magister", "numer", "strona", "to jest", "plac", "zobacz"};
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], sh.toFullForm(words[i]));
        }
    }

    @Test
    public void toShortcut() {
        String[] words = {"Unia Europejska", "ulica", "Rzeczpospolita Polska", "to znaczy"};
        String[] expected = {"UE", "ul.", "RP", "tzn."};
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], sh.toShortcut(words[i]));
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
        String[] words = {"xD", " abc ", " , o s iemnaście"};
        String[] expected = {"xD", " abc ", " , o s iemnaście"};
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], sh.toShortcut(words[i]));
        }
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], sh.toFullForm(words[i]));
        }
    }
}