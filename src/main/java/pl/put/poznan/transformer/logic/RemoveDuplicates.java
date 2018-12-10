package pl.put.poznan.transformer.logic;

public class RemoveDuplicates extends TextTransformerDecorator {
    public RemoveDuplicates(TextTransformer decoratedText) {
        super(decoratedText);
    }

    @Override
    public String transform(String text) {
        return removeDuplicatedWords(super.transform(text));
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
