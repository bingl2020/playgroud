package Tree;

public class ConvertToDoublyLinkedList {

    class Context {
        Node head;
        Node prev;
    }

    public Node convertBinaryTreeToDDL(Node root) {
        Context context = new Context();

        convertBinaryTreeToDDLRec(root, context);

        return context.head;
    }

    public void convertBinaryTreeToDDLRec(Node root, Context context) {
        if (root == null) {
            return;
        }
        convertBinaryTreeToDDLRec(root.left, context);

        // process
        if (context.prev == null) {
            context.head = root;
        } else {
            context.prev.right = root;
        }
        root.left = context.prev;
        context.prev = root;

        convertBinaryTreeToDDLRec(root.right, context);
    }

    public static void main(String[] args) {
		/* Construct below tree
	                1
	              /   \
	            /       \
	          2           3
	        /   \       /   \
	       4     5     6     7
	      / \   / \   / \   / \
	     8   9 10 11 12 13 14 15

		*/

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.left.left.left = new Node(8);
        root.left.left.right = new Node(9);
        root.left.right.left = new Node(10);
        root.left.right.right = new Node(11);
        root.right.left.left = new Node(12);
        root.right.left.right = new Node(13);
        root.right.right.left = new Node(14);
        root.right.right.right = new Node(15);

        ConvertToDoublyLinkedList test = new ConvertToDoublyLinkedList();
        Node head = test.convertBinaryTreeToDDL(root);

        while (head != null) {
            System.out.print(head.key + " -> ");
            head = head.right;
        }
    }

}
