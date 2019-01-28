/* binary search tree comprehensive: traverse pre-order, in-order, 
print+count leaves, find if tree is balanced, 
print all path to leaf, reverse level order traversal, get node level
 * input:
 *     1
 *    / \
 *   3   9
 *  / \   \
 * 5   7   11
 * 
 * preOrder output: 1 3 5 7 9 11 */

import java.util.*;
public class binaryTree{
	public static void main(String[] args)  {
    BinaryTree bt = new BinaryTree();
    BinaryTree.TreeNode root = new BinaryTree.TreeNode("1");
    bt.root = root;// use BinaryTree class create “bt”, then //BinaryTree class and TreeNode class create “root”
    bt.root.left = new BinaryTree.TreeNode("3");
    bt.root.left.left = new BinaryTree.TreeNode("5");

    bt.root.left.right = new BinaryTree.TreeNode("7");
    bt.root.right = new BinaryTree.TreeNode("9");
    bt.root.right.right = new BinaryTree.TreeNode("11");
   
      System.out.println("Using preOrder recursion:");
    bt.preOrder(root); 
  System.out.println();
   
    System.out.println("Using inOrder iteration:");
    bt.insert(root,"12");
    bt.inOrder();
     System.out.println();
    System.out.println("leaves are:");
    bt.printLeaves(root);
    System.out.println();
    
    System.out.println("the number of leaves are: "+bt.countLeaves(root)); 
    if(bt.isBalanced(root))
            System.out.println("Tree is balanced");
        else
            System.out.println("Tree is not balanced");
    System.out.println("\nPrint all paths from root to leaf:");
		bt.PathToLeaf(root,new String[10],0);
		System.out.println();
    System.out.println("Reverse level order is:");
bt.reverseLevelOrder(root);
System.out.println();
  
    System.out.println("Node data:5,Level :"+bt.level(root,"5",1));

}} 

class BinaryTree { //this class include preOrder()and other methods
  static class TreeNode { //must be static, main method refers it
    String data;
    TreeNode left, right;

    TreeNode(String data) {
      this.data=data;
      left=right=null;
    }// like LinkedList
  boolean isLeaf() {
      return left == null ? right == null : false;
    } }  //TreeNode class ends
  TreeNode root;// declare “root” as “TreeNode” type
  public void preOrder(TreeNode root) {//same data type as root
    if (root == null) {
      return;
    }
    System.out.print(root.data+" ");
    preOrder(root.left);
    preOrder(root.right);
  }
  public static int level(TreeNode root,String key,int level)
	{
		if(root==null)
			return 0;
		if(root.data==key)
			return level;//level 1
 		int num=level(root.left,key,level+1);
		if(num!=0)//root.left can be switch with right
			return num;
		num=level(root.right,key,level+1);
 	return num;
	}

  public static void reverseLevelOrder(TreeNode root) {
		Queue<TreeNode>queue=new LinkedList<>();//FIFO
		Stack<TreeNode>stack=new Stack<>();//FILO
		queue.add(root);
		while(!queue.isEmpty())
		{
			TreeNode node=queue.poll();
			if(node.right!=null)
				queue.add(node.right);
			if(node.left!=null)
				queue.add(node.left);
 //if right+left switched, start @ buttom row, right to left 
			stack.push(node);
		}
 	while(!stack.empty())//can use isEmpty()
			System.out.print(stack.pop().data+" ");
	}

  public static void PathToLeaf(TreeNode root,String[]path,int count) {
     if (root ==null)
         return;
        path[count++]= root.data;
     //below is when path ends
    if(root.isLeaf()) {
            for (int i=0; i<count; i++) {
			System.out.print(" "+path[i]);
		}System.out.println();//print space btw row
         }
 PathToLeaf(root.left, path, count);
 PathToLeaf(root.right, path, count);
 }

  TreeNode insert(TreeNode root,String data) {
 
        if (root == null) {
            root =new TreeNode(data);
            return root; }
     //if data is smaller than root, then data will be left   
        if (data.compareTo(root.data)<0)
            root.left=insert(root.left,data);
      //else is optional  
else if(data.compareTo(root.data)>0)
            root.right=insert(root.right,data);
        
        return root;
    }

  boolean isBalanced(TreeNode root) 
{
    if (root==null)
        return true;
 
    int lh = height(root.left);//abs() return -# to +#
    int rh = height(root.right);
    if (Math.abs(lh-rh)<= 1
            && isBalanced(root.left)
            && isBalanced(root.right)) 
        return true;

    return false;
}
int height(TreeNode root) 
{ 
    if (root==null)
        return 0;
  return 1+Math.max(height(root.left),height(root.right));
}

  public int countLeaves(TreeNode root) {
    if (root==null)
      return 0;

    if (root.isLeaf()) {
         return 1;}
   else{
  return countLeaves(root.left)+countLeaves(root.right);
    }
  }

 public void printLeaves(TreeNode root) {
    //base case
    if(root==null) {
      return;
    }
 if(root.isLeaf()) {
      System.out.printf("%s ", root.data);
    }
    printLeaves(root.left);
    printLeaves(root.right);
  }



  public void inOrder() {
        Stack<TreeNode> nodes = new Stack<>();
     while (!nodes.isEmpty() || root!=null) {
            if (root!=null) {
                nodes.push(root);
                root=root.left;
            } else {
                root=nodes.pop();
                System.out.printf("%s ", root.data);
                root=root.right;
            }}}}
/*output

Using preOrder recursion:
1 3 5 7 9 11 
Using inOrder iteration:
5 3 7 1 12 9 11 
leaves are:
5 7 12 11 
the number of leaves are: 4
Tree is balanced

Print all paths from root to leaf:
 1 3 5
 1 3 7
 1 9 12
 1 9 11

Reverse level order is:
5 7 12 11 3 9 1 
Node data:5,Level :3
*/