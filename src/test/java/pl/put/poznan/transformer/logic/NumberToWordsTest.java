package pl.put.poznan.transformer.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NumberToWordsTest {
    private NumberToWords t = null;

    @Before
    public void setUp() throws Exception {
        TextTransformer transformer = new SimpleText();
        t = new NumberToWords(transformer);
    }

    @After
    public void tearDown() throws Exception {
        t = null;
    }


    @Test
    public void tranform(){
        String[] words = {"1 , 2",
                "-2 | 3",
                "7 ; 2.6 , 0.9",
                "0.03 ; 9 ; 9",
                "63 ; 123",
                "0 3   5"
        };
        String[] expected = {"jeden , dwa",
                "minus dwa | trzy",
                "siedem ; dwa i sześć dziesiątych , dziewięć dziesiątych",
                "trzy setne ; dziewięć ; dziewięć",
                "sześćdziesiąt trzy ; sto dwadzieścia trzy",
                "zero trzy   pięć"
        };
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], t.transform(words[i]));
        }
    }
}