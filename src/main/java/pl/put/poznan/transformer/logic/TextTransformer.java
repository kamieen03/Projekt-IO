package pl.put.poznan.transformer.logic;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class TextTransformer {

    private final String[] transforms;

    public TextTransformer(String[] transforms){
        this.transforms = transforms;
    }

    public String transform(String text){

        for (String transform: transforms){
            switch (transform){
                case "upper": text = this.transformUpper(text);
                    break;
                case "lower": text = this.transformLower(text);
                    break;
                case "capitalize": text = this.transformCapitalize(text);
                    break;
                case "inverse": text = this.transformInverse(text);
                    break;
                case "nr_to_words": text = this.transformNumbers(text);
                    break;
                case "removeDuplicatedWords": text = this.removeDuplicatedWords(text);
                    break;
            }
        }

        return text;
    }

    private String transformNumbers(String text){
        String[] words = text.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String word: words) {
            String transformedWord = NumberTransformer.transform_numbers(word);
            builder.append(transformedWord+" ");
        }
        return builder.toString().substring(0, builder.toString().length() - 1);

    }

    private String transformUpper(String text){
        return  text.toUpperCase();
    }

    private String transformLower(String text){
        return text.toLowerCase();
    }

    private String transformCapitalize(String text){
        String[] words = text.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String word: words) {
            String transformedWord = Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
            builder.append(transformedWord+" ");
        }
        return builder.toString().substring(0, builder.toString().length() - 1);
    }

    private String transformInverse(String text){
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

