package tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tree.TireTree.TireTree;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TireTreeNode {
    private char value;
    private Set<TireTreeNode> children;
    private boolean end;
    public TireTreeNode(char c){
        this.value=c;
    }
    public int hashCode(){
        return Integer.hashCode(value);
    }
    public boolean equals(Object o){
        if (o instanceof TireTree){
            if (o.hashCode()==hashCode()){
                return true;
            }
        }
        return false;
    }
}
