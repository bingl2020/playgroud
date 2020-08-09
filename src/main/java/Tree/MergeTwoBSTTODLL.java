package Tree;

public class MergeTwoBSTTODLL {
    class DDL {
        Node head, tail;
    }

    public Node merge2(Node a, Node b) {

//        ConvertToDoublyLinkedList convertor = new ConvertToDoublyLinkedList();
//
//        Node first = convertor.convertBinaryTreeToDDL(a);
//        Node second = convertor.convertBinaryTreeToDDL(b);

        Node first = convertBSTtoDDL2(a);

        Node second = convertBSTtoDDL2(b);

        return mergeDDLs(first, second);
    }

    private Node convertBSTtoDDL2(Node node) {
        DDL ddl = new DDL();

        convertBSTtoDDL2(node, ddl);
        return ddl.head;
    }

    private void convertBSTtoDDL2(Node root, DDL ddl) {
        if (root == null) {
            return;
        }

        convertBSTtoDDL2(root.left, ddl);
        // process
        if (ddl.tail == null) {
            ddl.head = root;
        } else {
            ddl.tail.right = root;
        }

        root.left = ddl.tail;
        ddl.tail = root;

        convertBSTtoDDL2(root.right, ddl);
    }

    private Node mergeDDLs2(Node first, Node second) {
        Node dummy = new Node(0);

        Node curr = dummy;
        while (first != null && second != null) {
            Node temp = null;
            if (first.key < second.key) {
                temp = first;
                first = first.right;

            } else {
                temp = second;
                second = second.right;
            }

            curr.right = temp;
            temp.left = curr;

            curr = curr.right;
        }

        while (first != null) {
            Node temp = first;
            first = first.right;

            curr.right = temp;
            temp.left = curr;

            curr = curr.right;
        }

        while (second != null) {
            Node temp = second;
            second = second.right;

            curr.right = temp;
            temp.left = curr;

            curr = curr.right;
        }

        curr.right = null;

        if (dummy.right != null) {
            dummy.right.left = null;
        }

        return dummy.left;
    }

    public static void printDoublyList(Node head) {
        while (head != null) {
            System.out.print(head.key + " -> ");
            head = head.right;
        }
        System.out.print("null");
    }

    // Function to insert a BST node at the front of a doubly linked list
    public Node push(Node root, Node head) {
        // insert the given node at the front of the DDL
        root.right = head;

        // update the left pointer of the existing head node of the DDL
        // to point to the BST node
        if (head != null) {
            head.left = root;
        }

        // update the head pointer of DDL
        head = root;
        return head;
    }

    /*
    Recursive function to convert a binary search tree into a doubly linked list
        root -> Pointer to the root node of the binary search tree
        head -> Reference to the head node of the doubly linked list
    */
    public Node convertBSTtoDLL(Node root, Node head) {
        // Base case
        if (root == null) {
            return head;
        }

        // recursively convert the right subtree a
        head = convertBSTtoDLL(root.right, head);

        // push current node at the front of the doubly linked list
        head = push(root, head);

        // recursively convert the left subtree
        head = convertBSTtoDLL(root.left, head);

        return head;
    }

    // Recursive function to merge two doubly linked list into a
    // single doubly linked list in sorted order
    public Node mergeDDLs(Node a, Node b) {
        // if the first list is empty, return the second list
        if (a == null) {
            return b;
        }

        // if the second list is empty, return the first list
        if (b == null) {
            return a;
        }

        // if head node of the first list is smaller
        if (a.key < b.key) {
            a.right = mergeDDLs(a.right, b);
            a.right.left = a;
            return a;
        }

        // if head node of the second list is smaller
        else {
            b.right = mergeDDLs(a, b.right);
            b.right.left = b;
            return b;
        }
    }

    // Function to merge two binary search trees into a doubly linked list
    // in sorted order
    public Node merge(Node a, Node b) {
        // Convert first binary search tree to a doubly linked list
        Node first = null;
        first = convertBSTtoDLL(a, first);

        // Convert second binary search tree to a doubly linked list
        Node second = null;
        second = convertBSTtoDLL(b, second);

        // Merge both doubly linked lists
        return mergeDDLs(first, second);
    }

    public static void main(String[] args) {
		/*
		Construct first BST
			  20
			 /  \
			10  30
			   /  \
			  25  100
		*/

        Node a = new Node(20);
        a.left = new Node(10);
        a.right = new Node(30);
        a.right.left = new Node(25);
        a.right.right = new Node(100);

        inorderReverse(a);
        System.out.println("-----------------");
		/*
		Construct second BST
			  50
			 /  \
			5   70
		*/

        Node b = new Node(50);
        b.left = new Node(5);
        b.right = new Node(70);



        // merge both BSTs into a doubly linked list
        MergeTwoBSTTODLL test = new MergeTwoBSTTODLL();
        Node root = test.merge(a, b);
        printDoublyList(root);



//        Node root2 = test.merge2(a, b);
//        printDoublyList(root2);
    }

    private static  void inorderReverse(Node root) {
        if (root == null) {
            return;
        }

        inorderReverse(root.right);
        System.out.print(root.key + " -> ");
        inorderReverse(root.left);
    }
}