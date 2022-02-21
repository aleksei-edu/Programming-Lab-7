import utility.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        FileManager.setEnv("LABA");
        CollectionManager.loadCollection();
//        ArrayList<ArrayList<String>> list = new ArrayList<>();
//
//        list.add(new ArrayList(Arrays.asList("qw","qwert")));
//        list.add(new ArrayList(Arrays.asList("qwer","qwe")));
//        ArrayList<Integer> ArrayofMax = new ArrayList<>();
//        for (int x = 0; x < list.size(); x++){
//            ArrayofMax.add(x,0);
//        }
//        ArrayList<Integer> ArrayOfSize = new ArrayList<>();
//        for (int y = 0; y < list.size();y++){
//            for(int x = 0; x < list.get(y).size();x++){
//                ArrayofMax.set(x,Math.max(ArrayofMax.get(x),list.get(y).get(x).length()));
//            }
//        }
//        PrintTable pt = new PrintTable(new PrintTable.CreateTableTemplate(list),new PrintTable.TableSizes(ArrayofMax));
        while(true){
            ConsoleManager.interactiveMode();
        }
    }
}

