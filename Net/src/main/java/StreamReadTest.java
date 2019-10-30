


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-06-09  11:17
 */
public class StreamReadTest<E> {
    public static void main(String[] args) throws Exception{
        int[] arr={1,2,3};
        int[] arr1=arr;
        System.out.println(arr1.length);
        List list=Arrays.asList(arr);
        System.out.println(list.size());
        System.out.println(list.get(0));

        Collections.addAll(list,arr);
        Arrays.stream(arr).boxed().collect(Collectors.toList());
        Collectors.toList();


        FileInputStream fileInputStream=new FileInputStream("G:\\ttt.txt");
        BufferedInputStream bufferedInputStream=new BufferedInputStream(fileInputStream);
        int c,count=0;
        long start=System.currentTimeMillis();
        while((c=bufferedInputStream.read())!=-1){
            count+=1;
        }
        long end=System.currentTimeMillis();
        System.out.println("cost time:"+(end-start));

        byte[] bytes=new byte[1024];
        fileInputStream=new FileInputStream("G:\\ttt.txt");
        bufferedInputStream=new BufferedInputStream(fileInputStream);
        start=System.currentTimeMillis();
        count=0;
        while((c=bufferedInputStream.read(bytes))!=-1){
            count+=1;
        }
        end=System.currentTimeMillis();
        System.out.println("cost time:"+(end-start));

    }
}
