package LeetCode.Array

object threeSum {
  def main(args: Array[String]): Unit = {
    var x=Array(-1,0,1,2,-1,-4)
    var t=threeSum(x)
    println(t)
  }
  def threeSum(nums: Array[Int]): List[List[Int]] = {
    var map:Map[Int,String]=Map()
    for (i <- 0 until nums.length;j<- i+1 until nums.length){
      var t=0-nums(i)-nums(j)
      map+=t->(i+","+j)
    }
    var t:List[List[Int]]=List()
    for(i<-0 until nums.length){
      if (map.contains(nums(i))&&map(nums(i)).indexOf(i+"")==(-1)){
        var x:List[Int]=List()
        x=x.::(nums(i))
        val strings = map(nums(i)).split(",")
        for(j<-strings){
          x=x.::(nums(j.toInt))
        }
        t=t.::(x)
        map=map.removed(nums(i))
      }
    }
    t
  }
}
