package BinaryTree;

import java.util.*;

public class ReverseLevelOrderTraversal {
    static class Node {
        int data;
        Node left;
        Node right;
        Node(int val) {
            data = val;
            left = null;
            right = null;
        }
    }

    static int getHeight(Node root) {
        if(root == null) {
            return 0;
        }

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    static void printCurrentLevel(Node root, int level) {
        if (root == null) {
            return;
        }

        if (level == 1) {
            System.out.print(root.data + " ");
            return;
        }

        printCurrentLevel(root.left, level-1);
        printCurrentLevel(root.right, level-1);
    }

    static void printReverseLevelOrder(Node root) {
        int height = getHeight(root);
        for (int i = height; i >= 0; i--) {
            printCurrentLevel(root, i);
        }
    }

    static void printReverseLevelOrder2(Node root) {
        // Using queue and stack
        Stack<Node> stack = new Stack<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // adding right nodes to the queue then left nodes
            // for adding in the stack in the reverse order sequence
            // when popping from the stack left - right order the nodes will return in a level
            Node topNode = queue.peek();
            queue.remove();
            stack.add(topNode);

            if (topNode.right != null) {
                queue.add(topNode.right);
            }

            if (topNode.left != null) {
                queue.add(topNode.left);
            }

        }

        while(!stack.isEmpty()) {
            Node node = stack.peek();
            stack.pop();
            System.out.print(node.data + " ");
        }

    }

    static List<Integer> printReverseLevelOrder3(Node node) {
        // Using a hashmap
        // create a hashmap and add every node mapped to a level
        List<Integer> result = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        addNodesToMap(node, 0, map);

        for (int level = map.size(); level >= 0; level--) {
            List<Integer> nodesInLevel = map.get(level); // list of nodes in a level
            for (int i = 0; i < nodesInLevel.size(); i++) {
                // add the nodes data to the result
                result.add(nodesInLevel.get(i));
            }
        }

        return result;
    }

    private static void addNodesToMap(Node node, int level, Map<Integer, List<Integer>> map) {
        if (node == null) {
            return;
        }

        if (!map.containsKey(level)) {
            // if level is not present in the map, add it
            map.put(level, new ArrayList<>());
        }
        // add the node data to the list mapped to the level
        map.get(level).add(node.data);
        // call recursively left and right node to add it to the list mapped to the next level
        addNodesToMap(node.left, level+1, map);
        addNodesToMap(node.right, level+1, map);
    }

    public static void main(String[] args) {
        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(3);
        tree.left.left = new Node(4);
        tree.left.right = new Node(5);
        tree.right.left = new Node(6);
        tree.right.right = new Node(7);

//        printReverseLevelOrder2(tree);
        List<Integer> result = printReverseLevelOrder3(tree);
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }
    }
}
