package link;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ListNode {
    int val;//默认修饰符,同包访问
    protected ListNode next;
    public ListNode(int val){
        this.val=val;
    }
}
