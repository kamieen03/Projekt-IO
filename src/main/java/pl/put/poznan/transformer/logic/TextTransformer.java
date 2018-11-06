package pl.put.poznan.transformer.logic;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class TextTransformer {

    private final String[] transforms;

    public TextTransformer(String[] transforms){
        this.transforms = transforms;
    }

    public String transform(String text){
        // of course normally it would to something based on transforms
        return text.toUpperCase();
    }



    public String transformNumbers(String number){
        return NumberTransformer.transform_numbers(number);
    }
}

