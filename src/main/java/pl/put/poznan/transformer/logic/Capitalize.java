package pl.put.poznan.transformer.logic;

/**
 * Dekorator.
 * Umożliwia dodanie transformacji zmiany pierwszej litery każego słowa na wielką,
 * a reszty na małe w tekscie
 * do obiektu implementującgo interfejs TextTransformer;
 */
public class Capitalize extends TextTransformerDecorator {
    public Capitalize(TextTransformer decoratedText) {
        super(decoratedText);
    }

    /**
     * Zamienia pierwszą literę każdego wyrazu na wielką, a resztę na małe.
     * @param text tekst poddawany transformacji
     * @return przetransformowany tekst
     */
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
