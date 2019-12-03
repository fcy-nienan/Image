package LeetCode.Array

import java.util.Collections

import scala.util.Sorting
import Array._
object threeSumClosest {
  def main(args: Array[String]): Unit = {
    var t=List(-1,2,1,-4)
    var arr=range(0,1000,1);
    var list1=arr.toList

    var s=System.currentTimeMillis();
    quickSort(t)

  }
  def threeSumClosest(nums:Array[Int],target:Int):Int={
    if (nums.length==0)return 0
    var minValue=0
    for (i<- 0 until nums.length){
      var j=i+1
      var k=nums.length-1
      var fixed=nums(i)
      while(j<k){
        var value=nums(j)+nums(k)+fixed
        var reduce=value-target
        if (reduce>minValue){
          k-=1
        }else if (reduce<minValue){
          minValue=reduce
          j+=1
        }else{
          return value
        }
      }
    }
    minValue+target
  }
  def quickSort(xs: List[Int]): List[Int] = {
    if (xs.isEmpty) xs
    else
      quickSort(xs.drop(1).filter(x=>x<=xs.head)):::xs.head::quickSort(xs.filter(x=>x>xs.head))
  }
  def quickSort(nums:Array[Int],start:Int,end:Int):Unit={
    var low=start
    var high=end
    var key=nums(start)
    while(low<high){
      while(low<high&&nums(high)>=key){
        high-=1
      }
      if (nums(high)<key){
        var tmp=nums(high)
        nums(high)=nums(low)
        nums(low)=tmp
      }
      while(low<high&&nums(low)<=key){
        low+=1
      }
      if(nums(low)>key){
        var tmp=nums(high)
        nums(high)=nums(low)
        nums(low)=tmp
      }
    }
    if (start!=low)quickSort(nums,0,low-1)
    if (end!=high)quickSort(nums,low+1,end)
  }
}
