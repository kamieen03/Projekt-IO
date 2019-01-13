package pl.put.poznan.transformer.logic;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class HourTransformer  extends TextTransformerDecorator{
    private Map<String,String> hourMap;

    public HourTransformer(TextTransformer decoratedText) {
        super(decoratedText);
        hourMap = DictonaryLoader.loadDictonaryFromCSV("src/main/resources/hours.csv");
    }

    @Override
    public String transform(String text) {
        text = super.transform(text);
        return Arrays.stream(text.split(" "))
                .map(this::timeToWords)
                .collect(Collectors.joining(" "));
    }

    private String timeToWords(String text){
        if (text.contains(":") && text.split(":").length == 2){
            String hour = text.split(":")[0];
            String minutes = text.split(":")[1];
            if (0 <= Integer.valueOf(hour) && Integer.valueOf(hour) <= 23 &&
                0 <= Integer.valueOf(minutes) && Integer.valueOf(minutes) <= 59) {
                String res = "";
                if (Integer.valueOf(hour) != 0)
                    res += hourMap.get(hour) + " ";
                res += (new NumberToWords(new SimpleText(), null)).transform(minutes);
                if (Integer.valueOf(hour) == 0)
                    res += " po północy";
                return res;
            }
            return text;
        }
        return text;
    }
}
