class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}

class BinarySearchTree {
    Node root;

    BinarySearchTree() {
        root = null;
    }

    void insert(int key) {
        root = insertRec(root, key);
    }

    Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        return root;
    }

    void inorderTraversal() {
        inorderRec(root);
    }

    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    Node search(int key) {
        return searchRec(root, key);
    }

    Node searchRec(Node root, int key) {
        if (root == null || root.key == key)
            return root;

        if (key < root.key)
            return searchRec(root.left, key);

        return searchRec(root.right, key);
    }

    void delete(int key) {
        root = deleteRec(root, key);
    }

    Node deleteRec(Node root, int key) {
        if (root == null)
            return root;

        if (key < root.key)
            root.left = deleteRec(root.left, key);
        else if (key > root.key)
            root.right = deleteRec(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.key = minValue(root.right);
            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }

    int minValue(Node root) {
        int minValue = root.key;
        while (root.left != null) {
            minValue = root.left.key;
            root = root.left;
        }
        return minValue;
    }

    int maxValue(Node root) {
        int maxValue = root.key;
        while (root.right != null) {
            maxValue = root.right.key;
            root = root.right;
        }
        return maxValue;
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        // Inserting elements into the tree
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        // Printing the inorder traversal of the tree
        System.out.print("Inorder Traversal: ");
        tree.inorderTraversal();
        System.out.println();

        // Search for a key
        int searchKey = 40;
        Node searchResult = tree.search(searchKey);
        if (searchResult != null)
            System.out.println("Key " + searchKey + " found in the tree.");
        else
            System.out.println("Key " + searchKey + " not found in the tree.");

        // Delete a key
        int deleteKey = 30;
        tree.delete(deleteKey);
        System.out.println("Key " + deleteKey + " deleted from the tree.");

        // Printing the inorder traversal after deletion
        System.out.print("Inorder Traversal: ");
        tree.inorderTraversal();
        System.out.println();
    }
}