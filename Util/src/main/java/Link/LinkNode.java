package Link;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkNode {
    int val;
    protected LinkNode next;
    public LinkNode(int val){
        this.val=val;
    }
}
