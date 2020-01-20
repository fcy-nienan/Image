package Join

import scala.collection.mutable.ArrayBuffer

class LeftJoin extends Join {
  @Override
  override def join(A: Table, B: Table): Table = {
    val result=new ArrayBuffer[Row]()
    for(i <- 0 until A.length){
      val rowA=A(i)
      var meetCondition:Boolean=false
      for(j <- 0 until B.length){
        val rowB=B(j)
        if (fun(rowA,rowB)){
          result+=(rowA+rowB)
          meetCondition=true
        }
      }
      if(!meetCondition)
        result+=(rowA+getEmptyRow())
    }
    new Table("leftJoin",result.toArray)
  }
}
