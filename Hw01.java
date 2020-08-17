import java.util.*;
import java.io.*;

class Node
{
    int data;
    Node right = null;
    Node left = null;
    Node parent = null;

    Node(int data)
    {
        this.data = data;
    }
}


public class Hw01
{
    public Node root;
    public boolean tof;
    public int nodeL, nodeR, lD, rD;

    //Simple inorder print
    private void inorder_treewalk(Node root)
    {
        if(root != null)
        {
            inorder_treewalk(root.left);
            System.out.print(" " + root.data);
            inorder_treewalk(root.right);
        }
    }

    //helper
    public void inorder()
    {
        inorder_treewalk(root);
    }

    //searching for specific data in tree
    private boolean tree_search(Node root, int data)
    {
        //if key is not there there false
        if(root == null)
        {
            return false;
        }
        if(data < root.data)
        {
            return tree_search(root.left, data);
        }
        else if(data > root.data)
        {
            return tree_search(root.right, data);
        }
        //otherwise true
        else
            return true;
    }

    //helper
    public void treeSearch(int key)
    {
        tof = tree_search(root, key);
    }

    //Searching for left most node
    public int search_min(Node root)
    {
        while(root.left != null)
        {
            root = root.left;
        }

        return root.data;
    }

    //Searching for rightmost Node
    public int search_max(Node root)
    {
        while(root.right != null)
        {
            root = root.right;
        }
        return root.data;
    }

    //Insertion
    private Node insert(Node root, Node newNode)
    {
        if(root == null)
        {
            root = newNode;
            return root;
        }
        else if(newNode.data < root.data)
        {
            root.left = insert(root.left, newNode);
        }
        else if(newNode.data >= root.data)
        {
            root.right = insert(root.right, newNode);
        }

        return root;
    }

    //helper
    public void insert(Node node)
    {
        root = insert(root, node);
    }

    //helper
    public void delete(int key)
    {
        root = delete(root, key);
    }

    private Node delete(Node root, int key)
    {
        //tree empty
        if(root == null)
            return root;
        else if(key < root.data)
            root.left = delete(root.left, key);
        else if(key > root.data)
            root.right = delete(root.right, key);
        else
        {
            //no children just set to null
            if(root.right == null && root.left == null)
                return null;

            //no left child bbring up right child
            else if(root.left == null)
                return root.right;
            //no right child bbring up left
            else if(root.right == null)
                return root.left;
            //otherwise find min of right sub tree
            else
            {
                //set the root to minimum of right
                root.data = search_min(root.right);
                //go find the node with that data and get rid of it
                root.right = delete(root.right, root.data);
            }
        }
        return root;
    }

    //simple count function
    private int countNodes(Node root)
    {
      if(root == null)
        return 0;

      return 1 + countNodes(root.left) + countNodes(root.right);
    }

    //helper
    public void countL()
    {
      nodeL = countNodes(root.left);
    }

    //helper
    public void countR()
    {
      nodeR = countNodes(root.right);
    }

    private int countDepth(Node root)
    {
      int max, lDepth, rDepth;

      if (root == null)
        return 0;
      else
      {
          //recusrively going through each subtree to find the depth
          lDepth = countDepth(root.left);
          rDepth = countDepth(root.right);

          // calling the max function to find which subtree is bigger to find
          //the max depth
          return Math.max(lDepth, rDepth) + 1;
      }
    }

    //helper
    public void countDepthL()
    {
      lD = countDepth(root.left);
    }

    //helper
    public void countDepthR()
    {
      rD = countDepth(root.right);
    }

    public void complexityIndicator()
    {
      System.err.println("he200230");
      System.err.println("Difficulty: 2");
      System.err.println("Hours: 10");
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        int i = 0;
        String str;
        char c;
        Hw01 BST = new Hw01();
        File file = new File(args[0]);
        Scanner sc = new Scanner(file);
        Scanner sv = new Scanner(file);

        System.out.println(args[0] + " contains:");

        //just here to print out the file back to the user
        while(sv.hasNextLine())
        {
          System.out.println(sv.nextLine());
        }

        while(sc.hasNextLine())
        {
            c = sc.next().charAt(0);
            if(c == 'i')
            {
                i = sc.nextInt();
                BST.insert(new Node(i));
            }
            else if(c == 'p')
            {
                BST.inorder();
                System.out.println();
            }
            else if(c == 'd')
            {
                i = sc.nextInt();
                BST.treeSearch(i);
                if(BST.tof == true)
                {
                    BST.delete(i);
                }
            }
            else if(c == 's')
            {
              i = sc.nextInt();
              BST.treeSearch(i);

              if(BST.tof == true)
              {
                System.out.println(i + ": found");
              }
              else
              {
                System.out.println(i + ": NOT found");
              }
            }

            if(c == 'q')
            {
                break;
            }
        }

        BST.countL();
        BST.countR();
        BST.countDepthL();
        BST.countDepthR();
        System.out.printf("left children:%11d", BST.nodeL);
        System.out.println();
        System.out.printf("left depth:%14d",BST.lD);
        System.out.println();
        System.out.printf("right children:%10d", BST.nodeR);
        System.out.println();
        System.out.printf("right depth:%13d", BST.rD);
        System.out.println();
        return;
    }
}
