package Imitate.LEX.S1;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-10-06  11:49
 */
public class Token {
//    标识单词符号类别的整数
    public int kind;
//    源程序中单词符号的位置
    public int beginLine,beginColumn,endLine,endColumn;
//    构成单词符号的字符组成的串
    public String image;
//    下一个单词符号对象
    public Token next;
}
