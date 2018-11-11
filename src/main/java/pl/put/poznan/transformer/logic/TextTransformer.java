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
        String[] words = splitBeforeTransform(text);
        String result = "";
        for (int i = 0; i< words.length; i++){
            words[i] = transformSingleWord(words[i]);
        }
        return String.join(" ", words);
    }

    private String transformSingleWord(String text){
        switch (transforms[0]){
            case "upper": return this.transformUpper(text);
            case "lower": return this.transformLower(text);
            case "capitalize": return this.transformCapitalize(text);
            case "inverse": return this.transformInverse(text);
            case "nr_to_words": return this.transformNumbers(text);
            case "words_to_nr":  return "";
        }
        return "";
    }

    private String transformNumbers(String number){
        return NumberTransformer.transform_numbers(number);
    }

    private String transformUpper(String text){
        return  text.toUpperCase();
    }

    private String transformLower(String text){
        return text.toLowerCase();
    }

    private String transformCapitalize(String text){
        return Character.toUpperCase(text.charAt(0)) + text.substring(1).toLowerCase();
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

    private String[] splitBeforeTransform(String text){
        return text.split(" ");
    }
}

