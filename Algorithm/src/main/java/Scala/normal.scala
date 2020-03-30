package Scala

import java.util

object normal {
  def main(args:Array[String]):Unit={
    var arr=(1 to 100).toArray
    println(util.Arrays.toString(arr))
    arr(0)=100;
    for(i<-1 until 100){
      if((arr(i)&1)==0){
        arr(i)=arr(i)/2
      }else{
        arr(i)=arr(i-2)-1;
      }
    }
    println(util.Arrays.toString(arr))
  }
}
