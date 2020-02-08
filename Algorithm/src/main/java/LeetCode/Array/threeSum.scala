package LeetCode.Array

object threeSum {
  def main(args: Array[String]): Unit = {
    var x=Array(-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6)
    var list=threeSum(x)
  }
  def threeSum(nums: Array[Int]): List[List[Int]] = {
    var list:List[List[Int]]=List()
    if (nums.length==0)return list
    var set:Set[String]=Set();
    quickSort(nums,0,nums.length-1)
    for(k<- 0 until nums.length){
      var fixed=nums(k)
      var i=k+1
      var j=nums.length-1
      while(i<j){
        var v=fixed+nums(i)+nums(j)

        if(v>0){
          j-=1
        }else if(v<0){
          i+=1
        }else{
          var setString:String=fixed+""+nums(i)+""+nums(j)
          if (!set.contains(setString)) {
            var z: List[Int] = List()
            z = z.::(fixed)
            z = z.::(nums(i))
            z = z.::(nums(j))
            list = list.::(z)
            set+=setString
            println(setString)
          }
          i += 1
          j -= 1
        }
      }
    }
    list
  }
  def quickSort(nums:Array[Int],start:Int,end:Int):Unit={
    var low=start
    var high=end
    var key=nums(low)
    while(low<high){
      while(low<high&&nums(high)>=key){
        high-=1
      }
      if (nums(high)<=key){
        var tmp=nums(high)
        nums(high)=nums(low)
        nums(low)=tmp
      }
      while(low<high&&nums(low)<=key){
        low=low.+(1)
      }
      if (nums(low)>=key){
        var tmp=nums(high)
        nums(high)=nums(low)
        nums(low)=tmp
      }
    }
    if(low!=start)quickSort(nums,0,low-1)
    if(high!=end)quickSort(nums,low+1,end)
  }
}
