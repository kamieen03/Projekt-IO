package pl.put.poznan.transformer.logic;

public class Capitalize extends TextTransformerDecorator {
    public Capitalize(TextTransformer decoratedText) {
        super(decoratedText);
    }

    @Override
    public String transform(String text) {
        return capitalize(super.transform(text)); // + capitalize
    }

    private String capitalize(String text){
        String[] words = text.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String word: words) {
            String transformedWord = Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
            builder.append(transformedWord+" ");
        }
        return builder.toString().substring(0, builder.toString().length() - 1);
    }
}
