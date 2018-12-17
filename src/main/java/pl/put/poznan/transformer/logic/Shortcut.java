package pl.put.poznan.transformer.logic;

public class Shortcut extends TextTransformerDecorator {
    public Shortcut(TextTransformer decoratedText) {
        super(decoratedText);
    }

    @Override
    public String transform(String text) {
        ShortcutTransformer st = new ShortcutTransformer();
        String[] words = text.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String word: words) {
            String transformedWord = st.toShortcut(word);
            builder.append(transformedWord+" ");
        }
        return builder.toString().substring(0, builder.toString().length() - 1);
    }
}
