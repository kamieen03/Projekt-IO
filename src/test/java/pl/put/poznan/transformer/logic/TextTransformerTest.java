package pl.put.poznan.transformer.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextTransformerTest {
    private TextTransformer t = null;
    @Before
    public void setUp() throws Exception {
        t = new TextTransformer(null);
    }

    @After
    public void tearDown() throws Exception {
        t = null;
    }

    @Test
    public void testUpperUpper(){
        String[] words = {"Dwa", "F", "ORACLE"};
        String[] expected = {"DWA", "F", "ORACLE"};
        t.setTransform("upper");
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], t.transform(words[i]));
        }

    }

    @Test
    public void testUpperLower(){
        String[] words = {"dwa", "f", "oRA cLe", "sun"};
        String[] expected = {"DWA", "F", "ORA CLE", "SUN"};
        t.setTransform("upper");
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], t.transform(words[i]));
        }
    }

    @Test
    public void testUpperSpecialChar(){
        String[] words = {".", "I.N.T,.El", "2* 7", "micr,osoft"};
        String[] expected = {".", "I.N.T,.EL", "2* 7","MICR,OSOFT"};
        t.setTransform("upper");
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], t.transform(words[i]));
        }
    }

    @Test
    public void testInverseLower(){
        String[] words = {".", "abcdefgh", "kajak", "a b", "xy "};
        String[] expected = {".", "hgfedcba", "kajak", "b a", " yx"};
        t.setTransform("inverse");
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], t.transform(words[i]));
        }
    }

    @Test
    public void testInverseUpper(){
        String[] words = {"Kajak", "xD", "Xd", "XD", "FiLip", "Wwa"};
        String[] expected = {"Kajak", "dX", "Dx", "DX", "PiLif", "Aww"};
        t.setTransform("inverse");
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], t.transform(words[i]));
        }
    }

    @Test
    public void testRemoveDuplicated(){
        String[] words = {"a a a", "a a b", "dwa dwa osiem", "jeden dwa trzy trzy cztery"};
        String[] expected = {"a", "a b", "dwa osiem", "jeden dwa trzy cztery"};
        t.setTransform("removeDuplicatedWords");
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], t.transform(words[i]));
        }
    }

    @Test
    public void testNumberSeries(){
        String[] words = {"1 , 2",
                "-2| 3",
                "7; 2.6 ,0.9",
                "0.03;9;9",
                "63 ; 123",
                "0 3   5"
        };
        String[] expected = {"jeden , dwa",
            "minus dwa| trzy",
            "siedem; dwa i sześć dziesiątych ,dziewięć dziesiątych",
            "trzy setne;dziewięć;dziewięć",
            "sześćdziesiąt trzy ; sto dwadzieścia trzy",
                "zero trzy   pięć"
        };
        t.setTransform("nr_to_words");
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], t.transform(words[i]));
        }
    }

    @Test
    public void testCapitalize(){
        String[] words = {"a b c", "Jeden", "dwa", "f", "ABC", ". A. bC;", ";cd"};
        String[] expected = {"A B C", "Jeden", "Dwa", "F", "Abc", ". A. Bc;", ";cd"};
        t.setTransform("capitalize");
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], t.transform(words[i]));
        }
    }
}