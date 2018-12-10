package pl.put.poznan.transformer.logic;

public abstract class TextTransformerDecorator implements TextTransformer{
    protected final TextTransformer decoratedText;

    protected TextTransformerDecorator(TextTransformer decoratedText) {
        this.decoratedText = decoratedText;
    }

    @Override
    public String transform(String text) {
        return text;
    }


}
