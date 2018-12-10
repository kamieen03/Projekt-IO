package pl.put.poznan.transformer.logic;

public class Inverse extends TextTransformerDecorator {
    public Inverse(TextTransformer decoratedText) {
        super(decoratedText);
    }

    @Override
    public String transform(String text) {
        return inverse(super.transform(text)); // + inverse
}

    private String inverse(String text){
        char [] reversed = new char[text.length()];
        for(int i = 0; i < text.length(); i++){
            reversed[i] = text.charAt(text.length() - 1 - i);
            if (Character.isUpperCase(text.charAt(i)) && Character.isLowerCase(reversed[i]))
                reversed[i] = Character.toUpperCase(reversed[i]);
            else if (Character.isLowerCase(text.charAt(i)) && Character.isUpperCase(reversed[i]))
                reversed[i] = Character.toLowerCase(reversed[i]);
           }
        return String.valueOf(reversed);
    }
}
