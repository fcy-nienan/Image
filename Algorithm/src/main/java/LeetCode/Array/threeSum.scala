package LeetCode.Array

object threeSum {
  def threeSum(nums: Array[Int]): List[List[Int]] = {
    for (i <- 0 until nums.length){
      
    }
  }
  def twoSum(nums:Array[Int],target:Int):Array[Int]={
    var map:Map[Int,Int]=Map()
    for (i <- 0 until nums.length){
      if (map.contains(target-nums(i))&&map(target-nums(i))!=i){
        return Array(nums(i),target-nums(i))
      }
      map+=nums(i)->i
    }
    return null
  }
}
