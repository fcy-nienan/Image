package skill;

import java.lang.reflect.Field;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-06-27  16:31
 *
 * ~取反运算符
 * ^异或运算符
 */
public class BitDemo {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        System.out.println(8>>>1);
        System.out.println(-8>>>1);
    }
    public static void swap(Integer a,Integer b) throws NoSuchFieldException, IllegalAccessException {
        int tmp=a.intValue();
        Class clazz=Integer.class;
        Field f=clazz.getDeclaredField("value");
        f.setAccessible(true);
        f.set(a,b.intValue());
        f.setInt(b,tmp);
        f.set(b,tmp);
    }
//  a^b=diff (diff为a,b的差异)
//
//a^diff=b (a减去[a,b的差异diff]等于b)
//
//b^diff=a (b减去[a,b的差异diff]等于a)

//    a^b=a和b的并集减去a和b的交集


//    抑或,不同为1相同为0
//    异或交换两个值?
//    8    1000
//    7    0111
//    8^7= 1111=15
//    15^7=1000=8
//    15^8=0111=7



//    无符号右移
//    8>>>1   00001000   00000100
//    -8>>>1  10001000   01000100

    public static void swap(int a,int b){
        a=a^b;
        b=a^b;
        a=a^b;
        System.out.println(a);
        System.out.println(b);
    }
    public static int turn(int value,int index){
        int a=1;
        a=a<<index;
        return value^a;
    }
}
