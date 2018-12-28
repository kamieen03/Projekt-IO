package pl.put.poznan.transformer.logic;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NumberToWords extends TextTransformerDecorator{
    public NumberToWords(TextTransformer decoratedText) {
        super(decoratedText);
    }

    @Override
    public String transform(String text) {
        //return nrToWords(super.transform(text));    // + number to words
        return Arrays.stream(text.split(" "))
                .map(this::nrToWords)
                .collect(Collectors.joining(" "));
    }


    private String nrToWords(String text){
        if(isNumber(text))
            return NumberTransformer.transform_numbers(text);
        return text;
    }

    private boolean isNumber(String text){
        return text.matches("-?\\d+(\\.\\d+)?");
    }
}
