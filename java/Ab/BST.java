// Implement BinarySearchTree including insert and delete

// idea: recursive sol, DFS
public class BST {
	class Node {
	  int data;
	  Node left;
	  Node right;
	  Node(int data) {
	    this.data = data;
	    left = null;
	    right = null;
	  }
	}
		  
	public static Node root; // remember to make it static!
	  private boolean isDelete;
	  public void insert(int data) {
	    root = insertDFS(root, data);
	  }
	  
	  public Node insertDFS(Node root, int data) {
	    if(root == null) {
	      root = new Node(data);
	      return root;
	    }
	    if(data > root.data) {
	      root.right = insertDFS(root.right, data);
	    }
	    if(data < root.data) {
	      root.left = insertDFS(root.left, data);
	    }
	    return root;
	  }
	  
	  public boolean delete(int data) {
	    isDelete = false;
	    deleteDFS(root, data);
	    return isDelete;    
	  }
	  
	  public Node deleteDFS(Node root, int data) {
	    if(root == null) return root;
	    if(data < root.data) {
	      root.left = deleteDFS(root.left, data);
	    }
	    else if(data > root.data) {
	      root.right = deleteDFS(root.right, data);
	    }
	    else {
	      isDelete = true;
	      if(root.left != null) {
	        int max = findMax(root.left);
	        root.left = deleteDFS(root.left, max);
	        root.data = max;
	      }
	      else {
	        return root.right;
	      }
	    }
	    return root;
	  }
	  
	  public int findMax(Node root) {
	    int max = Integer.MIN_VALUE;
	    Node pos = root;
	    while(pos != null) {
	      if(pos.data > max) {
	        max = pos.data;
	        pos = pos.right;
	      }
	    }
	    return max;
	  }
	  
	  public boolean find(int data) {
	    if(root == null) return false;
	    return findDFS(root, data);
	  }
	  
	  public boolean findDFS(Node root, int data) {
	    if(root == null) return false;
	    if(root.data == data) return true;
	    else if(findDFS(root.left, data)) return true;
	    else if(findDFS(root.right, data)) return true;
	    else return false;  
	}

	public static void main(String[] args) {
		BST b = new BST();
	    b.insert(3);b.insert(8);
	    b.insert(1);b.insert(4);b.insert(6);b.insert(2);b.insert(10);b.insert(9);
	    b.insert(20);b.insert(25);b.insert(15);b.insert(16);
	    System.out.println("Original Tree : ");
	    b.display(b.root);
	    System.out.println("");
	    System.out.println("Check whether Node with value 4 exists : " + b.find(4));
	    System.out.println("Delete Node with no children (2) : " + b.delete(2));
	    b.display(root);
	    System.out.println("\n Delete Node with one child (4) : " + b.delete(4));
	    b.display(root);
	    System.out.println("\n Delete Node with Two children (10) : " + b.delete(10));
	    b.display(root);
	}
	  
	public void display(Node root) {
	    if(root == null) return;
	    display(root.left);
	    System.out.print(" " + root.data);
	    display(root.right);
	}

}


////Below is the iteration method
//public boolean find(int id){
//Node current = root;
//while(current!=null){
//  if(current.data==id){
//    return true;
//  }else if(current.data > id){
//    current = current.left;
//  }else{
//    current = current.right;
//  }
//}
//return false;
//}
//
//public boolean delete(int id){
//Node parent = root;
//Node current = root;
//boolean isLeftChild = false;
//while(current.data!=id){
//  parent = current;
//  if(id < current.data){
//    isLeftChild = true;
//    current = current.left;
//  }else{
//    isLeftChild = false;
//    current = current.right;
//  }
//  if(current ==null){
//    return false;
//  }
//}
////if i am here that means we have found the node
////Case 1: if node to be deleted has no children
//if(current.left==null && current.right==null){
//  if(current==root){
//    root = null;
//  }
//  if(isLeftChild ==true){
//    parent.left = null;
//  }else{
//    parent.right = null;
//  }
//}
////Case 2 : if node to be deleted has only one child
//else if(current.right==null){
//  if(current==root){
//    root = current.left;
//  }else if(isLeftChild){
//    parent.left = current.left;
//  }else{
//    parent.right = current.left;
//  }
//}
//else if(current.left==null){
//  if(current==root){
//    root = current.right;
//  }else if(isLeftChild){
//    parent.left = current.right;
//  }else{
//    parent.right = current.right;
//  }
//}else if(current.left!=null && current.right!=null){
//
//  //now we have found the minimum element in the right sub tree
//  Node successor   = getSuccessor(current);
//  if(current==root){
//    root = successor;
//  }else if(isLeftChild){
//    parent.left = successor;
//  }else{
//    parent.right = successor;
//  }
//  successor.left = current.left;
//}
//return true;
//}
//
////do a lot of things
//// find the successor
////if successor is deletenode right,then insert directly
////else put successor's right to successor parent's right
//public Node getSuccessor(Node deleleNode){
//Node successsor =null;
//Node successsorParent =null;
//Node current = deleleNode.right;
//while(current!=null){
//  successsorParent = successsor;
//  successsor = current;
//  current = current.left;
//}
////check if successor has the right child, it cannot have left child for sure
//// if it does have the right child, add it to the left of successorParent.
////successsorParent
//if(successsor!=deleleNode.right){
//  successsorParent.left = successsor.right;
//  successsor.right = deleleNode.right;
//}
//return successsor;
//}
//public void insert(int id){
//if(root == null) {
//  root = new Node(id);
//  return;
//}
//else {
//    Node pos = root;
//    Node pre = null;
//    while(pos!=null) {
//      if(id > pos.data) {
//        pre = pos;
//        pos = pos.right;
//
//      }
//      else {
//        pre = pos;
//        pos = pos.left;
//      }
//
//   }
//   if(id > pre.data) {
//     pre.right = new Node(id);
//   }
//   if(id <= pre.data) {
//     pre.left = new Node(id);
//   }
//}
//return;
//}
