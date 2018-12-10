package pl.put.poznan.transformer.logic;

public class LowerCase extends TextTransformerDecorator {
    public LowerCase(TextTransformer decoratedText) {
        super(decoratedText);
    }

    @Override
    public String transform(String text) {
        return super.transform(text).toLowerCase(); // + to lower case
    }
}
