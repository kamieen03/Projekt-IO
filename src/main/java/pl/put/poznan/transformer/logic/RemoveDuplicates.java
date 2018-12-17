package pl.put.poznan.transformer.logic;
/**
 * Dekorator.
 * Umożliwia dodanie transformacji usunięcia z tekstu występujących
 * kolejno duplikatów do obiektu implementującgo interfejs TextTransformer;
 */
public class RemoveDuplicates extends TextTransformerDecorator {
    public RemoveDuplicates(TextTransformer decoratedText) {
        super(decoratedText);
    }

    /**
     * Usuwa duplikaty (występujące kolejno identyczne
     * łańcuchy znaków) z tekstu.
     * @param text tekst poddawany transformacji
     * @return odwrócony tekst
     */
    @Override
    public String transform(String text) {
        return removeDuplicatedWords(super.transform(text)); // + remove duplicates
    }

    private String removeDuplicatedWords(String text){
        String[] words = text.split(" ");

        if(words.length==0) return "";
        if(words.length==1) return words[0].toString();

        StringBuilder output = new StringBuilder();
        output.append(words[0]);
        for (int i =1; i<words.length; i++){
            if (!words[i].equals(words[i-1])){
                output.append(" "+words[i]);
            }
        }
        return output.toString();
    }
}
