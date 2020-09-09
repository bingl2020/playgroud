package Tree;
//https://www.geeksforgeeks.org/convert-given-binary-tree-doubly-linked-list-set-3/
public class ConvertToDoublyLinkedList {

    class PrevNodeWrapper {
        Node head;
        Node prev;
    }

    public Node convertBinaryTreeToDDL(Node root) {
        PrevNodeWrapper prevNodeWrapper = new PrevNodeWrapper();

        convertBinaryTreeToDDLRec(root, prevNodeWrapper);

        return prevNodeWrapper.head;
    }

    public void convertBinaryTreeToDDLRec(Node root, PrevNodeWrapper prevNodeWrapper) {
        if (root == null) {
            return;
        }
        convertBinaryTreeToDDLRec(root.left, prevNodeWrapper);

        // process
        if (prevNodeWrapper.prev == null) {
            prevNodeWrapper.head = root;
        } else {
            prevNodeWrapper.prev.right = root;
        }
        root.left = prevNodeWrapper.prev;
        prevNodeWrapper.prev = root;

        convertBinaryTreeToDDLRec(root.right, prevNodeWrapper);
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

        Node root = new Node(20);
        root.left = new Node(10);
        root.right = new Node(30);
        root.right.left = new Node(25);
        root.right.right = new Node(100);

//        Node root = new Node(1);
//        root.left = new Node(2);
//        root.right = new Node(3);
//        root.left.left = new Node(4);
//        root.left.right = new Node(5);
//        root.right.left = new Node(6);
//        root.right.right = new Node(7);
//        root.left.left.left = new Node(8);
//        root.left.left.right = new Node(9);
//        root.left.right.left = new Node(10);
//        root.left.right.right = new Node(11);
//        root.right.left.left = new Node(12);
//        root.right.left.right = new Node(13);
//        root.right.right.left = new Node(14);
//        root.right.right.right = new Node(15);

        ConvertToDoublyLinkedList test = new ConvertToDoublyLinkedList();
        Node head = test.convertBinaryTreeToDDL(root);

        while (head != null) {
            System.out.print(head.key + " -> ");
            head = head.right;
        }
    }

}
