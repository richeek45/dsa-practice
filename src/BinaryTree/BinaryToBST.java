package BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;

public class BinaryToBST {
    // Given the array representation of Complete Binary Tree i.e, if index i is the parent,
    // index 2*i + 1 is the left child and index 2*i + 2 is the right child.
    // The task is to find the minimum number of swap required to convert it into Binary Search Tree.
    static class Pair {
        int first;
        int second;
        Pair(int a, int b) {
            first = a;
            second = b;
        }
    }

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

    static Node insert(Node root) {
        root = new Node(5);
        root.left = new Node(6);
        root.right = new Node(7);
        root.left.left = new Node(8);
        root.left.right = new Node(9);
        root.right.left = new Node(10);
        root.right.right = new Node(11);
        return root;
    }

    static void printInOrder(Node root) {
        // The idea is to use the fact that inorder traversal of Binary Search Tree is in increasing order of their value.
        if (root != null) {
            printInOrder(root.left);
            System.out.print(root.data + " ");
            printInOrder(root.right);
        }
    }

    static void inOrder(int[] arr, Vector<Integer> list, int n, int index) {
        if (index >= n) {
            return;
        }

        // adding the elements in the array or tree in inOrder sequence
        inOrder(arr, list, n, 2 * index + 1);
        list.add(arr[index]);
        inOrder(arr, list, n, 2 * index + 2);
    }

    static int minSwaps(Vector<Integer> list) {
        // The idea is to find which elements are not in correct index position in comparison to
        // ascending order elements and how many swaps required to get it in correct position
        // list has the values in inOrder sequence
        int size = list.size();
        ArrayList<Pair> arrPos = new ArrayList<Pair>();
        System.out.println(list);
        for (int i = 0; i < size; i++) {
            arrPos.add(new Pair(list.get(i), i));
        }

        // sorting in ascending order
        arrPos.sort(new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                return p1.first - p2.first;
            }
        });

        Boolean[] vis = new Boolean[size];
        Arrays.fill(vis, false);
        int ans = 0;
        for(int i = 0; i < size; i++) {
            System.out.print( "[" + arrPos.get(i).first + "," + arrPos.get(i).second + "] ");

            if (vis[i] || arrPos.get(i).second == i) {
                continue;
            }

            int cycleSize = 0;
            int j = i;
            while(!vis[j]) {
                vis[j] = true;
                j = arrPos.get(j).second;
                cycleSize++;
            }

            if (cycleSize > 0) {
                ans += cycleSize - 1;
            }
        }

        return ans;
    }


    public static void main(String[] args) {
        int[] arr = {5, 6, 7, 8, 9, 10, 11};
        // 8, 6, 9, 5, 10, 7, 11 -> inOrder traversal
        // 5, 6, 7, 8, 9, 10, 11 -> sorted inOrder traversal
        Vector<Integer> v = new Vector<>();
        int len = arr.length;
//        Node root = null;
//        root = insert(root);
//        printInOrder(root);
        inOrder(arr, v, len, 0);
        int minSwap = minSwaps(v);
        System.out.println(minSwap);

    }
}
