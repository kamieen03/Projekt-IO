package pl.put.poznan.transformer.logic;

public class BlankSignsFormater extends TextTransformerDecorator {


    public BlankSignsFormater(TextTransformer decoratedText) {
        super(decoratedText);
    }

    @Override
    public String transform(String text) {
        return formatBlankSigns(super.transform(text));
    }

    private String formatBlankSigns(String text){
        StringBuilder formated = new StringBuilder();
        char c;
        for(int i = 0; i < text.length(); i++){
            c = text.charAt(i);
            switch (c){
                case ' ':
                    formated.append('\u00B7');
                    break;
                case '\n':
                    formated.append('\u00B6');
                    System.out.println("Jest newline");
                    break;
                case '\t':
                    formated.append('\u2192');
                    System.out.println("Jest tab");
                    break;
                default:
                    formated.append(c);
            }
        }
        return formated.toString();
    }


}
