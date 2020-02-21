package LeetCode.ScalaAdvanced

object MatchPattern {
  def main(args: Array[String]): Unit = {
    var x=Array[Any](0,1,"hello",Nil,None,List(0,1,2),List(2,3,4),List(1,2,3)
    ,Vector(1,2,3),Vector(2,3,4),(1,2,3),(2,3),(3,4,5),User("fcy","521314")
    ,"StringType",4,Array(1,2,3),User("1","2"),List(1,2,3),Map("key"->"value"))
    x.foreach(e=>println(MatchPatternDemo(e)))
  }
  def MatchPatternDemo(x:Any):String=x match {
//      constant patterns
    case 0=>"zero"
    case 1=>"one"
    case "hello"=>"hello"
    case Nil=>"an empty List"
//      sequence patterns
    case List(0,_,_)=>"a three-element List with 0 as the first element"
    case List(1,_*)=>"a list beginning with 1 ,having any number of elements"
    case Vector(1,_*)=>"a vector starting with 1 ,having any number of elements"
//      sequence patterns and use variable
    case list @ List(0,_,_)=>s"a three-element List $list"
    case list @ List(1,_*)=>s"a list beginning with 1 $list"
//      tuples
    case (a,b)=>s"got $a and $b"
    case (a,b,c)=>s"got $a,$b and $c"
//      constructor patterns
    case User(name,"521314")=>s"found a person and name is $name"
//      typed patterns
    case s:String=>s"got a string type $s"
    case i:Int=>s"got a int type $i"
    case a:Array[Int]=>s"got a array type ${a.mkString}"
    case u:User=>s"user:${u.name}"
    case list:List[_]=>s"thanks for the List:$list"
    case m:Map[_,_]=>m.toString
    case _=>"unknown"
  }
  case class User(name:String,password:String)
}
