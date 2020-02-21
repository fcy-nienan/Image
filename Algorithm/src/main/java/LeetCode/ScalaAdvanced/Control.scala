package LeetCode.ScalaAdvanced
import scala.util.control.Breaks._
object Control {
  def main(args: Array[String]): Unit = {
    DemoControlBreak()
    println()
    DemoControlContinue()
  }
  def DemoControlBreak():Unit={
    println("demo break")
    breakable {
      for (i<- 0 to 10){
        if (i==3){
          break()
        }
        print(i)
      }
    }
  }
  def DemoControlContinue():Unit={
    println("demo continue")
    for (i<- 0 to 10){
      breakable {
        if (i==3){
          break()
        }
        print(i)
      }
    }
  }
}
