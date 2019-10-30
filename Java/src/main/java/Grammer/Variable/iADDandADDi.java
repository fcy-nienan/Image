package Grammer.Variable;

/**
 * @descripiton:
 * int i=0;
 *     ICONST_0
 *     ISTORE 1
 * int j=i++;
 *     ILOAD 1
 *     IINC 1 1
 *     ISTORE 2
 * j=++i;
 *     IINC 1 1
 *     ILOAD 1
 *     ISTORE 2
 * 如上图是相应代码对应的字节码
 * i++使用的字节码指令是先ILOAD加载变量,然后再在操作数栈上做加一的操作,但是局部变量表中的变量还是之前的值
 * ++i使用的字节码指令是先IINC对局部变量表中的变量做加一操作,然后在ILOAD到操作数栈上
 *
 * IINC指令作用的是局部变量表中的变量,i++是先装载,再对局部变量表中的值加一,但是最后在操作数栈上的值还是原始值
 * 而++i是先对局部变量表中的值加一,然后在装载,所以最后使用的是加了后的值
 * @author: fcy
 * @date: 2019-04-23  1:01
 */
public class iADDandADDi {
    public static void main(String[] args) {
        int i=0;
        int j=i++;
        j=++i;
    }
}
