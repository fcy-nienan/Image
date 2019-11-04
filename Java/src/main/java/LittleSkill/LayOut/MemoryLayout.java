package LittleSkill.LayOut;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-06-25  23:23
 */
/*              no compress                 compress
 * boolean           1                           1
 * byte              1                           1
 * short             2                           2
 * int               4                           4
 * float             4                           4
 * long              8                           8
 * double            8                           8
 * reference         8                           4
 * header            16                          12
 * array header      24(header+length)           16
 *
 *
boolean default value:false
int default value:0
long default value:0
short default value:0
float default value:0.0
double default value:0.0
class java.lang.String default value:null
byte default value:0
char default value:0
 *
 * */
public class MemoryLayout{

    public static void main(String[] args){
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(MemoryObject.class).toPrintable());
    }

}
