package link;

public class getDecimalValue {
    public static void main(String[] args) {
        ListNode listNode = LinkUtil.create(new int[]{1, 0,0, 1});
        int decimalValue = getDecimalValue(listNode);
        System.out.println(decimalValue);
    }
    public static int getDecimalValue(ListNode head) {
        if (head==null)return 0;
        int len=0;
        ListNode p=head;
        while (p!=null){
            len++;
            p=p.next;
        }
        int sum=0;
        while (head!=null){
            int val=head.val;
            sum+=(val<<(len-1));
            len--;
            head=head.next;
        }
        return sum;
    }
    /*遍历一遍?*/
    public static int getDecimalValue1(ListNode head){
        int sum=0;
        while (head!=null){
            sum=sum*2+head.val;
        }
        return sum;
    }
}
