package Join

class InnerJoin extends Join {
  @Override
  override def join(A: Table, B: Table): Table = {
    val result=scala.collection.mutable.ArrayBuffer[Row]()
    for(i <- 0 until A.length){
      val rowA=A(i)
      for(j <- 0 until B.length){
        val rowB=B(j)
//        printf("join row data :\r\n%s-----%s\r\n",rowA,rowB)
        if(fun(rowA,rowB)){
          result+=(rowA+rowB)
        }
      }
    }
    new Table("innerJoin",result.toArray)
  }
}

