//Henrique Cury
//he200230
//3517770
//Hw02

import java.sql.SQLOutput;
import java.util.*;
import java.io.*;


//Node structure
class Node
{
    Node next;
    Node up;
    private int data;

    //getter
    public int getData()
    {
        return data;
    }

    //setter
    public void setData(int data)
    {
        this.data = data;
    }

    //sets null next and up
    public Node()
    {
        this.next = null;
        this.up = null;
    }
}

public class Hw02
{
    Node head;

    //inserting before(head) if it is less and next if it is greater
    private Node insert(Node head, Node newNode)
    {
        if(head == null)
        {
            head = newNode;
            return head;
        }
        else if(newNode.getData() > head.getData())
        {
            head.next = insert(head.next, newNode);
        }
        else if(newNode.getData() < head.getData())
        {
            newNode.next = head;
            head = newNode;
        }
        return head;
    }

    //accessing the private function
    public void insert(Node node)
    {
        head = insert(head, node);
    }


    //promotes the node until tails, copies the data of bottom layer
    public Node promoteUp(Node node)
    {

        node.up = new Node();
        node.up.setData(node.getData());
        return node;
    }

    //simple print function
    public void print(Node head)
    {
        if(head == null)
        {
            return;
        }

        System.out.print(" " + head.getData() + ";" + " ");

        if(head.up != null)
        {
            print(head.up);
        }
        else
            System.out.println();
        print(head.next);
        return;
    }

    //SEARCHING AND DELETING IF NEED BE
    public void search(int x, char c)
    {
        Node node = head; //traversal node
        Node temp = null;

        while(node.getData() != x)
        {
            temp = node;
            if(node.next != null)
                node = node.next;
            else
                break;
        }


        if(x == node.getData())
        {
            if(c == 'd')
            {
                if(head.getData() == x)
                {
                    temp = node.next;
                    head = demote(node);
                    head = temp;
                    System.out.println(x + " deleted");
                }
                else
                {
                    temp.next = node.next;
                    node = demote(node);
                    node = temp;
                    System.out.println(x + " deleted");
                }
            }
            else
                System.out.println(x + " found");
        }
        else if(x != node.getData() && c == 'd')
        {
            System.out.println(x + " integer not found - delete not successful");
        }
        else
            System.out.println(x + " NOT FOUND");

        return;
    }


    //DELETES UNTIL THERE IS NO MORE UP NODES
    public Node demote(Node node)
    {
        Node temp = node;

        if(node == null)
            return node;

        if(node.up != null)
            node.up = demote(node.up);

        node = null;
        return node;
    }


    public static void main(String[] args) throws FileNotFoundException
    {
        Hw02 skipList = new Hw02();
        char c;
        int i;
        File file = new File(args[0]);
        Scanner sc = new Scanner(file);
        long seed;
        Random random;

        System.out.println("For the input file named " + args[0]);

        //CODE FROM McAlpin
        try
        {

            if(args[0].equals(new String("R")))
            {
                seed = System.currentTimeMillis();
                System.out.println("With the RNG seeded," + seed + ",");
            }
            else
            {
                seed = 42;
                System.out.println("With the RNG unseeded,");
            }

        }
        catch (ArrayIndexOutOfBoundsException aiobe)
        {
            seed = 42;
            System.out.println("With the RNG unseeded,\n");
        }
        random = new Random(seed);


        //same code as last project with a bit of change
        while(sc.hasNextLine())
        {

            c = sc.next().charAt(0);
            if(c == 'i')
            {
                i = sc.nextInt();
                Node node = new Node();
                node.setData(i);

                while (random.nextInt() % 2 == 1)
                {
                    skipList.promoteUp(node);
                }

                skipList.insert(node);
            }
            else if(c == 'p')
            {
                System.out.println("the current Skip List is shown below: ");
                System.out.println("---infinity");
                skipList.print(skipList.head);
                System.out.println("+++infinity");
                System.out.println("---End of Skip List---");
            }
            else if(c == 's' || c=='d')
            {
                i = sc.nextInt();
                skipList.search(i, c);
            }
            if(c == 'q')
            {
                break;
            }
        }
        return;
    }
}
