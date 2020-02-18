package Advanced.Concurrent.Thread;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * Author:fcy
 * Date:2020/2/13 13:49
 */
public class MultiObject {
    public static void main(String[] args) {
        int[] arr=new int[]{5,4,3,2,1};
        Arrays.sort(arr,0,2);
        System.out.println(Arrays.toString(arr));
    }
}
