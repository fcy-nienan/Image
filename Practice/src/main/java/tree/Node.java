package tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public  class Node {
    public int value;
    public Node left;
    public Node right;
    public Node(int value){
        this.value=value;
    }
}
