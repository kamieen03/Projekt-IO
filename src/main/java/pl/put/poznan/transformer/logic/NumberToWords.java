package pl.put.poznan.transformer.logic;

import java.util.ArrayList;

public class NumberToWords extends TextTransformerDecorator{
    public NumberToWords(TextTransformer decoratedText) {
        super(decoratedText);
    }

    @Override
    public String transform(String text) {
        return nrToWords(super.transform(text));    // + number to words
    }

    private String nrToWords(String text){
        ArrayList<String> words = new ArrayList<>();
        StringBuilder NAN = new StringBuilder();
        StringBuilder num = new StringBuilder();
        for(char c : text.toCharArray()){
            if(Character.isDigit(c) || c=='.' || c=='-') {
                if(NAN.length() > 0) words.add(NAN.toString());
                NAN = new StringBuilder();
                num.append(c);
            }else{
                NAN.append(c);
                if(num.length() > 0) words.add(num.toString());
                num = new StringBuilder();
            }
        }
        if(NAN.length() > 0) words.add(NAN.toString());
        if(num.length() > 0) words.add(num.toString());
        //String[] words = text.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String word: words) {
            String transformedWord = NumberTransformer.transform_numbers(word);
            //builder.append(transformedWord + " ");
            builder.append(transformedWord);
        }
        //return builder.toString().substring(0, builder.toString().length() - 1);
        return builder.toString();
    }
}
