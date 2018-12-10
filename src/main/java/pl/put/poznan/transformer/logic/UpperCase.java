package pl.put.poznan.transformer.logic;

public class UpperCase extends TextTransformerDecorator {
    public UpperCase(TextTransformer decoratedText) {
        super(decoratedText);
    }

    @Override
    public String transform(String text) {
        return super.transform(text).toUpperCase(); // + to upper case
    }
}
