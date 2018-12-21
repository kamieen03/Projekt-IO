package pl.put.poznan.transformer.logic;

/**
 * Obiekt zwracający nieprzetransformowany tekst.
 * Przy użyciu dekoratorów(Capitalize, Inverse...) można nałożyć dodatkowe transformacje.
 */
public class SimpleText implements TextTransformer{

    /**
     * Zwraca tekst w postatci jaką otrzymał.
     * @param text tekst wejscopwy
     * @return  tekst wyjsciowy
     */
    @Override
    public String transform(String text) {
        return text;
    }
}
