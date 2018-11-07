package pl.put.poznan.transformer.logic;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class NumberTransformer {

    public static String transform_numbers(String liczba){
        boolean ujemna = false;
        if (liczba.charAt(0) == '-'){
            ujemna = true;
            liczba = liczba.substring(1);
        }
        if (liczba.equals("0")){
            return "zero";
        }

        int len = liczba.length();
        //System.out.println(liczba);
        String result = "";
        String temp;
        int a = 0;
        for(int i = len; i >0; i = i - 3) {
            int from = Integer.max(i-3, 0);
            String slice = liczba.substring(from, i);
            if (slice.length() <3){
                slice = String.format("%3s", slice).replace(' ', '0');
            }
            temp = NumberTransformer.processOneSlice(slice);
            if (a>0 && temp.equals("jeden")) temp = "";
            temp = temp.concat(" ");
            if (a == 1) temp = temp.concat(NumberArray.THOUSANDS.get(NumberArray.mapping(slice)));
            if (a == 2) temp = temp.concat(NumberArray.MILIONS.get(NumberArray.mapping(slice)));
            temp = temp.concat(" ");
            result = temp.concat(result);
            a++;
        }
        if (ujemna) result = "minus ".concat(result);
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

}



class NumberArray{
    public static ArrayList<String> SMALL;
    public static ArrayList<String> TENS;
    public static ArrayList<String> HUNDREDS;
    public static ArrayList<String> THOUSANDS;
    public static ArrayList<String> MILIONS;

    static {
        try {
            SMALL = NumberArray.read().get("small");
            TENS = NumberArray.read().get("tens");
            HUNDREDS = NumberArray.read().get("hundreds");
            THOUSANDS = NumberArray.read().get("thousands");
            MILIONS = NumberArray.read().get("milions");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static HashMap<String, ArrayList<String>> read() throws IOException {
        HashMap<String, ArrayList<String>> numbersDict = new HashMap<>();
        File file = new File("src/liczby.txt");
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
        for (String typ:numbersDict.keySet()) {
            System.out.println(numbersDict.get(typ));
        }
        return numbersDict;
    }

    public static int mapping(String slice){
        //na podstawie trzycofrowego slice funkcja decyduje, czy wybrać np.
        // "tysiąc", "tysiące" czy "tysięcy"
        int val = Integer.valueOf(slice.substring(1, 3));
        if (val == 1) return 0;
        if ((val % 10 == 2 || val %10 == 3 || val % 10 == 4) && slice.charAt(1) != '1'){
            return 1;
        }
        return 2;
    }
}
