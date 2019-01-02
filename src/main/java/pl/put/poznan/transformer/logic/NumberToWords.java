package pl.put.poznan.transformer.logic;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NumberToWords extends TextTransformerDecorator{
    private NumberTransformer nt = new NumberTransformer();

    public NumberToWords(TextTransformer decoratedText,
                         NumberTransformer n) {
        super(decoratedText);
        if (n != null)
            nt = n;
    }

    @Override
    public String transform(String text) {
        text = super.transform(text);    // + number to words
        return Arrays.stream(text.split(" "))
                .map(this::nrToWords)
                .collect(Collectors.joining(" "));
    }


    private String nrToWords(String text){
        if(isNumber(text)){
            return nt.transformNumber(text);
        }
        return text;
    }

    private boolean isNumber(String text){
        return text.matches("-?\\d+(\\.\\d+)?");
    }
}
