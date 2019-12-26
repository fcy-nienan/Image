package LeetCode.BackTracking

import java.util

object LetterCombinations {
  def main(args: Array[String]): Unit = {
    var list=letterCombinations("1");
    println(list);
  }
  def letterCombinations(digits:String):List[String]={
    var result:List[String]=List()
    if(digits==null||digits.equals(""))return result;
    var map=Map(
      '1'->"",
      '2'->"abc",
      '3'->"def",
      '4'->"ghi",
      '5'->"jkl",
      '6'->"mno",
      '7'->"pqrs",
      '8'->"tuv",
      '9'->"wxyz"
    );
    help(digits,0,"",result,map);
  }
  def help(digits:String, index:Int, builder:String, result:List[String], map:Map[Char,String]): List[String] ={
    if(index==digits.length){
      result.::(builder)
    }else{
      var value=map(digits.charAt(index))
      var sum:List[String]=List()
      sum=sum.:::(result)
      if(value.equals("")){
        sum=sum.:::(help(digits, index + 1, builder, result, map))
      }else {
        value.toCharArray.foreach(e => {
          sum=sum.:::(help(digits, index + 1, builder+e, result, map))
        })
      }
      sum
    }
  }
}
