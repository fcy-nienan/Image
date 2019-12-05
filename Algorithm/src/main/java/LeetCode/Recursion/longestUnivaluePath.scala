package LeetCode.Recursion
import java.util

import scala.collection.mutable
class TreeNode(var _value: Int) {
     var value: Int = _value
     var left: TreeNode = null
     var right: TreeNode = null
   }
object longestUnivaluePath {
  def longestUnivaluePath(root: TreeNode): Int = {
    mutable.HashMap
    val list:util.LinkedList[TreeNode] = new util.LinkedList[TreeNode]()
    list.offer(root)
    var sum=0;
    while ( {list.size != 0}) {
      val poll = list.poll
      sum=Math.max(sum,visit(poll))
      if (poll.right != null) list.offer(poll.right)
      if (poll.left != null) list.offer(poll.left)
    }
    sum
  }
  def visit(root:TreeNode):Int={
    var sum=0;
    if (root!=null&&root.left!=null&&root.value==root.left.value) {
      sum += 1;
      sum+=longestUnivaluePath(root.left)
    }
    if (root!=null&&root.right!=null&&root.value==root.right.value){
      sum+=1;
      sum+=longestUnivaluePath(root.right)
    }
    sum
  }
}
