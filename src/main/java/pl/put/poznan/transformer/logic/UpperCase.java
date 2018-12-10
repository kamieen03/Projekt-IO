package pl.put.poznan.transformer.logic;

/**
 * Dekorator.
 * Umożliwia dodanie transformacji zamiany tekstu na wielkie litery
 * do obiektu implementującgo interfejs TextTransformer;
 */
public class UpperCase extends TextTransformerDecorator {
    public UpperCase(TextTransformer decoratedText) {
        super(decoratedText);
    }

    /**
     * Zamienia tekst na napisany wielkimi literami.
     * @param text tekst poddawany trensformacji
     * @return tekst wielkimi literami
     */
    @Override
    public String transform(String text) {
        return super.transform(text).toUpperCase(); // + to upper case
    }
}
