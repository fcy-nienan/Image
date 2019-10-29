package com.fcy.BigData.Scala.BaseProgram
class QuickSort1{
  def quickSort(array:Array[Int],start:Int,end:Int): Unit ={
    var low:Int=start;
    var high:Int=end;
    var key:Int=array(start);
    while(low<high){
      while(low<high&&array(high)>=key){
        high-=1;
      }
      if (low<high) {
        var tmp=array(low);
        array(low)=array(high);
        array(high)=tmp;
      }
      while(low<high&&array(low)<=key){
        low+=1;
      }
      if (low<high) {
        var tmp=array(low);
        array(low)=array(high);
        array(high)=tmp;
      }
    }
    if (low!=start)quickSort(array,start,low-1);
    if (end!=high)quickSort(array,low+1,end);
  }
}
object QuickSort1 {
  def main(array:Array[String]):Unit={
    var array=Array(23,589,2,4,1,0,4,89);
    var t=new QuickSort1();
    t.quickSort(array,0,array.length-1);
    array.foreach(e=>println(e));
  }
}
