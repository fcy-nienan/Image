package LeetCode.Array

object maxArea {
  def maxArea(height: Array[Int]): Int = {
    var maxArea=0
    for (i<- 0 until height.length; j<- 0 until height.length){
      maxArea=Math.max(Math.min(height(i),height(j))*(j-i),maxArea)
    }
    maxArea
  }

  def main(args: Array[String]): Unit = {
    var z=Array(1,8,6,2,5,4,8,3,7)
    println(maxArea1(z))
  }
  def maxArea1(height:Array[Int]):Int={
    var i=0
    var maxArea=0
    var j=height.length-1
    while(i<j){
      maxArea=Math.max(maxArea,Math.min(height(i),height(j))*(j-i))
      if (height(i)<height(j)){
        i+=1
      }else{
        j-=1
      }
    }
    maxArea
  }
}
