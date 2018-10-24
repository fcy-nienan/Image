package com.fcy.Strange;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Force {
    public static void main(String[] args) {
        test1();
    }
    public static boolean compare3(int a,int b,int c){
        if(a==b&&a==c&&b==c)
            return true;
        else
            return false;
    }
    public static boolean compare2(int a,int b){
        return a==b?true:false;
    }
    public static int getMaxCount(int[] i){
        Arrays.sort(i);
        int a=0,b=0,c=0,d=0;
        for(int n=0;n<i.length;n++){
            if (i[n] == 1)
                a++;
            if (i[n] == 2)
                b++;
            if (i[n] == 3)
                c++;
            if (i[n] == 4) {
                d++;
            }
        }
        int max=Math.max(Math.max(a,b),Math.max(c,d));
        int min=Math.min(Math.min(a,b),Math.min(c,d));
        return max-min;
    }
    public static int getMinCount(int[] i) {
        Arrays.sort(i);
        int a = 0, b = 0, c = 0, d = 0;
        int m = 0;
        for (int n = 0; n < i.length; n++) {
            if (i[n] == 1)
                a++;
            if (i[n] == 2)
                b++;
            if (i[n] == 3)
                c++;
            if (i[n] == 4) {
                d++;
            }
        }
        int mincount=compare4(a,b,c,d);
        if(a==mincount)
            return 1;
        if(b==mincount)
            return 2;
        if(c==mincount)
            return 3;
        if(d==mincount)
            return 4;
        return 0;
    }
//    返回四个数中的最小值
    public static int compare4(int a,int b,int c,int d){
        return compare0(compare0(a,b),compare0(c,d));
    }
    public static int compare0(int a,int b){
        return a>b?b:a;
    }
    public static void test1(){
        int state=0;
        for(int p1=1;p1<=4;p1++){
            for(int p2=1;p2<=4;p2++){
                for(int p3=1;p3<=4;p3++){
                    for(int p4=1;p4<=4;p4++){
                        for(int p5=1;p5<=4;p5++){
                            for(int p6=1;p6<=4;p6++){
                                for(int p7=1;p7<=4;p7++){
                                    for(int p8=1;p8<=4;p8++){
                                        for(int p9=1;p9<=4;p9++){
                                            for(int p10=1;p10<=4;p10++){
                                                if(Math.abs(p2-p5)==2) {
                                                    state++;
                                                }
                                                switch(p3){
                                                    case 1:{
                                                        if(p6!=p3&&p2!=p3&&p4!=p3)
                                                            state++;
                                                        break;
                                                    }
                                                    case 2:{
                                                        if(p6!=p3&&p6!=p2&&p6!=p4){
                                                            state++;
                                                        }
                                                        break;
                                                    }
                                                    case 3:{
                                                        if(p2!=p3&&p2!=p6&&p2!=p4){
                                                            state++;
                                                        }
                                                        break;
                                                    }
                                                    case 4:{
                                                        if(p4!=p2&&p4!=p3&&p4!=p6){
                                                            state++;
                                                        }
                                                        break;
                                                    }
                                                    default:{
                                                        break;
                                                    }
                                                }
                                                switch(p4){
                                                    case 1:{
                                                        if(p1==p5){
                                                            state++;
                                                        }
                                                        break;
                                                    }
                                                    case 2:{
                                                        if(p2==p7){
                                                            state++;
                                                        }
                                                        break;
                                                    }
                                                    case 3:{
                                                        if(p1==p9){
                                                            state++;
                                                        }
                                                        break;
                                                    }
                                                    case 4:{
                                                        if(p6==p10){
                                                            state++;
                                                        }
                                                        break;
                                                    }
                                                }
                                                switch(p5){
                                                    case 1:{
                                                        if(p5==p8){
                                                            state++;
                                                        }
                                                        break;
                                                    }
                                                    case 2:{
                                                        if(p5==p4)
                                                            state++;
                                                        break;
                                                    }
                                                    case 3:{
                                                        if(p5==p9)
                                                            state++;
                                                        break;
                                                    }
                                                    case 4:{
                                                        if(p5==p7)
                                                            state++;
                                                        break;
                                                    }
                                                }
                                                switch(p6){
                                                    case 1:{
                                                        if(compare3(p2,p4,p8))
                                                            state++;
                                                        break;
                                                    }
                                                    case 2:{
                                                        if(compare3(p1,p6,p8))
                                                            state++;
                                                        break;
                                                    }
                                                    case 3:{
                                                        if(compare3(p3,p8,p10))
                                                            state++;
                                                        break;
                                                    }
                                                    case 4:{
                                                        if(compare3(p5,p9,p8))
                                                            state++;
                                                        break;
                                                    }
                                                }
                                                int[] s={p1,p2,p3,p4,p5,p6,p7,p8,p9,p10};
                                                int min=getMinCount(s);
                                                switch(p7){
                                                    case 1:{
                                                        if(min==3)
                                                            state++;
                                                        break;
                                                    }
                                                    case 2:{
                                                        if(min==2)
                                                            state++;
                                                        break;
                                                    }
                                                    case 3:{
                                                        if(min==1)
                                                            state++;
                                                        break;
                                                    }
                                                    case 4:{
                                                        if(min==4)
                                                            state++;
                                                        break;
                                                    }
                                                }
                                                switch(p8){
                                                    case 1:{
                                                        if(Math.abs(p7-p1)!=1)
                                                            state++;
                                                        break;
                                                    }
                                                    case 2:{
                                                        if(Math.abs(p5-p1)!=1)
                                                            state++;
                                                        break;
                                                    }
                                                    case 3:{
                                                        if(Math.abs(p2-p1)!=1)
                                                            state++;
                                                        break;
                                                    }
                                                    case 4:{
                                                        if(Math.abs(p10-p1)!=1)
                                                            state++;
                                                        break;
                                                    }
                                                }
                                                switch(p9){
                                                    case 1:{
                                                        if(p1==p6||p6==p5)
                                                            state++;
                                                        break;
                                                    }
                                                    case 2:{
                                                        if(p1==p6||p10==p5)
                                                            state++;
                                                        break;
                                                    }
                                                    case 3:{
                                                        if(p1==p6||p2==p5)
                                                            state++;
                                                        break;
                                                    }
                                                    case 4:{
                                                        if(p1==p6||p9==p5)
                                                            state++;
                                                        break;
                                                    }
                                                }
                                                int maxcount=getMaxCount(s);

                                                switch(p10){
                                                    case 1:{
                                                        if(maxcount==3)
                                                            state++;
                                                    }
                                                    case 2:{
                                                        if(maxcount==2)
                                                            state++;
                                                        break;
                                                    }
                                                    case 3:{
                                                        if(maxcount==4)
                                                            state++;

                                                        break;
                                                    }
                                                    case 4:{
                                                        if(maxcount==1)
                                                            state++;
                                                        break;

                                                    }
                                                }

                                                if(state==9){
                                                    System.out.println(p1+"  "+p2+"  "+p3+"  "+p4+"  "+p5+"  "+p6+"  "+p7+"  "+p8+"  "+p9+"  "+p10);
                                                }
                                                state=0;


                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}

