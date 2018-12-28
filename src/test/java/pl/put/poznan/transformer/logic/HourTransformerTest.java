package pl.put.poznan.transformer.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class HourTransformerTest {
    private HourTransformer t = null;

    @Before
    public void setUp() throws Exception {
        TextTransformer transformer = new SimpleText();
        t = new HourTransformer(transformer);
    }

    @After
    public void tearDown() throws Exception {
        t = null;
    }

    @Test
    public void transform() {
        String[] words = {"2:0", "0:1", "21:16", "17:59"};
        String[] expected = {"druga zero",
                            "jeden po północy",
                            "dwudziesta pierwsza szesnaście",
                            "siedemnasta pięćdziesiąt dziewięć"};
        for (int i = 0; i < words.length; i++) {
            assertEquals(expected[i], t.transform(words[i]));
        }
    }
}