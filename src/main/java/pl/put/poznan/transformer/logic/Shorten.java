package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
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
        text = super.transform(text);
        String[] splitUp = text.split(" ");
        ArrayList<String> newText = new ArrayList<>();
        if(splitUp.length == 1) newText.add(splitUp[0]);
        else {
            for (int i = 0; i < splitUp.length; i++) {
                if ( i != splitUp.length - 1 && shortcutsMap.containsValue(splitUp[i] + " " + splitUp[i + 1])) {
                    newText.add(splitUp[i] + " " + splitUp[i + 1]);
                    i++;
                } else {
                    newText.add(splitUp[i]);
                }
            }
        }

        return Arrays.stream(newText.toArray(new String[newText.size()]))
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
