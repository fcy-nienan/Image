package LeetCode.ScalaAdvanced;
/**
 * 柯里化从直观上看就是将一个接收多个参数的函数改造为只接收一个参数并且返回一个函数，
 * 返回的函数接收其他参数，一直到接收最后一个参数
 * 可以惰性求值,知道所有的参数到齐后才能得到最终的结果
 * 中间过程得到的知识一个函数的地址
 * 还有一些其他的特性待研究
 */
object Curring {
  def main(args: Array[String]): Unit = {
    println(add(1,2))//3
    println(addSugar(1))//Curring$$$Lambda$3/1355531311@f5f2bb7
    println(addSugar(1)(2))//3
    println(addThree(1))//Curring$$$Lambda$4/1690716179@3ecf72fd
    println(addThree(1)(2))//Curring$$$Lambda$5/564160838@7a92922
    println(addThree(1)(2)(3))//6
  }
  /*正常的add函数*/
  def add(x:Int,y:Int):Int=x+y
  /*语法糖的格式*/
  def addSugar(x:Int)=(b:Int)=>{
    x+b
  }
  /*不加语法糖的格式*/
  def addNoSugar(x:Int):Int=>Int=(b:Int)=>x+b
  /*
  * 下面的函数是一个接收两个参数并且返回一个函数的函数
  */
  def test(x:Int,y:String):String=>List[String]=(y:String)=>List(y)
  /*
  * addThree是一个函数,该函数接收一个Int类型参数,并且返回一个B
  * B是一个函数,该函数接收一个Int类型参数,并且返回一个C
  * C是一个函数,该函数接收一个Int类型参数,并且返回一个Int类型参数
  */
  def addThree(x:Int):(Int)=>(Int)=>(Int)={
    (b:Int)=>{
      (c:Int)=>{
        x+b+c
      }
    }
  }
  /*省略一些括号*/
  def addThreeSugar(x:Int):Int=>Int=>Int=(b:Int)=>(c:Int)=>x+b+c;

}
