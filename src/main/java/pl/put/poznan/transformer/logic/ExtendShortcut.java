package pl.put.poznan.transformer.logic;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ExtendShortcut extends TextTransformerDecorator{
    Map<String,String> shortcutsMap;

    public ExtendShortcut(TextTransformer decoratedText) {
        super(decoratedText);
        shortcutsMap = DictonaryLoader.loadDictonaryFromCSV("src/main/resources/shortcuts.csv");
    }

    @Override
    public String transform(String text) {
        text = super.transform(text);
            return Arrays.stream(text.split(" "))
                    .map(s -> toFullForm(s))
                    .collect(Collectors.joining( " "));
    }

    public String toFullForm(String shortcut){

        StringBuilder transformed = new StringBuilder(shortcutsMap.getOrDefault(shortcut, shortcut));
        for (int i = 0; i<shortcut.length()-1; i++){
            if (Character.isUpperCase(shortcut.charAt(i))){
                transformed.setCharAt(i, shortcut.charAt(i));
            }
        }
        return transformed.toString();
    }

}
