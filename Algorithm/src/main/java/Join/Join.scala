package Join

import scala.util.Random

trait Join {
  def join(A:Table,B:Table):Table
  def fun(A:Row,B:Row):Boolean={
    A(0).toString.equals(B(0).toString)
  }
}
