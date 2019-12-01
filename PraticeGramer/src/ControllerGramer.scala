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
}
