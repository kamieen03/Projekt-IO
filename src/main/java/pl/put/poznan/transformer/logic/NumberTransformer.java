package pl.put.poznan.transformer.logic;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;


/**
 * Transformator liczb.
 * Wywołując statyczną metodę {@code transform_numbers} można dokonać
 * transformacji liczby na język polski.
 * Zakres liczb: [-10^9 + 1; 10^9 - 1].
 * Maksymalnie dwa miejsca po przecinku.
 */
public class NumberTransformer {

    /**
     * Zwraca liczbę przetransformowaną na język polski.
     *
     * @param liczba transformowana liczba zapisana jako jako ciąg cyfr z ewentualnym
     *        minusem na początku
     * @return liczba przetransformowana na język polski jako słowa oddzielone
     *         spacjami
     */
    public String transformNumber(String liczba){
        for (char c : liczba.toCharArray()){
            if (!Character.isDigit(c) && c!='.' && c!='-') return liczba;
        }

        boolean decimal = false;
        String coma="";
        if(liczba.contains(".")){
            String[] parts = liczba.split("[.]");
            liczba = parts[0];
            coma = parts[1];
            decimal = true;
        }

        boolean ujemna = false;
        if (liczba.charAt(0) == '-'){
            ujemna = true;
            liczba = liczba.substring(1);
        }

        if (liczba.length() > 9) return liczba;
        if (liczba.equals("0") && !decimal){
            return "zero";
        }

        int len = liczba.length();
        ArrayList<String> resultArr = new ArrayList<>();
        String temp;
        int a = 0;
        for(int i = len; i >0; i = i - 3) {
            int from = Integer.max(i-3, 0);
            String slice = liczba.substring(from, i);
            if (slice.length() <3){
                slice = String.format("%3s", slice).replace(' ', '0');
            }
            temp = NumberTransformer.processOneSlice(slice);
            if (a>0) {
                if (temp.equals("jeden")) temp = "";
                if (a == 1 && !slice.equals("000")) resultArr.add(NumberArray.THOUSANDS.get(NumberArray.mapping(slice)));
                if (a == 2 && !slice.equals("000")) resultArr.add(NumberArray.MILIONS.get(NumberArray.mapping(slice)));
            }
            if (!temp.equals("")) resultArr.add(temp);
            a++;
        }
        String result = "";
        Collections.reverse(resultArr);
        for(String part: resultArr){
            result = result.concat(part);
            result = result.concat(" ");
        }
        if (result.length() > 0)
            result = result.substring(0, result.length() - 1);
        if (ujemna) result = "minus ".concat(result);
        if(decimal) {
            if(liczba.charAt(0) != '0') result += " i ";
            result+= handleComa(coma);
        }
        return result;
    }

    private static String processOneSlice(String slice){

        ArrayList<String> words = new ArrayList<>();

        if (slice.equals("000")) return "";
        if ('0' != slice.charAt(0)){
            String hundreds = NumberArray.HUNDREDS.get(slice.charAt(0) - '0' - 1);
            words.add(hundreds);
        }
        int part = Integer.parseInt(slice.substring(1,3));
        if (part != 0) {
            if (0 < part && part < 20) {
                words.add(NumberArray.SMALL.get(part));
            } else {
                int tens = slice.charAt(1) - '0';
                int units = slice.charAt(2) - '0';
                words.add(NumberArray.TENS.get(tens - 2));
                if (units > 0) {
                    words.add(NumberArray.SMALL.get(units));
                }
            }
        }
        String result = "";
        for (String word:words) {
            result = result.concat(word);
            result = result.concat(" ");
        }
        result = result.substring(0, result.length() - 1);

        return result;
    }

    private static String handleComa(String slice) {
        boolean decimal = slice.length() == 1;
        int val = Integer.valueOf(slice);
        String result = "";
        if (val == 1) {
            if (decimal) result += "jedna dziesiąta";
            else result += "jedna setna";
        } else if(val == 2){
           if(decimal) result += "dwie dziesiąte";
           else result += "dwie setne";
        } else if (val >= 3 && val <= 19) {
            result += processOneSlice(
                    String.format("%3s", slice).replace(' ', '0')
            );
            if(decimal && val <= 4) result += " dziesiąte";
            else if(decimal) result += " dziesiątych";
            else if(val <=4) result += " setne";
            else result += " setnych";
        } else if(2 <= val % 10 && val%10 <= 4){
            result += NumberArray.TENS.get(slice.charAt(0) - '0' - 2);
            if (val %2 == 0) result += " dwie";
            else if(val %3 == 0) result += " trzy";
            else result += " cztery";
            result += " setne";
        } else{
            result += processOneSlice(
                    String.format("%3s", slice).replace(' ', '0')
            );
            result += " setnych";
        }
        return result;
    }

}


/**
 * Klasa - kontener przechowująca fragmenty liczb (jako słowa) potrzebne do konstrukcji
 * całości
 * fragmenty są przechowywane w listach SMALL, TENS, itd., ładownaych z pliku liczby.txt
 * klasa jest ładowana w trakcie pierwszego wywołania transformNumber z klasy NumberTransformer
 * dotarcza również funkcję mapping wymaganą do odmiany słów "tysiąc" i "milion"
 *
 * SMALL :1-19
 * TENS : 20-90, co 10
 * HUNDREDS: 100 - 900, co 100
 * THOUSANDS: tysiąc, tysiące, tysięcy
 * MILONS: milion, miliony, milionów
 */
class NumberArray{
    public static ArrayList<String> SMALL;
    public static ArrayList<String> TENS;
    public static ArrayList<String> HUNDREDS;
    public static ArrayList<String> THOUSANDS;
    public static ArrayList<String> MILIONS;

    static {
        try {
            HashMap<String, ArrayList<String>> dict = NumberArray.read();
            SMALL = dict.get("small");
            TENS = dict.get("tens");
            HUNDREDS = dict.get("hundreds");
            THOUSANDS = dict.get("thousands");
            MILIONS = dict.get("milions");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Odczytuje fragmenty nazw liczb z pliku liczby.txt
     *
     * @return haszmapa postaci typ(np. "small" labo "tens"): [słowa]
     * @throws IOException
     */
    public static HashMap<String, ArrayList<String>> read() throws IOException {
        HashMap<String, ArrayList<String>> numbersDict = new HashMap<>();
        File file = new File("src/main/resources/liczby.txt");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String st;
        String type = null;
        ArrayList<String> currNumberArr = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>(Arrays.asList
                ("small", "tens", "hundreds", "thousands", "milions")
        );
        while ((st = br.readLine()) != null) {
            if (types.contains(st)) {
                if (type!=null) {
                    ArrayList<String> copy = (ArrayList<String>) currNumberArr.clone();
                    numbersDict.put(type, copy);
                    currNumberArr.clear();
                }
                type = new String(st);
            }else{
                currNumberArr.add(st);
            }
        }
        numbersDict.put(type, currNumberArr);
        return numbersDict;
    }

    /**
     * Na podstawie trzycofrowego slice funkcja decyduje, czy wybrać np.
     * "tysiąc", "tysiące" czy "tysięcy"
     *
     * @param slice ciąg trzycyfrowy
     * @return indeks w tabelach THOUSANDS i MILIONS
     */
    public static int mapping(String slice){
        int val = Integer.valueOf(slice.substring(1, 3));
        if (val == 1) return 0;
        if ((val % 10 == 2 || val %10 == 3 || val % 10 == 4) && slice.charAt(1) != '1'){
            return 1;
        }
        return 2;
    }
}
