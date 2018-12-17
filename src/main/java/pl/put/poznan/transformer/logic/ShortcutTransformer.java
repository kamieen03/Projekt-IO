package pl.put.poznan.transformer.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ShortcutTransformer{

    Map<String,String> shortcutsMap;

    public ShortcutTransformer(){

        shortcutsMap = loadShortcutsFromCSVFile("src/main/resources/shortcuts2.csv");
    }


    public String toFullForm(String shortcut){
        if (shortcutsMap.containsKey(shortcut)){
            return shortcutsMap.get(shortcut);
        } else {
            return shortcut;
        }
    }

    public String toShortcut(String fullForm){
        if (shortcutsMap.containsValue(fullForm)){
            for (String s : shortcutsMap.keySet()) {
                if (shortcutsMap.get(s).equals(fullForm)) {
                    return s;
                }
            }
            return fullForm;
        } else {
            return fullForm;
        }
    }

    public Map<String, String> loadShortcutsFromCSVFile(String path){
        Map<String,String> map =  new HashMap<String, String>();

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";

        try {
            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                String[] row = line.split(cvsSplitBy);
                map.put(row[0], row[1]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }
}
