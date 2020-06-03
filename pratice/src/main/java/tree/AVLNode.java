package tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AVLNode{
    public int height;
    public int value;
    public AVLNode left;
    public AVLNode right;
    public AVLNode(int value){
        this.value=value;
        this.height=1;
    }
}
