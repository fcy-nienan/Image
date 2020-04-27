package groupby;

import scala.collection.parallel.FactoryOps;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.logging.Logger;

public class TableOps {
    private static Random random=new Random();
    private static final int DEFAULT_THRESHOLD=20;

    public static void fill(Table table){

    }
    public static Cell getCell(){
        return new Cell(random.nextInt(DEFAULT_THRESHOLD));
    }
    public static Row getRow(int c){
        Cell[] cell=new Cell[c];
        for (int i=0;i<c;i++){
            cell[i]=getCell();
        }
        return new Row(cell);
    }
    public static Table getTable(int r,int c){
        Row[] rows=new Row[r];
        for (int i=0;i<r;i++){
            rows[i]=getRow(c);
        }
        return new Table(rows,r,c);
    }
//            System.out.printf("%3d|%4d|%4d|\r\n",new Object[12]);

    private static final int DEFAULT_DISTANCE=4;
    private static final String DEFAULT_SEPARATOR="|";
    private static final String DEFAULT_OUTLINE="-";
    public static SoutParam getTableDisplayParam(Table table){
        int r=table.getR(),c=table.getC();
        StringBuilder format=new StringBuilder();
        List<Object> list=new ArrayList<>();
        format.append(appendLimit(c,DEFAULT_DISTANCE,DEFAULT_OUTLINE));
        for (int i=0;i<r;i++){
            for (int j=0;j<c;j++){
                format.append(DEFAULT_SEPARATOR+"%"+DEFAULT_DISTANCE+"s");
                list.add(table.getRows()[i].get(j));
            }
            format.append(DEFAULT_SEPARATOR+"\r\n");
            format.append(appendLimit(c,DEFAULT_DISTANCE,DEFAULT_OUTLINE));
        }
        return new SoutParam(format.toString(),list.toArray());
    }
    private static String appendLimit(int cells,int distance,String separator){
        StringBuilder builder=new StringBuilder();
        builder.append(DEFAULT_OUTLINE);
        int newDis=distance+separator.length();
        int sum=newDis*cells;
        for (int i=0;i<sum;i++){
         builder.append(DEFAULT_OUTLINE);
        }
        builder.append("\r\n");
        return builder.toString();
    }



    public static void groupBy(Table table,int index){
        Object[][] objects = table.toArray();
        Map<Object,List<Object[]>> map=new TreeMap();
        for (int i=0;i<objects.length;i++){
            if (map.containsKey(objects[i][index])){
                map.get(objects[i][index]).add(objects[i]);
            }else{
                List<Object[]> list=new ArrayList<>();
                list.add(objects[i]);
                map.put(objects[i][index],list);
            }
        }
        Iterator<Map.Entry<Object, List<Object[]>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Object, List<Object[]>> next = iterator.next();
            System.out.println(next.getKey());
            List<Object[]> value = next.getValue();
            for (Object[] objects1 : value) {
                System.out.println(Arrays.toString(objects1));
            }
            System.out.println("-----------");
        }
    }
}
