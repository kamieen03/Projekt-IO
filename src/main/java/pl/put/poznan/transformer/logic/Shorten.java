package pl.put.poznan.transformer.logic;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Shorten extends TextTransformerDecorator {

    Map<String,String> shortcutsMap;


    public Shorten(TextTransformer decoratedText) {
        super(decoratedText);
        shortcutsMap = DictonaryLoader.loadDictonaryFromCSV("src/main/resources/shortcuts.csv");
    }

    @Override
    public String transform(String text) {
        return Arrays.stream(text.split(" "))
                .map(s -> toShortcut(s))
                .collect(Collectors.joining( " " ));
    }

    public String toShortcut(String fullForm){

        Optional<String> shortcut =  shortcutsMap.entrySet().stream()
                .filter(s -> s.getValue().toLowerCase().equals(fullForm.toLowerCase()))
                .map(Map.Entry::getKey)
                .findFirst();

        if (shortcut.isPresent()){
            StringBuilder transformed = new StringBuilder(shortcut.get());
            for (int i = 0; i<shortcut.get().length()-1; i++){
                if (Character.isUpperCase(fullForm.charAt(i))){
                    transformed.setCharAt(i, fullForm.charAt(i));
                }
            }
            return transformed.toString();
        } else {
            return fullForm;
        }
    }
}
