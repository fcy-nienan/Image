object ControllerGramer {
  def main(args: Array[String]): Unit = {

  }
  /*
  for statement
  for (i <- range){
    statement(s);
  }

  for with filter
  for( var x <- List
      if condition1; if condition2...
   ){
   statement(s);
   }
  */
  def DemoFor():Unit={
    var i=0
    var j=0
    for (i <- 0 to 10){
      println(i)
    }
    for (i <- 0 until 10){
      println(i)
    }
    for (i<-0 until 10 if i>3){
      println(i)
    }
    for (i<-0 until 100 ; j<-0 until 3 if (i*j)%10==0 ){
      printf("%d %d",i,j)
    }
    var vector=for (i<-0 until 10) yield i/3==0
    for (i<- vector){
      println(i)
    }
  }
  def DemoCollection():Unit={
    var array:Array[String]=new Array[String](10);
    println(array(1))
    println(array(2))
    println(array.length)
    var z=Array(1,2,3,4)
    var y=Array(2,3,4,5,6)
    var x=Array(3)(3)
    z.concat(y)
    var map=Map()
    var map1=Map("1"->"2","2"->"3")
    var map2:Map[String,String]= Map[String,String]();
    map1+="3"->"4"


  }
  trait a{

  }
  class z extends x with a {

  }
  class x{

  }
  trait y{

  }
  class t extends z with y{

  }
}
