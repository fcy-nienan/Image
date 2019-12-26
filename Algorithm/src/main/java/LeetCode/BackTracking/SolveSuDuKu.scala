package LeetCode.BackTracking

object SolveSuDuKu {
  def main(args: Array[String]): Unit = {

  }
  def solveSudoku(board: Array[Array[Char]]): Unit = {

  }
  def violence(board:Array[Array[Char]]):Unit={
    for (k<- 0 to 10 ){
      for (l<- 0 to 10){
        if (board(k)(l)=='.'){
          for (m<- 0 to 10){
            board(k)(l)=m.toChar
          }
        }
      }
    }
  }
  def single(x:Int,y:Int,current:Int,board:Array[Array[Char]]):Boolean={
    if (board(x)(y)=='.'){
      for (i<- 0 to 10){
        single(x,y,current+1,board)
      }
    }
  }
  def isValid(i:Int,j:Int,board:Array[Array[Char]]):Boolean={
    var current=board(i)(j)
    for(k<- 0 to 10){
      if (board(i)(k)==current||board(k)(j)==current){
        false
      }
    }
    true
  }
}
