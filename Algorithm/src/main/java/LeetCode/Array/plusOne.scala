package LeetCode.Array

object plusOne {
  //9 99 999
  def main(args: Array[String]): Unit = {
    println(plusOne(Array(1,2,3)).toList)
    println(plusOne(Array(1,2,9)).toList)
    println(plusOne(Array(9,9)).toList)
    println(plusOne(Array(9)).toList)

  }
  def plusOne(digits: Array[Int]): Array[Int] = {
    var index=digits.length-1
    digits(index)=digits(index)+1
    while (digits(index)==10){
      digits(index)=0
      if (index-1== -1){
        return (1 :: digits.toList).toArray
      }
      digits(index-1)=digits(index-1)+1
      index=index-1
    }
    digits
  }
}
