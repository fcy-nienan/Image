package util;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

//假如n是composite，then  n = a*b
//
//不妨设a <= b，所以有 a*a <= a*b = n，两面开平方，就有 a <= sqrt(n) 了
//
//所以 trivial division 算法只需要计算到根号n就可以了
public class getAllPrime {
    private static Logger logger = Logger.getLogger(getAllPrime.class.getName());

    public static void main(String args[]) throws Exception {
        long start=System.currentTimeMillis();
        System.out.println(getAllPrimeOne(1111111).size());
        long end=System.currentTimeMillis();
        System.out.println("cost time:"+(end-start));
    }
//86538   560
    public static List<Integer> getAllPrimeOne(int n){
        List<Integer> list=new ArrayList<>();
        for(int i=1;i<=n;i++){
            if (isPrime(i)){
                list.add(i);
            }
        }
        return list;

    }
    private static boolean isPrimeOptimitic(int n){
        if (n%24==0)return false;
        int sqrt= (int) Math.sqrt(n);
        for(int i=2;i<=sqrt;i++){
            if (n%i==0){
                return false;
            }
        }
        return true;
    }
    private static boolean isPrime(int n){
        for(int i=2;i<n;i++){
            if (n%i==0){
                return false;
            }
        }
        return true;
    }
}
