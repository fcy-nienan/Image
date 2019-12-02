object ControllerGramer {
  def main(args: Array[String]): Unit = {
    DemoCollection()
  }
  def DemoCollection():Unit={
    var set:Set[Int]=Set();
    set=Set(1,2,3,4,5,6)
    println(set.head)//5
    println(set.tail)//Set(1,2,3,4)
    println(set.isEmpty)//false

    var set1=Set(1,2,3,4,5)
    var set2=Set(2,3,4,5,6)
    var set3=set1++set2
    set3=set1.++(set2)
    println(set3)//Set(5,1,6,2,3,4)

    var set4=Set(1,2,3,4,5)
    println(set4.max)//5
    println(set4.min)//1

    //find common values
    var set5=Set(1,2,3,4,5)
    var set6=Set(2,3,4,5,6)
    var set7=set5.&(set6)
    set7=set5.intersect(set6)
    println(set7)//Set(5,2,3,4)

    set7+=4
    set7+=8
    println(set7)//HashSet(5,2,3,8,4)

    set7-=2
    set7-=3
    set7-=1
    println(set7)//5,8,4




    var list1=List(1,2,3)
    list1=2::list1//增加2
    println(list1)//List(2,1,2,3)
    println(list1(1))//获取第二个元素
    println(list1 apply 0)//获取第一个元素
    list1=list1.updated(1,10)//更新第一个为10
    println(list1)//List(2,10,2,3)
    list1=list1:+3
    list1=list1.::(3)
  }
}
