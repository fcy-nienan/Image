package Reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ReferencedObject {
    private long longValue;
    private float floatValue;
    private byte[] bytes;
    public ReferencedObject(int size){
        if (size<0){
            throw new IllegalArgumentException("size can not be a negative value!"+size);
        }
        this.bytes=new byte[size*1024*1024];
    }
    public ReferencedObject(){
        this(0);
    }
}
