package pl.put.poznan.transformer.logic;

import com.google.cloud.translate.*;
import com.google.cloud.translate.Translate.TranslateOption;

import java.util.ArrayList;
import java.util.List;


public class Translator extends TextTransformerDecorator{
    private final static Translate translator = TranslateOptions.getDefaultInstance().getService();
    private String toLanguage;

    public Translator(TextTransformer decoratedText, String toLanguage) {
        super(decoratedText);
        this.toLanguage = toLanguage;
    }

    @Override
    public String transform(String text) {
        return translate(super.transform(text));
    }

    private String translate(String text){
        Detection detection = translator.detect(text);

        ArrayList<String> as = new ArrayList<>();
        List<Language> languages = translator.listSupportedLanguages();
        for(Language l : languages)
            as.add("\"" + l.getCode() + "\"");
        System.out.println(as);

        Translation translation =
                translator.translate(
                        text,
                        TranslateOption.sourceLanguage(detection.getLanguage()),
                        TranslateOption.targetLanguage(toLanguage));

        return translation.getTranslatedText();
    }

//    public static String getLanguages(){
//        StringBuilder stringLang = new StringBuilder();
//
//        List<Language> languages = translator.listSupportedLanguages();
//        for(Language l : languages)
//            stringLang.append(l.getName() + "," + l.getCode() + ",");
//
//        return stringLang.toString();
//    }


}
