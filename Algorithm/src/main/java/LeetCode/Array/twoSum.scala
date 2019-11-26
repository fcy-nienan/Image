package LeetCode.Array

object twoSum {
  def twoSum(nums:Array[Int],target:Int):Array[Int]={
    var result:List[Int]=List()
    var map: Map[Int, Int] = Map()
    var i=0;
    for(i <- 0 until nums.length){
      var reduce=target-nums(i)
      var index=map.get(reduce)
      if (map.contains(reduce)){
        result=result.appended(map.get(reduce))
        result=result.appended(nums(i))
        return result.toArray
      }
      map+=(nums(i)->i)
    }

    result.toArray
  }
}