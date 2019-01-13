package com.fcy.Net.NATTransfromation;

import java.util.HashMap;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-01-12  20:28
 */
public class CommandUtils {
    private static HashMap<Integer,Command> hashMap=new HashMap<>();
    static {
//        hashMap.put(1,new Command(1,))
    }
    public static Command getCommand(int tag){
        return hashMap.get(tag);
    }
}
