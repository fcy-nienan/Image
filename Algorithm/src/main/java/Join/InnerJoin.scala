package Join

class InnerJoin extends Join {
  @Override
  override def join(A: Table, B: Table): Unit = {
    val result=scala.collection.mutable.ListBuffer[Row]()
    for(i <- 0 until A.length){
      val rowA=A(i)
      for(j <- 0 until B.length){
        val rowB=B(j)
        if(fun(rowA,rowB)){
          result+=(rowA+rowB)
        }
      }
    }
    new Table("innerJoin",result.toArray)
  }

  @Override
  override def fun(A: Row, B: Row): Boolean = {
    A(0)==B(0)
  }
}

