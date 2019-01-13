package pl.put.poznan.transformer.logic;

import java.util.Map;
import java.util.stream.Collectors;

public class Latex extends TextTransformerDecorator {

    private Map<String,String> specialCharacters;

    public Latex(TextTransformer decoratedText) {
        super(decoratedText);
        specialCharacters = DictonaryLoader.loadDictonaryFromCSV("src/main/resources/latexSpecials.csv");
    }

    @Override
    public String transform(String text) {
        text = super.transform(text);
        return text.chars()
                .mapToObj(s -> (char) s)
                .map(this::toLatex)
                .collect(Collectors.joining());
    }

    private String toLatex(Character character){
        if (specialCharacters.containsKey(character.toString())){
            return specialCharacters.get(character.toString());
        } else {
            return character.toString();
        }


    }
}
