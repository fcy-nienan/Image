package ReflectionTest;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;

public class MethodBody {
    private static Logger logger = Logger.getLogger(MethodBody.class.getName());
    private static Random random=new Random();
    private static final String split="-";
    private static Set<String> allocatedID=new HashSet();
    //32位    8-8-8-8    string-number-string-number
    public String computeUUID(){
        String uuid;
        while (true){
            uuid=getOneID();
            if (!allocatedID.contains(uuid)){
                allocatedID.add(uuid);
                break;
            }
        }
        return uuid;
    }
    private String getOneID(){
        StringBuilder builder=new StringBuilder();
        builder.append(getString()).
                append(split).
                append(getNumber()).
                append(split).
                append(getString()).
                append(split).
                append(getNumber());
        return builder.toString();
    }
    private String getString(){
        StringBuilder builder=new StringBuilder();
        for (int i=0;i<8;i++) {
            int k = random.nextInt(26);
            int t = random.nextInt(2);
            if (t == 0) {
                k += 65;
            } else {
                k += 97;
            }
            builder.append((char)k);
        }
        return builder.toString();
    }
    private String getNumber(){
        StringBuilder builder=new StringBuilder();
        for (int i=0;i<10;i++){
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }
}
