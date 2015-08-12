import java.util.*;
import java.util.stream.*;

public class Example {
  public static void main(String[] args) {
    Node<Integer> x = new Node<Integer>(10);
    int len=0;
    Node root = getBinaryTree(true);
    System.out.println("\n height ->" +
    getHeight(root));
    System.out.println("\n preOrder -> ");
    preOrder(root);
    System.out.println("\n Iterative preOrder -> ");
    iterativePreOrder(root);
    System.out.println("\n postOrder -> ");
    postOrder(root);
    System.out.println("\n Iterative postOrder -> ");
    iterativePostOrder(root);
    System.out.println("\n inOrder -> ");
    inOrder(root);
    System.out.println("\n Iterative inOrder -> ");
    iterativeInOrder(root);
  }

  public static void iterativePreOrder(Node node) {
    Stack<Node> parentStack = new Stack<Node>();
    while(node!=null || !parentStack.isEmpty()) {
      if(node!=null) {
        System.out.print(node.data + "*");
        if(node.right!=null) parentStack.push(node.right);
        node = node.left;
      } else {
        node = parentStack.pop();
      }
    }
  }

  public static void iterativeInOrder(Node node) {
    Stack<Node> parentStack = new Stack<Node>();
    while(node!=null || !parentStack.isEmpty()) {
      if(node!=null) {
        parentStack.push(node);
        node = node.left;
      } else {
        node = parentStack.pop();
        System.out.print(node.data + "*");
        node = node.right;
      }
    }
  }

  public static void iterativePostOrder(Node node) {
    Stack<Node> parentStack = new Stack<Node>();
    Node lastVisitedNode =null, peekNode;
    while(node!=null || !parentStack.isEmpty()) {
      if(node!=null) {
        parentStack.push(node);
        node = node.left;
      } else {
        peekNode = parentStack.peek();
        if(peekNode.right != null && peekNode.right!=lastVisitedNode) {
          node = peekNode.right;
        } else {
          System.out.print(peekNode.data + "*");
          lastVisitedNode = parentStack.pop();
        }
      }
    }
  }

  public static void preOrder(Node node) {
    if(node==null) return;
    System.out.print(node.data + "*" );
    preOrder(node.left);
    preOrder(node.right);
  }
  public static void postOrder(Node node) {
    if(node==null) return;
    postOrder(node.left);
    postOrder(node.right);
    System.out.print(node.data + "*" );
  }
  public static void inOrder(Node node) {
    if(node==null) return;
    inOrder(node.left);
    System.out.print(node.data + "*" );
    inOrder(node.right);
  }
  public static int getHeight(Node node) {
    if(node == null)
      return 0;
    return Math.max(getHeight(node.left),getHeight(node.right)) + 1;
  }

  public static Node getBinaryTree(Boolean isBST) {
    List<Node> tree = new ArrayList<Node>();
    List<Integer> list = isBST ? Arrays.asList(-1,-8,5,-4,2,8,-6,3,6,9,7):Arrays.asList(-1,-8,9,6,-4,5,1,2,7,3,-6);
    list.forEach(e -> {
      tree.add(new Node<Integer>(e));
    });
    Node root = tree.get(0);
    root.left = tree.get(1);
    root.right = tree.get(2);
    tree.get(1).right = tree.get(3);
    tree.get(2).left = tree.get(4);
    tree.get(2).right = tree.get(5);
    tree.get(3).left = tree.get(6);
    tree.get(4).right = tree.get(7);
    tree.get(5).left = tree.get(8);
    tree.get(5).right = tree.get(9);
    tree.get(8).right = tree.get(10);
    return root;
    /**
              non-BST     -1                    BST           -1
                        /   \                               /    \
                     -8      9                           -8       5
                      \     / \                           \     /  \
                      6   -4   5                          -4   2    8
                    /      \  / \                        /     \   / \
                  1        2 7   3                     -6       3 6   9
                             \                                    \
                             -6                                    7
    **/
  }
}



class Node<T> {
  T data;
  Node left,right;
  Node(T data) {
    this.data = data;
  }
}
