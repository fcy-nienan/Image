package com.fcy.BigData.Scala.actor

import scala.actors.Actor

object t1 extends Actor{
  def act(): Unit = {
    receive{
      case x:String=>{
        println(x)
      }
    }
  }
}
object ActorConcat {
  def main(args: Array[String]): Unit = {
    t1.start()
    t1 ! "hello"

  }

}
