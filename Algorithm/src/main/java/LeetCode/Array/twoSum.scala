package LeetCode.Array

object twoSum {
  def twoSum(nums:Array[Int],target:Int):Array[Int]={
<<<<<<< HEAD
<<<<<<< HEAD
    var i=0
=======
>>>>>>> tmp
    var map:Map[Int,Int]=Map()
    for( i<- 0 until nums.length){
      var leftover:Int=target-nums(i)
      if (map.contains(leftover)&&map.get(leftover)!=i){
        return Array(i,map(leftover))
<<<<<<< HEAD
=======
    var result:List[Int]=List()
    var map: Map[Int, Int] = Map()
    var i=0;
    for(i <- 0 until nums.length){
      var reduce=target-nums(i)
      var index=map.get(reduce)
      if (map.contains(reduce)){
        return result.toArray
>>>>>>> tmp
=======
>>>>>>> tmp
      }
      map+=nums(i)->i
    }
    return null
  }
}