package LeetCode.ScalaAdvanced;
/**
 * 柯里化从直观上看就是将一个接收多个参数的函数改造为只接收一个参数并且返回一个函数，
 * 返回的函数接收其他参数，一直到接收最后一个参数
 * 可以惰性求值,知道所有的参数到齐后才能得到最终的结果
 * 中间过程得到的知识一个函数的地址
 * 还有一些其他的特性待研究
 *
 *
 * def add(a:Int)(b:Int) = {a + b}
 * // Works
 * add(5)(6)
 * // Doesn't compile
 * val f = add(5)
 * // Works
 * val f = add(5)_
 * f(10) // yields 15
 *
 * def add2(a:Int) = { b:Int => a + b }
 * // Works
 * add2(5)(6)
 * // Also works
 * val f = add2(5)
 * f(10) // Yields 15
 * // Doesn't compile
 * val f = add2(5)_
 *
 *
 *
 * Also note that add has (potentially) less overhead than add2 has if you immediately provide all parameters.
 * Like add(5)(6) just translates to add(5, 6) on the JVM level, no Function object is created.
 * On the other hand, add2(5)(6) will first create a Function object that encloses 5,
 * and then call apply(6) on that
 *
 * 第一种写法比第二种写法的开销更小
 * 第一种写法的add(5)(6)在JVM层面会被转化为add(5,6),没有函数对象会被创建
 * 第二种方法会首先创建一个包含5的函数对象,然后再在他上面调用apply(6)
 *
 */
object Curring {
  def main(args: Array[String]): Unit = {
//    println(add(1,2))//3
//    println(addSugar(1))//Curring$$$Lambda$3/1355531311@f5f2bb7
//    println(addSugar(10))
//    println(addSugar(1)(2))//3
//    println(addThree(1))//Curring$$$Lambda$4/1690716179@3ecf72fd
//    println(addThree(1)(2))//Curring$$$Lambda$5/564160838@7a92922
//    println(addThree(1)(2)(3))//6
    println(addSugar2(1)(2))
    println(addSugar2(1))
    val f1=addSugar(1)_
    println(f1(100))

  }
  /*正常的add函数*/
  def add(x:Int,y:Int):Int=x+y
  /*不加语法糖的格式*/
  def addNoSugar(x:Int):Int=>Int=(b:Int)=>x+b
  //语法糖的格式//这种方式是只能一下调用两个参数,要不然编译会不通过
  def addSugar(x:Int)(b:Int)=x+b
  def addSugar2(x:Int)={b:Int=>x+b}
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
  //更简洁的语法糖
  def addThreeSugar(x:Int)(y:Int)(z:Int)=x+y+z
}
