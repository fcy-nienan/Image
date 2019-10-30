package Grammer.Base;

/**
 * @descripiton: 测试各种拼接字符串的效率
 * @author: fcy
 * @date: 2018-08-20  17:56
 */
public class TestStringContact {
    public static void main(String args[]) {
        testContact();
    }
    public static void testContact(){
        String s="";
        long start=System.currentTimeMillis();
        for(int i=0;i<10000;i++){
            s+=i+"";
        }
        long end=System.currentTimeMillis();
        System.out.println("Cose:"+(end-start));

        s="";
        start=System.currentTimeMillis();
        for(int i=0;i<10000;i++){
            s.concat(""+i);
        }
        end=System.currentTimeMillis();
        System.out.println("Cost:"+(end-start));

        StringBuilder builder=new StringBuilder();
        start=System.currentTimeMillis();
        for(int i=0;i<10000;i++){
            builder.append(i);
        }
        end=System.currentTimeMillis();
        System.out.println("Cost:"+(end-start));
    }
}
