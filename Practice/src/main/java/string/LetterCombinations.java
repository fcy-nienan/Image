package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

示例 1：

输入：digits = "23"
输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
示例 2：

输入：digits = ""
输出：[]
示例 3：

输入：digits = "2"
输出：["a","b","c"]


解题思路:

就是普通的排列组合，通过递归回溯来进行组合，需要注意的是递归终止条件和当数组为1的时候的判断
*/

public class LetterCombinations {
    public static void main(String[] args) {

    }
    public List<String> letterCombinations(String digits) {
        char[] dig = digits.toCharArray();
        HashMap<Character,char[]> map = new HashMap();
        map.put('1',new char[]{});
        map.put('2',new char[]{'a','b','c'});
        map.put('3',new char[]{'d','e','f'});
        map.put('4',new char[]{'g','h','i'});
        map.put('5',new char[]{'j','k','l'});
        map.put('6',new char[]{'m','n','o'});
        map.put('7',new char[]{'p','q','r','s'});
        map.put('8',new char[]{'t','u','v'});
        map.put('9',new char[]{'w','x','y','z'});
        List<List<Character>> source = new ArrayList<>();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < dig.length; i++) {
            char[] ch = map.get(dig[i]);
            List list = Arrays.asList(ch);
            source.add(list);
        }
        reusive(source,result,"",0,source.size());
        return result;
    }

    public void reusive(List<List<Character>> source,List<String> result,String currentStr,int index,int endIndex){
        if (index == endIndex){
            result.add(currentStr);
            return;
        }
        List<Character> indexList = source.get(index);
        if (indexList.size() == 0){
            reusive(source,result,currentStr,index + 1,endIndex);
        }else{
            for (int i = 0; i < indexList.size(); i++) {
                char c = indexList.get(i);
                reusive(source,result,currentStr + c,index + 1,endIndex);
            }
        }

    }
}
