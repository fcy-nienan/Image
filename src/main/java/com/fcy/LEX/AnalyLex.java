package com.fcy.LEX;

import java.util.ArrayList;
import java.util.List;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-09-10  14:56
 */
public class AnalyLex {
    static List<LexResult> results=new ArrayList<>();
    public static void dis(){
        results.forEach(e->{
            System.out.println(e);
        });
    }
}
