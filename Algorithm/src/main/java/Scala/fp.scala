package Scala

object fp {
  def main(args: Array[String]): Unit = {
    val array=Array();
    val list=List(1,3,5,7,9);
    val set=Set();
    val map=Map();

    println(list.drop(1))
    println(list.filter(_ > 3))
    println(list.take(3))



  }
}
