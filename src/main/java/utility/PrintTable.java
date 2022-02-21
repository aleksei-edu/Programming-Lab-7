package utility;

import exception.TableSizeException;

import java.util.ArrayList;
import java.util.List;

public class PrintTable extends NicePrint{
    private CreateTableTemplate crtTableTmpl;
    private TableSizes tableSizes;

    public PrintTable(CreateTableTemplate crtTableTmpl, TableSizes tableSizes){
        this.crtTableTmpl = crtTableTmpl;
        this.tableSizes = tableSizes;
    }

    public String createTable(){
        String table = "";
        int lineLenght = 0;
        try{
            if (tableSizes.arrayOfSizes.size() != crtTableTmpl.tmpTable.get(0).size()) {
                throw new TableSizeException("Размеры TableSizes и TableTemplate не совпадают");
            }
            for (Integer index : tableSizes.arrayOfSizes){
                lineLenght+=index;
            }
            for (int y = 0; y < crtTableTmpl.tmpTable.size(); y++){
                table+="|"+"-".repeat(lineLenght) + "|" +"\n";
                table+="|";
                for (int x = 0; x < crtTableTmpl.tmpTable.get(y).size();x++){
                    table+=crtTableTmpl.tmpTable.get(y).get(x) +
                            " ".repeat(tableSizes.arrayOfSizes.get(x) - crtTableTmpl.tmpTable.get(y).get(x).length())+"|";
                }
            }
        }
        catch (TableSizeException e){
            e.printStackTrace();
        }
        return table;
    }

    public class TableSizes{
        private ArrayList<Integer> arrayOfSizes;

        public TableSizes(ArrayList<Integer> sizes){
            this.arrayOfSizes = sizes;
        }

    }

    public class CreateTableTemplate{
        private ArrayList<ArrayList<String>> tmpTable;

        public CreateTableTemplate(ArrayList<ArrayList<String>> tmpTable){
            this.tmpTable = tmpTable;
        }
    }
}
