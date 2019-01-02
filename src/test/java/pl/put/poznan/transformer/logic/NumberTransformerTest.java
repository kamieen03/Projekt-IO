package pl.put.poznan.transformer.logic;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NumberTransformerTest {
    private NumberTransformer nt;
    @Before
    public void setUp() throws Exception {
        nt = new NumberTransformer();
    }

    @After
    public void tearDown() throws Exception {
        nt = null;
    }

    @Test
    public void transformIntegers() {
        int[] numbers = {0, 1, -1, 100, 20, 38, 999, 1000, 1005, 3512, 8000000, -987654321};
        String[] names = {
                "zero",
                "jeden",
                "minus jeden",
                "sto",
                "dwadzieścia",
                "trzydzieści osiem",
                "dziewięćset dziewięćdziesiąt dziewięć",
                "tysiąc",
                "tysiąc pięć",
                "trzy tysiące pięćset dwanaście",
                "osiem milionów",
                "minus dziewięćset osiemdziesiąt siedem milionów sześćset pięćdziesiąt " +
                        "cztery tysiące trzysta dwadzieścia jeden"};
        String result;
        for (int i = 0; i < numbers.length; i++) {
            result = nt.transformNumber(String.valueOf(numbers[i]));
            assertEquals(names[i], result);
        }

    }

    @Test
    public void transformFloats(){
        double[] numbers = {1.01, 2.1, 0.12, -0.56, -24.31, -1.62, 45.1, -3.02, -9.2, 3.4, 56.7};
        String[] names = {
                "jeden i jedna setna",
                "dwa i jedna dziesiąta",
                "dwanaście setnych",
                "minus pięćdziesiąt sześć setnych",
                "minus dwadzieścia cztery i trzydzieści jeden setnych",
                "minus jeden i sześćdziesiąt dwie setne",
                "czterdzieści pięć i jedna dziesiąta",
                "minus trzy i dwie setne",
                "minus dziewięć i dwie dziesiąte",
                "trzy i cztery dziesiąte",
                "pięćdziesiąt sześć i siedem dziesiątych"
        };
        String result;
        for (int i = 0; i < numbers.length; i++) {
            result = nt.transformNumber(String.valueOf(numbers[i]));
            assertEquals(names[i], result);
        }
    }
}