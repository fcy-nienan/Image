package groupby;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.logging.Logger;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Table<T> {
    private Row<T>[] rows;
    private int r;
    private int c;
    public Table(Row[] rows){
        this.rows=rows;
    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        for (int i=0;i<rows.length;i++){
            builder.append(rows[i].toString());
        }
        return builder.toString();
    }
    public Object[][] toArray(){
        Object[][] objects=new Object[r][c];
        for (int i=0;i<r;i++){
            for (int j=0;j<c;j++){
                objects[i][j]=rows[i].get(j).get();
            }
        }
        return objects;
    }
}
