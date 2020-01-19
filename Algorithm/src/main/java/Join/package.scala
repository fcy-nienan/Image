import scala.util.Random

package object Join {
  class Field(name:String,value:String){

    @Override
    override def toString: String = {
      value
    }
  }
  class Row(fields:Array[Field]){
    def getFields()=fields
    def apply(x:Int)=fields(x)
    def update(x:Int,v:Field)=fields(x)=v
    def length=fields.length
    def +(A:Row): Row ={
      val result=new Array[Field](length+A.length)
      System.arraycopy(this.fields,0,result,0,length)
      System.arraycopy(A.getFields(),0,result,0,A.length)
      new Row(result)
    }

    @Override
    override def toString: String = {
      val builder=new StringBuilder
      for(value <- 0 until length){
        builder.append(this(value))
        builder.append(",")
      }
      builder.deleteCharAt(builder.length-1)
      builder.toString()
    }
  }
  class Table(name:String,rows:Array[Row]){
    def apply(x:Int)=rows(x)
    def update(x:Int,v:Row)=rows(x)=v
    def length=rows.length

    override def toString: String = {
      val builder=new StringBuilder
      for(value <- 0 until length){
        builder.append(this(value)+System.getProperty("line.separator"))
      }
      builder.toString()
    }
  }
  class Schema(schemaName:String,fieldName:Array[String]){
    def apply(x:Int)=fieldName(x)
    def update(x:Int,v:String)=fieldName(x)=v
    def length=fieldName.length

    override def toString: String = {
      val newBuilder=new StringBuilder()
      for(v <- 0 until length){
        newBuilder.append(this(v))
        newBuilder.append(",")
      }
      newBuilder.deleteCharAt(newBuilder.length-1)
      newBuilder.toString
    }
  }
  val id="id"
  val username="username"
  val password="password"
  val address="address"
  val defaultSchema=Array(id,username,password,address)

  def createField(fieldName:String):Field={
    val value:String=fieldName match {
      case "id" => createID(5)
      case "username" => createName
      case "password" => createPassword
      case "address" => createAddress
    }
    printf("fieldName is : %s and create field value: %s \r\n",fieldName,value)
    new Field(fieldName,value)
  }
  def createRow(schema:Schema,numField:Int):Row={
    var t=List[Field]()
    for (i <- 0 until numField){
      t=createField(schema(i)) :: t
    }
    t=t.reverse
    val row=new Row(t.toArray)
    printf("schema is : %s and numField is %d and create row: %s \r\n",schema,numField,row)
    row
  }
  def createTable(tabName:String,schema:Schema,numRow:Int,numField:Int):Table={
    var t=List[Row]()
    for (i <- 0 to numRow){
      t=createRow(schema,numField) :: t
    }
    val table=new Table(tabName,t.toArray)
    printf("tableName is %s and schema is %s and table data is \r\n%s \r\n",tabName,schema,table)
    table
  }

  def createID(bound:Int):String={
    Random.nextInt(bound).toString
  }
  def createName(): String ={
    createRandomString(3)
  }
  def createPassword(): String ={
    createRandomString(6)
  }
  def createAddress():String={
    createRandomString(10)
  }
  def createRandomString(len:Int):String={
    var builder=new StringBuilder
    for (i <- 0 to len){
      builder.append(createID(26))
    }
    builder.toString
  }
}
