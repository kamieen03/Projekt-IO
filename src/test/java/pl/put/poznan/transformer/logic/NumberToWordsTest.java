package pl.put.poznan.transformer.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class NumberToWordsTest {
    private NumberToWords t = null;

    @Before
    public void setUp() throws Exception {
        TextTransformer transformer = new SimpleText();
        t = new NumberToWords(transformer, null);
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

    @Test
    public void mockNumberTransformer(){
        String[] words = {"0.7 , 2", "1.0", "4", "2 -4"};
        String[] expected = {"siedem dziesiątych , dwa", "jeden","cztery", "dwa minus cztery"};
        NumberTransformer mock = mock(NumberTransformer.class);
        when(mock.transformNumber(",")).thenReturn(",");
        when(mock.transformNumber("0.7")).thenReturn("siedem dziesiątych");
        when(mock.transformNumber("2")).thenReturn("dwa");
        when(mock.transformNumber("1.0")).thenReturn("jeden");
        when(mock.transformNumber("4")).thenReturn("cztery");
        when(mock.transformNumber("-4")).thenReturn("minus cztery");
        NumberToWords nw = new NumberToWords(new SimpleText(), mock);
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], nw.transform(words[i]));
            for(String w : words[i].split(" ")) {
                count++;
            }
        }
        verify(mock, times(count - 1)).transformNumber(anyString());
    }

}