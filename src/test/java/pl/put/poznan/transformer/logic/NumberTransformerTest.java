package pl.put.poznan.transformer.logic;
import org.junit.Test;

import static org.junit.Assert.*;

public class NumberTransformerTest {

    @Test
    public void transform_numbers() {
        int[] numbers = {0,1,-1,100,20,38,999, 1000, 1005, 3512, 8000000, -987654321};
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
            result = NumberTransformer.transform_numbers(String.valueOf(numbers[i]));
            assertEquals(names[i], result);
        }

    }
}