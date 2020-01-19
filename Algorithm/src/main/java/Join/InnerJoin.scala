package Join

class InnerJoin extends Join {
  @Override
  override def join(A: Table, B: Table): Unit = {
    val result=scala.collection.mutable.ListBuffer[Row]()
    for(i <- 0 until A.length){
      val rowA=A(i)
      for(j <- 0 until B.length){
        val rowB=B(j)
        printf("join row data :\r\n%s-----%s\r\n",rowA,rowB)
        if(fun(rowA,rowB)){

          result+=(rowA+rowB)
        }
      }
    }
    new Table("innerJoin",result.toArray)
  }

  @Override
  override def fun(A: Row, B: Row): Boolean = {
    printf("compare key:%s--%s and result : %s\r\n",A(0),B(0),A(0).toString.equals(B(0).toString))
    A(0).toString.equals(B(0).toString)
  }
}

