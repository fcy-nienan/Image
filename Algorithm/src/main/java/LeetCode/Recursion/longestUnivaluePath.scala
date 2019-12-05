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
    if (root!=null){
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
  def preOrder(root:TreeNode):Unit={

  }
}
