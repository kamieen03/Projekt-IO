package pl.put.poznan.transformer.logic;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class TextTransformer {

    private final String[] transforms;

    public TextTransformer(String[] transforms){
        this.transforms = transforms;
    }

    public String transformNumbers(String number){
        return NumberTransformer.transform_numbers(number);
    }

    public String transformUpper(String text){
        return  text.toUpperCase();
    }

    public String transformLower(String text){
        return text.toLowerCase();
    }

    public String transformCapitalize(String text){
        return Character.toUpperCase(text.charAt(0)) + text.substring(1).toLowerCase();
    }

    public String transformInverse(String text){
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
}

