package groupby;

import java.util.logging.Logger;

public class Row <T extends Object>{
    private Cell<T>[] cells;
    public Row(Cell<T>[] cell){
        this.cells=cell;
    }
    public Cell get(int i){
        return this.cells[i];
    }
    public void set(int index,Cell cell){
        this.cells[index]=cell;
    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<cells.length;i++){
            builder.append(cells[i].toString()).append(",");
        }
        builder.replace(builder.length()-1,builder.length(),"\r\n");
        return builder.toString();
    }
}
