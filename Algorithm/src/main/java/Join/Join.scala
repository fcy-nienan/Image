package Join

import scala.util.Random

trait Join {
  def join(A:Table,B:Table)
  def fun(A:Row,B:Row):Boolean
}
