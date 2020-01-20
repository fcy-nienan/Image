package Join

class RightJoin extends Join {
  @Override
  override def join(A: Table, B: Table): Table = {
    var result=new scala.collection.mutable.ArrayBuffer[Row]()
    for (i <- 0 until B.length){
      val rowB=B(i)
      var meetCondition=false
      for(j <- 0 until A.length){
        val rowA=A(j)
        if (fun(rowA,rowB)){
          result+=(rowA+rowB)
          meetCondition=true
        }
      }
      if (!meetCondition){
        result+=(getEmptyRow()+rowB)
      }
    }
    new Table("rightJoin",result.toArray)
  }
}
