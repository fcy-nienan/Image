import java.util.logging.Logger;

public class fb {
    private static long start,end;
    public static void main(String args[]) throws Exception {
        int x=11;
//        System.out.println("计算斐波那契数列第"+x+"项");
//        start();
//        System.out.println(fbIterate(x));
//        endAndDisAndStart("迭代");
//        System.out.println(fbTailRecursive(x-2, 1, 1));
//        endAndDisAndStart("非递归(尾递归)");
//        System.out.println(fbdp(x));
//        endAndDisAndStart("dp");
        for (int i=3;i<60;i++) {
            fbRecursive(i);
            System.out.println(i+"---"+xxx);
            xxx=0;
        }
//        endAndDisAndStart("递归");
    }
    private static  int xxx=0;
    public static long fbRecursive(long x){
        if(x==1||x==2){
            return 1;
        }
        xxx++;
        return fbRecursive(x-1)+fbRecursive(x-2);
    }
    public static long fbIterate(long x){
        long one=1,two=1,num=one;
        for (int i=0;i<x-2;i++){
            num=one+two;
            one=two;
            two=num;
        }
        return num;
    }
    public static long fbTailRecursive(long x,long first,long second){
        if (x==0)return second;
        return fbTailRecursive(x-1,second,first+second);
    }
    public static long fbdp(int x){
        long[] dp=new long[x];
        dp[0]=1;
        dp[1]=1;
        for (int i=2;i<x;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[x-1];
    }

    private static void start(){
        start=System.nanoTime();
    }
    private static void endAndDisAndStart(String msg){
        end=System.nanoTime();
        System.out.println(msg+"  cost time:"+(end-start)+"纳秒");
        start=System.nanoTime();
    }

}
