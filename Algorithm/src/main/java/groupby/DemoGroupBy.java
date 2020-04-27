package groupby;

import java.io.Console;
import java.util.*;
import java.util.logging.Logger;

public class DemoGroupBy {
    private static Logger logger = Logger.getLogger(DemoGroupBy.class.getName());

    public static void main(String args[]) throws Exception {
        Table table = TableOps.getTable(5, 5);
        displayTable(table);
        TableOps.groupBy(table,1);

    }
    private static void displayTable(Table table){
        SoutParam tableDisplayParam = TableOps.getTableDisplayParam(table);
        String format=tableDisplayParam.getFormat();
        Object o=tableDisplayParam.getArgs();
        Object[] objects= (Object[]) o;
        System.out.printf(format,objects);
    }

}
