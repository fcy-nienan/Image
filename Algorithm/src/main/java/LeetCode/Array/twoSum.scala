package LeetCode.Array

object twoSum {
  def twoSum(nums:Array[Int],target:Int):Array[Int]={
    var map:Map[Int,Int]=Map()
    for( i<- 0 until nums.length){
      var leftover:Int=target-nums(i)
      if (map.contains(leftover)&&map.get(leftover)!=i){
        return Array(i,map(leftover))
      }
      map+=nums(i)->i
    }
    return null
  }
}