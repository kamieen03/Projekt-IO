package pl.put.poznan.transformer.logic;

/**
 * Dekorator.
 * Umożliwia dodanie transformacji zamiany tekstu na małe litery
 * do obiektu implementującgo interfejs TextTransformer;
 */
public class LowerCase extends TextTransformerDecorator {
    public LowerCase(TextTransformer decoratedText) {
        super(decoratedText);
    }

    /**
     * Zamienia tekst na napisany małymi literami.
     * @param text tekst poddawany trensformacji
     * @return tekst małymi literami
     */
    @Override
    public String transform(String text) {
        return super.transform(text).toLowerCase(); // + to lower case
    }
}
