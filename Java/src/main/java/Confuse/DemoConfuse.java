package Confuse;

import java.io.File;

/*
 * Author:fcy
 * Date:2020/2/5 13:58
 */
public class DemoConfuse {
    public static void main(String[] args) {

    }
    public static void one(){
        Long x=new Long(12);
        Long y=new Long(12);
        System.out.println(x==y);//false
        System.out.println(x.equals(y));//true
    }
    public static void checkDir(){
        String path="D:\\upload";
        File file=new File(path);
        System.out.println(file.exists());
        System.out.println(file.isDirectory());
    }
    public static void breakLabel(){
//        下面这段代码我是想一直输出try,但运行后发现只输出了一次
//        按我之前的理解是break connect之后不是应该跳到了开始吗,但实际是直接跳出了connect这个标签
//        那么可以这样理解,connect定义了一个标签,然后下面的代码块都是这个标签范围内的
//        然后break 这个标签代表着跳出这个标签代码的范围
        connect:
        try{
            Thread.sleep(3000);
            System.out.println("try");
            System.out.println(1/0);
        }catch (Exception e){
            break connect;
        }
        t:
        for (int i=0;i<10;i++){
            System.out.println(i);
            if (i==1){
                break t;
            }
        }
        int i=3,j;
        outter:while(i>0){
            j=3;
            inner:while(j>0){
                if(j<2) break outter;
                System.out.println(i+"and"+j);
                j--;
            }
            i--;
        }
    }
}
