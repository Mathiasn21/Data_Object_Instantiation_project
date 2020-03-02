package framework.datastructure;
import java.util.ArrayList;
import java.util.List;

public class AVLTree {

    private static final int MAX_HALF_LENGTH = 4;

    public static class Node {
        private Node left, right;
        private int height = 1;
        private int value;

        public Node (int val) {
            this.value = val;
        }
    }
    public int height (Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    public Node insert(Node node, int value) {
        if (node == null) {
            return(new Node(value));
        }

        if (value < node.value) {
            node.left  = insert(node.left, value);
        } else {
            node.right = insert(node.right, value);
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        int balance = getBalance(node);

        if (balance > 1 && value < node.left.value) {
            return rightRotate(node);
        }

        if (balance < -1 && value > node.right.value) {
            return leftRotate(node);
        }

        if (balance > 1 && value > node.left.value) {
            node.left =  leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && value < node.right.value) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    public Node rightRotate(Node node) {
        Node x = node.left;
        Node T2 = x.right;

        x.right = node;
        node.left = T2;

        node.height = Math.max(height(node.left), height(node.right))+1;
        x.height = Math.max(height(x.left), height(x.right))+1;

        return x;
    }

    public Node leftRotate(Node node) {
        Node node2 = node.right;
        Node T2 = node2.left;

        node2.left = node;
        node.right = T2;

        node.height = Math.max(height(node.left), height(node.right))+1;
        node2.height = Math.max(height(node2.left), height(node2.right))+1;

        return node2;
    }

    public int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }


    public void preOrder(Node root) {
        if (root != null) {
            preOrder(root.left);
            System.out.printf("%d ", root.value);
            preOrder(root.right);
        }
    }

    public Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    public Node deleteNode(Node root, int value) {
        if (root == null)
            return null;

        if ( value < root.value )
            root.left = deleteNode(root.left, value);

        else if( value > root.value )
            root.right = deleteNode(root.right, value);


        else {
            if( (root.left == null) || (root.right == null) ) {
                root = root.left != null ? root.left : root.right;
            }
            else {
                Node temp = minValueNode(root.right);
                root.value = temp.value;
                root.right = deleteNode(root.right, temp.value);
            }
        }

        if (root == null) {
            return null;
        }

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left =  leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }

    public void print(Node root) {
        if(root == null) {
            System.out.println("(XXXXXX)");
            return;
        }

        int height = root.height, width = (int)Math.pow(2, height-1);

        List<Node> current = new ArrayList<>(1), next = new ArrayList<>(2);
        current.add(root);

        int elements = 1;

        StringBuilder sb = new StringBuilder(MAX_HALF_LENGTH *width);
        sb.append(" ".repeat(Math.max(0, MAX_HALF_LENGTH * width)));
        String textBuffer;

        for(int i = 0; i < height; i++) {
            sb.setLength(MAX_HALF_LENGTH * ((int)Math.pow(2, height-1-i) - 1));
            textBuffer = sb.toString();
            for(Node n : current) {
                System.out.print(textBuffer);

                if(n == null) {
                    System.out.print("        ");
                    next.add(null);
                    next.add(null);

                } else {
                    System.out.printf("(%6d)", n.value);
                    next.add(n.left);
                    next.add(n.right);
                }
                System.out.print(textBuffer);
            }

            System.out.println();
            if(i < height - 1) {
                for(Node n : current) {
                    System.out.print(textBuffer);
                    if(n == null) {
                        System.out.print("        ");
                    } else {
                        System.out.printf("%s      %s",
                                n.left == null ? " " : "/", n.right == null ? " " : "\\");
                    }

                    System.out.print(textBuffer);
                }
                System.out.println();
            }
            elements *= 2;
            current = next;
            next = new ArrayList<>(elements);
        }
    }
}
