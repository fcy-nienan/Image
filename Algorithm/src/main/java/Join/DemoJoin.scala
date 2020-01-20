package Join

object DemoJoin {
  def main(args: Array[String]): Unit = {
    val schemaA=new Schema("tableA",defaultSchema)
    println(schemaA)
    val schemaB=schemaA
    val tableA=createTable("tableA",schemaA,3,1)
    val tableB=createTable("tableB",schemaB,3,1)
    printf("tableA data:\r\n%s",tableA)
    printf("tableB data:\r\n%s",tableB)

    val innerJoin=new InnerJoin
    val innerJoinResult=innerJoin.join(tableA,tableB)
    printf("inner join result : \r\n%s",innerJoinResult)

    val leftJoin=new LeftJoin
    val leftJoinResult=leftJoin.join(tableA,tableB)
    printf("left join result: \r\n%s",leftJoinResult)

    val rightJoin=new RightJoin
    val rightJoinResult=rightJoin.join(tableA,tableB)
    printf("right join result: \r\n%s",rightJoinResult)
  }
}
