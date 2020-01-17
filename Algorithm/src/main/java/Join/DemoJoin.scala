package Join

object DemoJoin {
  def main(args: Array[String]): Unit = {
    val schemaA=new Schema("tableA",defaultSchema)
    println(schemaA)
    val schemaB=schemaA
    val tableA=createTable("tableA",schemaA,3,4)
    val tableB=createTable("tableB",schemaB,3,4)
    println(tableA)
    println(tableB)
    val innerJoin=new InnerJoin
    val result=innerJoin.join(tableA,tableB)
    println(result.toString)
  }
}
