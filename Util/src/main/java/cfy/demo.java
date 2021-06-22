package cfy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class demo {
    public static void main(String[] args) throws Exception{
//        16 a 2
//        1010
//        2    36
        System.out.println(getHex("ab"));
        Thread t=new Thread();
//        t.join();
        DateFormat format=DateFormat.getDateInstance(DateFormat.LONG,Locale.CHINA);
        String pattern1 = "yyyyMMHHssmmdd";
        String pattern2 = "yyyyddHHssmmMM";
        Date date1 = new SimpleDateFormat(pattern1).parse("20201212125613");
        System.out.println(format.format(date1));
        System.out.println(date1);
        Date date2 = new SimpleDateFormat(pattern2).parse("20201212125813");
        System.out.println(format.format(date2));
        System.out.println(date2);
        long subTime = date2.getTime()-date1.getTime();
        System.out.println(subTime);
        System.out.println(2678520000l);


        String one="20201212125613";
        String two="20201212125813";

    }
    public static String transfer(String str){
        String[] s = str.split(" ");
        int fromBase=Integer.parseInt(s[0]);
        int twoBase=Integer.parseInt(s[2]);
        String middle=s[1];
        if (fromBase==16){
            int hex = getHex(middle);
            System.out.println(Integer.toBinaryString(hex));
        }else{

        }

        return "";
    }
    public static int getBin(String middle){
        for (int i=0;i<middle.length();i++){

        }
        return 1;
    }
    public static int getHex(String middle){
        char[] chars = middle.toCharArray();
        int x=0;
        for (int i=0;i<chars.length-1;i++){
            if (chars[i]>=97){
                x+=(chars[i]-87)*16;
            }else{
                x+=(chars[i]-48)*16;
            }
        }
        if (chars[chars.length-1]>=97){
            x+=chars[chars.length-1]-87;
        }else{
            x+=chars[chars.length-1]-48;
        }
        return x;
    }


    public static String getMaxDep(){
        Scanner sc = new Scanner(System.in);
        int lines = sc.nextInt();
        String[][] dep=new String[lines][2];
        sc.nextLine();
        for(int i = 0; i < lines; i++){
            dep[i]=sc.nextLine().split(" ");
            System.out.println(Arrays.toString(dep[i]));
        }
        Map<String,Integer> map=new HashMap();
        int max=Integer.MIN_VALUE;
        int newValue=1;
        for (int i=0;i<lines;i++){
            if (map.containsKey(dep[i][0])){
                newValue=map.get(dep[i][0])+1;
                map.put(dep[i][1],newValue);
            }else{
                newValue=1;
                map.put(dep[i][1],newValue);
            }
            if (max<newValue){
                max=newValue;
            }
        }
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Integer> next = iterator.next();
            if (next.getValue().intValue()==max){
                System.out.println(next.getKey());
            }
        }
        return "";
    }

//    4
//    pkgA pkgB
//    pkgA pkgD
//    pkgB pkgC
//    pkgC pkgD


}
