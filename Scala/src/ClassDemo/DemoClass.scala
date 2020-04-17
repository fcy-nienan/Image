package ClassDemo

class DemoClass(var x:Int,var y:Int) {

}
class GetAndSetClass{
  private var _x=0
  private var _y=0
  def x=_x
  def y=_y
  def x_=(x:Int)=_x=x
  def y_=(y:Int)=_y=y
}

